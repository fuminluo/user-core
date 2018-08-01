package com.xdaocloud.framework.service;

import java.util.List;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.request.permission.*;
import com.xdaocloud.framework.dto.response.permission.FindOneResponse;
import com.xdaocloud.framework.tree.TreeView;

public interface PermissionService {

    ResultInfo<PageResponse> findAll(FindAllPermissionRequest request)
        throws Exception;

    ResultInfo<FindOneResponse> findById(Long id)
        throws Exception;

    ResultInfo<?> save(SavePermissionRequest request)
        throws Exception;

    ResultInfo<?> deleteLogic(List<Long> ids)
        throws Exception;

    ResultInfo<?> delete(List<Long> ids)
        throws Exception;

    ResultInfo<?> updateStatus(UpdatePermissionStatusRequest request)
        throws Exception;

    ResultInfo<List<TreeView>> findCheckedAsTreeByRoleId(Long roleId)
        throws Exception;

    ResultInfo<?> update(UpdatePermissionRequest request)
        throws Exception;

    ResultInfo<List<TreeView>> findAllAsTreeView(String appId)
        throws Exception;


    /**
     * 根据用户id和应用查询列表
     *
     * @author LuoFuMin
     * @data 2018/7/14
     */
    ResultInfo<?> findPermission(FindPermissionRequest findPermissionRequest);
}
