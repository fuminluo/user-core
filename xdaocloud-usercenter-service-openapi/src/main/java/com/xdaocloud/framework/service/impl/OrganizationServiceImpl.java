package com.xdaocloud.framework.service.impl;

import java.util.ArrayList;
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
import com.xdaocloud.framework.dto.request.organization.FindAllOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.SaveOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.SaveOrganizationUserRequest;
import com.xdaocloud.framework.dto.request.organization.UpdateOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.UpdateOrganizationStatusRequest;
import com.xdaocloud.framework.dto.response.organization.FindAllResponse;
import com.xdaocloud.framework.dto.response.organization.FindOneResponse;
import com.xdaocloud.framework.dto.response.organization.FindUserByOrganizationIdResponse;
import com.xdaocloud.framework.mapper.OrganizationMapper;
import com.xdaocloud.framework.mapper.OrganizationUserMapper;
import com.xdaocloud.framework.model.Organization;
import com.xdaocloud.framework.model.OrganizationUser;
import com.xdaocloud.framework.service.OrganizationService;
import com.xdaocloud.framework.tree.TreeView;
import com.xdaocloud.framework.tree.TreeViewBuilder;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 组织管理
 * 
 * @author WangPengHua
 */
@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationUserMapper organizationUserMapper;

    /**
     * 分页查询所有
     */
    @Override
    public ResultInfo<PageResponse> findAll(FindAllOrganizationRequest request)
        throws Exception {
        PageHelper.startPage(request.getPage(), request.getSize());
        List<FindAllResponse> list = organizationMapper.findAll(request);
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        PageInfo<FindAllResponse> pageInfo = new PageInfo<FindAllResponse>(list);
        PageResponse response = new PageResponse(pageInfo);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    /**
     * 查询树结构
     */
    @Override
    public ResultInfo<List<TreeView>> findAllAsTreeView()
        throws Exception {
        Example example = new Example(Organization.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        criteria.andEqualTo("status", 1);
        List<Organization> list = organizationMapper.selectByExample(example);
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        // 转成成TreeView格式
        List<TreeView> trees = new ArrayList<>();
        for (Organization data : list) {
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
     * 详细
     */
    @Override
    public ResultInfo<FindOneResponse> findById(Long id)
        throws Exception {
        Organization organization = organizationMapper.selectByPrimaryKey(id);
        if (organization == null) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        FindOneResponse parentResponse = null;
        if (organization.getParentId() != null) {
            Organization parent = organizationMapper.selectByPrimaryKey(organization.getParentId());
            if (parent != null) {
                parentResponse = new FindOneResponse();
                parentResponse.setId(parent.getId());
                parentResponse.setName(parent.getName());
            }
        }
        FindOneResponse response = new FindOneResponse();
        response.setId(organization.getId());
        response.setName(organization.getName());
        response.setParent(parentResponse);
        response.setType(organization.getType());
        response.setAddress(organization.getAddress());
        response.setNation(organization.getNation());
        response.setProvince(organization.getProvince());
        response.setCity(organization.getCity());
        response.setCounty(organization.getCounty());
        response.setTelephone(organization.getTelephone());
        response.setMobilePhone(organization.getMobilePhone());
        response.setEmail(organization.getEmail());
        response.setTotalOutputValue(organization.getTotalOutputValue());
        response.setIndustryType(organization.getIndustryType());
        response.setUnitType(organization.getUnitType());
        response.setLinkman(organization.getLinkman());
        response.setMemberSize(organization.getMemberSize());
        response.setDescription(organization.getDescription());
        response.setStatus(organization.getStatus());

        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    /**
     * 保存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> save(SaveOrganizationRequest request)
        throws Exception {

        Organization organization = new Organization();
        organization.setName(request.getName());
        if (request.getParentId() != null && request.getParentId() > 0) {
            organization.setParentId(request.getParentId());
        }
        organization.setType(request.getType());
        organization.setNation(request.getNation());
        organization.setProvince(request.getProvince());
        organization.setCity(request.getCity());
        organization.setCounty(request.getCounty());
        organization.setAddress(request.getAddress());
        organization.setTelephone(request.getTelephone());
        organization.setMobilePhone(request.getMobilePhone());
        organization.setEmail(request.getEmail());
        organization.setTotalOutputValue(request.getTotalOutputValue());
        organization.setIndustryType(request.getIndustryType());
        organization.setUnitType(request.getUnitType());
        organization.setLinkman(request.getLinkman());
        organization.setMemberSize(request.getMemberSize());
        organization.setDescription(request.getDescription());

        boolean bool = organizationMapper.insertSelective(organization) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 修改
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> update(UpdateOrganizationRequest request)
        throws Exception {

        Organization organization = new Organization();
        organization.setId(request.getId());
        organization.setName(request.getName());
        if (request.getParentId() != null && request.getParentId() > 0) {
            organization.setParentId(request.getParentId());
        }
        organization.setType(request.getType());
        organization.setNation(request.getNation());
        organization.setProvince(request.getProvince());
        organization.setCity(request.getCity());
        organization.setCounty(request.getCounty());
        organization.setAddress(request.getAddress());
        organization.setTelephone(request.getTelephone());
        organization.setMobilePhone(request.getMobilePhone());
        organization.setEmail(request.getEmail());
        organization.setTotalOutputValue(request.getTotalOutputValue());
        organization.setIndustryType(request.getIndustryType());
        organization.setUnitType(request.getUnitType());
        organization.setLinkman(request.getLinkman());
        organization.setMemberSize(request.getMemberSize());
        organization.setDescription(request.getDescription());

        boolean bool = organizationMapper.updateByPrimaryKeySelective(organization) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 逻辑删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> deleteLogic(List<Long> ids)
        throws Exception {
        if (ListUtils.isEmpty(ids)) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        for (Long id : ids) {
            Organization organization = new Organization();
            organization.setId(id);
            organization.setDeleted(true);
            organization.setUpdateTime(new Date());
            boolean bool = organizationMapper.updateByPrimaryKeySelective(organization) > 0;
            if (!bool) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 物理删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> delete(List<Long> ids)
        throws Exception {
        if (ListUtils.isEmpty(ids)) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        Example example = new Example(Organization.class);
        example.createCriteria().andIn("id", ids);
        boolean bool = organizationMapper.deleteByExample(example) > 0;
        if (!bool) {
            throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 更新状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> updateStatus(UpdateOrganizationStatusRequest request)
        throws Exception {
        for (Long id : request.getIds()) {
            Organization organization = new Organization();
            organization.setId(id);
            organization.setStatus(request.getStatus());
            organization.setUpdateTime(new Date());
            boolean bool = organizationMapper.updateByPrimaryKeySelective(organization) > 0;
            if (!bool) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 查询子组织
     */
    @Override
    public List<Long> findChildrenIds(List<Long> childrenIds, Long rootId)
        throws Exception {
        Organization organization = new Organization();
        organization.setParentId(rootId);
        List<Organization> list = organizationMapper.select(organization);
        if (!ListUtils.isEmpty(list)) {
            for (Organization obj : list) {
                childrenIds.add(obj.getId());
                findChildrenIds(childrenIds, obj.getId());
            }
        }
        return childrenIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> saveUser(SaveOrganizationUserRequest request)
        throws Exception {
        Long organizationId = request.getOrganizationId();
        // 先删除旧的用户
        organizationUserMapper.delete(new OrganizationUser(organizationId));

        // 保存新的用户
        List<Long> userIds = request.getUserIds();
        if (!ListUtils.isEmpty(userIds)) {
            for (Long userId : userIds) {
                int insertSelective = organizationUserMapper.insertSelective(new OrganizationUser(organizationId, userId));
                if (insertSelective == 0) {
                    throw new XdaoException(ResultInfo.ERROR, ResultInfo.MSG_ERROR);
                }
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    @Override
    public ResultInfo<List<FindUserByOrganizationIdResponse>> findUserByOrganizationId(Long organizationId)
        throws Exception {
        // 用户量大时本接口需改造
        List<FindUserByOrganizationIdResponse> responses = organizationMapper.findUserByOrganizationId(organizationId);
        if (ListUtils.isEmpty(responses)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, responses);
    }
}
