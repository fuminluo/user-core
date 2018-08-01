package com.xdaocloud.framework.controller;

import java.util.List;
import javax.validation.Valid;

import com.xdaocloud.framework.dto.request.permission.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xdaocloud.base.common.BaseController;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.response.permission.FindOneResponse;
import com.xdaocloud.framework.service.PermissionService;
import com.xdaocloud.framework.tree.TreeView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "权限资源管理API")
@Controller
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询列表
     */
    @ApiOperation(value = "权限列表", notes = "获取系统权限列表")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/permission/list")
    public ResultInfo<PageResponse> findAll(@Valid @ModelAttribute FindAllPermissionRequest findAllRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return permissionService.findAll(findAllRequest);
    }

    /**
     * 根据用户id和应用查询列表
     *
     * @author LuoFuMin
     * @data 2018/7/14
     */
    @ApiOperation(value = "权限列表", notes = "获取系统权限列表")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/findPermission/list")
    public ResultInfo<?> findPermission(@Valid @ModelAttribute FindPermissionRequest findPermissionRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return permissionService.findPermission(findPermissionRequest);
    }

    /**
     * 查询列表
     */
    @ApiOperation(value = "权限树形列表", notes = "获取权限组织树形列表")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/permission/tree")
    public ResultInfo<List<TreeView>> findAllAsTreeView(@RequestParam(required = false) String appId)
            throws Exception {
        return permissionService.findAllAsTreeView(appId);
    }

    /**
     * 查询详情
     */
    @ApiOperation(value = "权限资源详情", notes = "权限资源详情")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/permission/detail/{id}")
    public ResultInfo<FindOneResponse> findById(@PathVariable Long id)
            throws Exception {
        return permissionService.findById(id);
    }

    /**
     * 添加一个权限
     */
    @ApiOperation(value = "添加一个权限", notes = "添加一个权限")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/permission/save")
    public ResultInfo<?> save(@Valid @RequestBody SavePermissionRequest saveRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return permissionService.save(saveRequest);
    }

    /**
     * 修改一个权限
     */
    @ApiOperation(value = "修改一个权限", notes = "修改一个权限")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/permission/update")
    public ResultInfo<?> update(@Valid @RequestBody UpdatePermissionRequest updateRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return permissionService.update(updateRequest);
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "批量删除权限", notes = "批量删除权限(逻辑删除)")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/permission/delete")
    public ResultInfo<?> delete(@Valid @RequestBody DeletePermissionRequest deleteRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return permissionService.deleteLogic(deleteRequest.getIds());
    }

    /**
     * 批量启用/禁用
     */
    @ApiOperation(value = "批量启用/禁用权限", notes = "批量启用/禁用权限")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/permission/status")
    public ResultInfo<?> updateStatus(@Valid @RequestBody UpdatePermissionStatusRequest updateStatusRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return permissionService.updateStatus(updateStatusRequest);
    }
}
