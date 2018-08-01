package com.xdaocloud.framework.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdaocloud.base.exception.XdaoException;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.base.utils.ListUtils;
import com.xdaocloud.framework.dto.request.role.FindAllRoleRequest;
import com.xdaocloud.framework.dto.request.role.SaveRequest;
import com.xdaocloud.framework.dto.request.role.SaveRolePermissionRequest;
import com.xdaocloud.framework.dto.request.role.SaveRoleUserRequest;
import com.xdaocloud.framework.dto.request.role.UpdateRoleRequest;
import com.xdaocloud.framework.dto.request.role.UpdateRoleStatusRequest;
import com.xdaocloud.framework.dto.response.role.FindAllResponse;
import com.xdaocloud.framework.dto.response.role.FindOneResponse;
import com.xdaocloud.framework.dto.response.role.FindUserByRoleIdResponse;
import com.xdaocloud.framework.mapper.RoleMapper;
import com.xdaocloud.framework.mapper.RolePermissionMapper;
import com.xdaocloud.framework.mapper.UserRoleMapper;
import com.xdaocloud.framework.model.Role;
import com.xdaocloud.framework.model.RolePermission;
import com.xdaocloud.framework.model.UserRole;
import com.xdaocloud.framework.service.RoleService;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public ResultInfo<PageResponse> findAll(FindAllRoleRequest request)
        throws Exception {
        PageHelper.startPage(request.getPage(), request.getSize());
        List<FindAllResponse> list = roleMapper.findAll(request);
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
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        FindOneResponse response = new FindOneResponse(role.getId(), role.getName(), role.getRemark(), role.getStatus());
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> save(SaveRequest request)
        throws Exception {
        Role role = new Role();
        role.setName(request.getName());
        role.setRemark(request.getRemark());
        boolean bool = roleMapper.insertSelective(role) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> update(UpdateRoleRequest request)
        throws Exception {
        Role role = new Role();
        role.setId(request.getId());
        role.setName(request.getName());
        role.setRemark(request.getRemark());
        boolean bool = roleMapper.updateByPrimaryKeySelective(role) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> savePermission(SaveRolePermissionRequest request)
        throws Exception {
        Long roleId = request.getRoleId();
        // 先删除旧的权限
        rolePermissionMapper.delete(new RolePermission(roleId));

        // 保存新的权限
        List<Long> permissionIds = request.getPermissionIds();
        if (!ListUtils.isEmpty(permissionIds)) {
            for (Long permissionId : permissionIds) {
                int insertSelective = rolePermissionMapper.insertSelective(new RolePermission(roleId, permissionId));
                if (insertSelective == 0) {
                    throw new XdaoException(ResultInfo.ERROR, ResultInfo.MSG_ERROR);
                }
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> saveUser(SaveRoleUserRequest request)
        throws Exception {
        Long roleId = request.getRoleId();
        // 先删除旧的用户
        userRoleMapper.delete(new UserRole(roleId));

        // 保存新的用户
        List<Long> userIds = request.getUserIds();
        if (!ListUtils.isEmpty(userIds)) {
            for (Long userId : userIds) {
                int insertSelective = userRoleMapper.insertSelective(new UserRole(roleId, userId));
                if (insertSelective == 0) {
                    throw new XdaoException(ResultInfo.ERROR, ResultInfo.MSG_ERROR);
                }
            }
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
            Role role = new Role();
            role.setId(id);
            role.setDeleted(true);
            role.setUpdateTime(new Date());
            boolean bool = roleMapper.updateByPrimaryKeySelective(role) > 0;
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
        Example example = new Example(Role.class);
        example.createCriteria().andIn("id", ids);
        boolean bool = roleMapper.deleteByExample(example) > 0;
        if (!bool) {
            throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> updateStatus(UpdateRoleStatusRequest request)
        throws Exception {
        for (Long id : request.getIds()) {
            Role role = new Role();
            role.setId(id);
            role.setStatus(request.getStatus());
            role.setUpdateTime(new Date());
            boolean bool = roleMapper.updateByPrimaryKeySelective(role) > 0;
            if (!bool) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    public ResultInfo<List<FindUserByRoleIdResponse>> findUserByRoleId(Long roleId)
        throws Exception {
        // 用户量大时本接口需改造
        List<FindUserByRoleIdResponse> responses = roleMapper.findUserByRoleId(roleId);
        if (ListUtils.isEmpty(responses)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, responses);
    }
}
