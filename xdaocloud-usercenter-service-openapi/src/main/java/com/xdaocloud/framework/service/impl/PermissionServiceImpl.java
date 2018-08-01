package com.xdaocloud.framework.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xdaocloud.framework.dto.request.permission.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdaocloud.base.exception.XdaoException;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.base.utils.ListUtils;
import com.xdaocloud.framework.dto.response.permission.FindAllResponse;
import com.xdaocloud.framework.dto.response.permission.FindByRoleIdResponse;
import com.xdaocloud.framework.dto.response.permission.FindOneResponse;
import com.xdaocloud.framework.mapper.PermissionMapper;
import com.xdaocloud.framework.model.Permission;
import com.xdaocloud.framework.service.PermissionService;
import com.xdaocloud.framework.tree.TreeView;
import com.xdaocloud.framework.tree.TreeViewBuilder;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public ResultInfo<PageResponse> findAll(FindAllPermissionRequest request)
            throws Exception {
        PageHelper.startPage(request.getPage(), request.getSize());
        List<FindAllResponse> list = permissionMapper.findAll(request);
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        PageInfo<FindAllResponse> pageInfo = new PageInfo<FindAllResponse>(list);
        PageResponse response = new PageResponse(pageInfo);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    @Override
    public ResultInfo<FindOneResponse> findById(Long id)
            throws Exception {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if (permission == null) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        FindOneResponse parentResponse = null;
        if (permission.getParentId() != null) {
            Permission parent = permissionMapper.selectByPrimaryKey(permission.getParentId());
            if (parent != null) {
                parentResponse = new FindOneResponse(parent.getId(), parent.getName());
            }
        }
        FindOneResponse response = new FindOneResponse(permission.getId(), permission.getAppId(), permission.getName(), permission.getPermissionKey(), permission.getType(), permission.getUrl(), permission.getMethod(), parentResponse, permission.getRemark(), permission.getStatus());
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> save(SavePermissionRequest request)
            throws Exception {
        Permission permission = new Permission();
        permission.setPermissionKey(request.getPermissionKey().toLowerCase());
        int count = permissionMapper.selectCount(permission);
        if (count > 0) {
            return new ResultInfo<>(ResultInfo.FAILURE, "该权限Key已存在");
        }

        permission.setName(request.getName());
        if (request.getParentId() != null) {
            permission.setParentId(request.getParentId());
        }
        permission.setType(request.getType());
        permission.setUrl(request.getUrl());
        permission.setMethod(request.getMethod());
        permission.setRemark(request.getRemark());
        permission.setAppId(request.getAppId());
        boolean bool = permissionMapper.insertSelective(permission) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> update(UpdatePermissionRequest request)
            throws Exception {
        Permission permission = new Permission();
        Permission p = permissionMapper.selectByPrimaryKey(request.getId());
        if (!p.getPermissionKey().equalsIgnoreCase(request.getPermissionKey())) {
            permission.setPermissionKey(request.getPermissionKey().toLowerCase());
            int count = permissionMapper.selectCount(permission);
            if (count > 0) {
                return new ResultInfo<>(ResultInfo.FAILURE, "该权限Key已存在");
            }
        }

        permission.setId(request.getId());
        permission.setName(request.getName());
        if (request.getParentId() != null) {
            permission.setParentId(request.getParentId());
        }
        permission.setType(request.getType());
        permission.setUrl(request.getUrl());
        permission.setMethod(request.getMethod());
        permission.setRemark(request.getRemark());
        permission.setAppId(request.getAppId());
        boolean bool = permissionMapper.updateByPrimaryKeySelective(permission) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> deleteLogic(List<Long> ids)
            throws Exception {
        if (ListUtils.isEmpty(ids)) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        for (Long id : ids) {
            Permission permission = new Permission();
            permission.setId(id);
            permission.setDeleted(true);
            permission.setUpdateTime(new Date());
            boolean bool = permissionMapper.updateByPrimaryKeySelective(permission) > 0;
            if (!bool) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> delete(List<Long> ids)
            throws Exception {
        if (ListUtils.isEmpty(ids)) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        Example example = new Example(Permission.class);
        example.createCriteria().andIn("id", ids);
        boolean bool = permissionMapper.deleteByExample(example) > 0;
        if (!bool) {
            throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> updateStatus(UpdatePermissionStatusRequest request)
            throws Exception {
        for (Long id : request.getIds()) {
            Permission permission = new Permission();
            permission.setId(id);
            permission.setStatus(request.getStatus());
            permission.setUpdateTime(new Date());
            boolean bool = permissionMapper.updateByPrimaryKeySelective(permission) > 0;
            if (!bool) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    public ResultInfo<List<TreeView>> findCheckedAsTreeByRoleId(Long roleId)
            throws Exception {
        List<FindByRoleIdResponse> list = permissionMapper.findCheckedByRoleId(roleId);
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        List<TreeView> trees = new ArrayList<>();
        for (FindByRoleIdResponse data : list) {
            trees.add(new TreeView(data.getId(), data.getName(), data.getParentId(), data.getChecked()));
        }
        List<TreeView> build = TreeViewBuilder.build(trees);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, build);
    }

    /**
     * 查询树结构
     */
    @Override
    public ResultInfo<List<TreeView>> findAllAsTreeView(String appId)
            throws Exception {
        Example example = new Example(Permission.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        criteria.andEqualTo("status", 1);
        if (StringUtils.isNotBlank(appId)) {
            criteria.andEqualTo("appId", appId);
        }
        List<Permission> list = permissionMapper.selectByExample(example);
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        // 转成成TreeView格式
        List<TreeView> trees = new ArrayList<>();
        for (Permission data : list) {
            if (data.getParentId() == null) {
                trees.add(new TreeView(data.getId(), data.getName(), data.getParentId(), null));
            } else {
                trees.add(new TreeView(data.getId(), data.getName(), data.getParentId(), null));
            }
        }
        List<TreeView> build = TreeViewBuilder.build(trees);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, build);
    }

    /**
     * 根据用户id和应用查询列表
     *
     * @author LuoFuMin
     * @data 2018/7/14
     */
    @Override
    public ResultInfo<?> findPermission(FindPermissionRequest findPermissionRequest) {
        List<String> list = permissionMapper.findPermission(findPermissionRequest.getUserId(), findPermissionRequest.getAppId());
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, list);
    }
}
