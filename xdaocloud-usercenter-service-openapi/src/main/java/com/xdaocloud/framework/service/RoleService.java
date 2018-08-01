package com.xdaocloud.framework.service;

import java.util.List;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.request.role.FindAllRoleRequest;
import com.xdaocloud.framework.dto.request.role.SaveRequest;
import com.xdaocloud.framework.dto.request.role.SaveRolePermissionRequest;
import com.xdaocloud.framework.dto.request.role.SaveRoleUserRequest;
import com.xdaocloud.framework.dto.request.role.UpdateRoleRequest;
import com.xdaocloud.framework.dto.request.role.UpdateRoleStatusRequest;
import com.xdaocloud.framework.dto.response.role.FindOneResponse;
import com.xdaocloud.framework.dto.response.role.FindUserByRoleIdResponse;

public interface RoleService {

    ResultInfo<PageResponse> findAll(FindAllRoleRequest request)
        throws Exception;

    ResultInfo<FindOneResponse> findById(Long id)
        throws Exception;

    ResultInfo<?> save(SaveRequest request)
        throws Exception;

    ResultInfo<?> deleteLogic(List<Long> ids)
        throws Exception;

    ResultInfo<?> delete(List<Long> ids)
        throws Exception;

    ResultInfo<?> updateStatus(UpdateRoleStatusRequest request)
        throws Exception;

    ResultInfo<?> savePermission(SaveRolePermissionRequest request)
        throws Exception;

    ResultInfo<?> saveUser(SaveRoleUserRequest request)
        throws Exception;

    ResultInfo<List<FindUserByRoleIdResponse>> findUserByRoleId(Long roleId)
        throws Exception;

    ResultInfo<?> update(UpdateRoleRequest updateRequest)
        throws Exception;
}
