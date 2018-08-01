package com.xdaocloud.framework.service;

import java.util.List;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.request.organization.FindAllOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.SaveOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.SaveOrganizationUserRequest;
import com.xdaocloud.framework.dto.request.organization.UpdateOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.UpdateOrganizationStatusRequest;
import com.xdaocloud.framework.dto.response.organization.FindOneResponse;
import com.xdaocloud.framework.dto.response.organization.FindUserByOrganizationIdResponse;
import com.xdaocloud.framework.tree.TreeView;

public interface OrganizationService {

    ResultInfo<PageResponse> findAll(FindAllOrganizationRequest request)
        throws Exception;

    ResultInfo<FindOneResponse> findById(Long id)
        throws Exception;

    ResultInfo<?> save(SaveOrganizationRequest request)
        throws Exception;

    ResultInfo<?> deleteLogic(List<Long> ids)
        throws Exception;

    ResultInfo<?> delete(List<Long> ids)
        throws Exception;

    ResultInfo<?> updateStatus(UpdateOrganizationStatusRequest request)
        throws Exception;

    ResultInfo<List<TreeView>> findAllAsTreeView()
        throws Exception;

    List<Long> findChildrenIds(List<Long> childrenIds, Long rootId)
        throws Exception;

    ResultInfo<?> update(UpdateOrganizationRequest request)
        throws Exception;

    ResultInfo<?> saveUser(SaveOrganizationUserRequest request)
        throws Exception;

    ResultInfo<List<FindUserByOrganizationIdResponse>> findUserByOrganizationId(Long organizationId)
        throws Exception;
}
