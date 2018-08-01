package com.xdaocloud.framework.controller;

import java.util.List;
import javax.validation.Valid;
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
import com.xdaocloud.framework.dto.request.role.DeleteRoleRequest;
import com.xdaocloud.framework.dto.request.role.FindAllRoleRequest;
import com.xdaocloud.framework.dto.request.role.SaveRequest;
import com.xdaocloud.framework.dto.request.role.SaveRolePermissionRequest;
import com.xdaocloud.framework.dto.request.role.SaveRoleUserRequest;
import com.xdaocloud.framework.dto.request.role.UpdateRoleRequest;
import com.xdaocloud.framework.dto.request.role.UpdateRoleStatusRequest;
import com.xdaocloud.framework.dto.response.role.FindOneResponse;
import com.xdaocloud.framework.dto.response.role.FindUserByRoleIdResponse;
import com.xdaocloud.framework.service.PermissionService;
import com.xdaocloud.framework.service.RoleService;
import com.xdaocloud.framework.tree.TreeView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "角色管理API")
@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询列表
     */
    @ApiOperation(value = "角色列表", notes = "获取系统角色列表")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/role/list")
    public ResultInfo<PageResponse> findAll(@Valid @ModelAttribute FindAllRoleRequest findAllRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return roleService.findAll(findAllRequest);
    }

    /**
     * 根据角色Id查询权限(返回结果包含Check状态)
     */
    @ApiOperation(value = "根据角色Id查询权限", notes = "返回树形结构，包含Check状态")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/role/permission/tree")
    public ResultInfo<List<TreeView>> findCheckedAsTreeByRoleId(@RequestParam Long roleId)
        throws Exception {
        return permissionService.findCheckedAsTreeByRoleId(roleId);
    }

    /**
     * 根据角色Id查询用户(返回结果包含Selected状态)
     */
    @ApiOperation(value = "根据角色Id查询用户", notes = "返回结果包含Selected状态")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/role/user/list")
    public ResultInfo<List<FindUserByRoleIdResponse>> findUserByRoleId(@RequestParam Long roleId)
        throws Exception {
        return roleService.findUserByRoleId(roleId);
    }

    /**
     * 查询详情
     */
    @ApiOperation(value = "根据角色Id查询角色详情", notes = "根据角色Id查询角色详情")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/role/detail/{id}")
    public ResultInfo<FindOneResponse> findById(@PathVariable Long id)
        throws Exception {
        return roleService.findById(id);
    }

    /**
     * 添加一个角色
     */
    @ApiOperation(value = "添加一个角色", notes = "添加一个角色")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/role/save")
    public ResultInfo<?> save(@Valid @RequestBody SaveRequest saveRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return roleService.save(saveRequest);
    }

    /**
     * 修改一个角色
     */
    @ApiOperation(value = "修改一个角色", notes = "修改一个角色")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/role/update")
    public ResultInfo<?> update(@Valid @RequestBody UpdateRoleRequest updateRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return roleService.update(updateRequest);
    }

    /**
     * 保存角色权限关系
     */
    @ApiOperation(value = "保存角色权限关系", notes = "保存角色权限关系")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/role/permission/save")
    public ResultInfo<?> savePermission(@Valid @RequestBody SaveRolePermissionRequest savePermissionRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return roleService.savePermission(savePermissionRequest);
    }

    /**
     * 保存角色用户关系
     */
    @ApiOperation(value = "保存用户权限关系", notes = "保存用户权限关系")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/role/user/save")
    public ResultInfo<?> saveUser(@Valid @RequestBody SaveRoleUserRequest saveUserRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return roleService.saveUser(saveUserRequest);
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "批量删除角色", notes = "批量删除角色(逻辑删除)")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/role/delete")
    public ResultInfo<?> delete(@Valid @RequestBody DeleteRoleRequest deleteRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return roleService.deleteLogic(deleteRequest.getIds());
    }

    /**
     * 批量启用/禁用
     */
    @ApiOperation(value = "批量启用/禁用角色", notes = "批量启用/禁用角色")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/role/status")
    public ResultInfo<?> updateStatus(@Valid @RequestBody UpdateRoleStatusRequest updateStatusRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return roleService.updateStatus(updateStatusRequest);
    }
}
