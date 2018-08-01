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
import com.xdaocloud.framework.dto.request.organization.DeleteOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.FindAllOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.SaveOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.SaveOrganizationUserRequest;
import com.xdaocloud.framework.dto.request.organization.UpdateOrganizationRequest;
import com.xdaocloud.framework.dto.request.organization.UpdateOrganizationStatusRequest;
import com.xdaocloud.framework.dto.response.organization.FindOneResponse;
import com.xdaocloud.framework.dto.response.organization.FindUserByOrganizationIdResponse;
import com.xdaocloud.framework.service.OrganizationService;
import com.xdaocloud.framework.tree.TreeView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "组织管理API")
@Controller
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 查询列表
     */
    @ApiOperation(value = "组织列表", notes = "获取系统组织列表")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/organization/list")
    public ResultInfo<PageResponse> findAll(@Valid @ModelAttribute FindAllOrganizationRequest findAllRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return organizationService.findAll(findAllRequest);
    }

    /**
     * 查询列表
     */
    @ApiOperation(value = "组织树形列表", notes = "获取系统组织树形列表")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/organization/tree")
    public ResultInfo<List<TreeView>> findAllAsTreeView()
        throws Exception {
        return organizationService.findAllAsTreeView();
    }

    /**
     * 查询详情
     */
    @ApiOperation(value = "组织详情", notes = "组织详情")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/organization/detail/{id}")
    public ResultInfo<FindOneResponse> findById(@PathVariable Long id)
        throws Exception {
        return organizationService.findById(id);
    }

    /**
     * 添加一个组织
     */
    @ApiOperation(value = "添加一个组织", notes = "添加一个组织")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/organization/save")
    public ResultInfo<?> save(@Valid @RequestBody SaveOrganizationRequest saveRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return organizationService.save(saveRequest);
    }

    /**
     * 修改一个组织
     */
    @ApiOperation(value = "修改一个组织", notes = "修改一个组织")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/organization/update")
    public ResultInfo<?> update(@Valid @RequestBody UpdateOrganizationRequest updateRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return organizationService.update(updateRequest);
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "批量删除组织", notes = "批量删除组织(逻辑删除)")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/organization/delete")
    public ResultInfo<?> delete(@Valid @RequestBody DeleteOrganizationRequest deleteRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return organizationService.deleteLogic(deleteRequest.getIds());
    }

    /**
     * 批量启用/禁用
     */
    @ApiOperation(value = "批量启用/禁用组织", notes = "批量启用/禁用组织")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/organization/status")
    public ResultInfo<?> updateStatus(@Valid @RequestBody UpdateOrganizationStatusRequest updateStatusRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return organizationService.updateStatus(updateStatusRequest);
    }

    /**
     * 保存组织用户关系
     */
    @ApiOperation(value = "保存组织用户关系", notes = "保存组织用户关系")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/organization/user/save")
    public ResultInfo<?> saveUser(@Valid @RequestBody SaveOrganizationUserRequest saveUserRequest, BindingResult bindingResult)
        throws Exception {
        parseBindingResult(bindingResult);
        return organizationService.saveUser(saveUserRequest);
    }

    /**
     * 根据组织Id查询用户(返回结果包含Selected状态)
     */
    @ApiOperation(value = "根据组织Id查询用户", notes = "返回结果包含Selected状态")
    @ResponseBody
    @GetMapping(value = "/api/auth/v1/organization/user/list")
    public ResultInfo<List<FindUserByOrganizationIdResponse>> findUserByOrganizationId(@RequestParam Long organizationId)
        throws Exception {
        return organizationService.findUserByOrganizationId(organizationId);
    }
}
