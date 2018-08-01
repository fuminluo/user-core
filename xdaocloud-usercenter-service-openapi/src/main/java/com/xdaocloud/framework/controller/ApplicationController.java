package com.xdaocloud.framework.controller;

import com.xdaocloud.base.common.BaseController;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.base.page.PageRequest;
import com.xdaocloud.framework.dto.request.application.*;
import com.xdaocloud.framework.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(description = "应用管理API")
@RestController
public class ApplicationController extends BaseController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * 查询应用列表
     */
    @ApiOperation(value = "查询应用列表", notes = "获取所有应用列表")
    @GetMapping(value = "/api/auth/v1/application/list")
    public ResultInfo<PageResponse> findAll(PageRequest pageRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return applicationService.findAll(pageRequest);
    }

    /**
     * 搜索应用列表
     */
    @ApiOperation(value = "搜索应用列表", notes = "搜索应用列表")
    @PostMapping(value = "/api/auth/v1/application/list/search")
    public ResultInfo<PageResponse> search(@RequestBody SearchApplicationRequest searchRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return applicationService.search(searchRequest);
    }

    /**
     * 查询用户所拥有的应用
     * @param userId 用户id
     */
    @ApiOperation(value = "查询用户所拥有的应用", notes = "根据用户id查询其所拥有的应用")
    @GetMapping(value = "/api/auth/v1/application/list/user/{userId}")
    public ResultInfo<?> findByUserId(@PathVariable Long userId)
            throws Exception {
        return applicationService.findByUserId(userId);
    }

    /**
     * 查询应用所拥有的用户
     * @param applicationId 用户id
     */
    @ApiOperation(value = "查询应用所拥有的用户", notes = "根据应用id查询其所拥有的用户")
    @GetMapping(value = "/api/auth/v1/application/findUserByApplicationId/{applicationId}")
    public ResultInfo<?> findByApplicationId(@PathVariable Long applicationId)
            throws Exception {
        return applicationService.findUserByApplicationId(applicationId);
    }

    /**
     * 添加一个应用
     */
    @ApiOperation(value = "添加一个应用", notes = "添加一个应用")
    @PostMapping(value = "/api/auth/v1/application/save")
    public ResultInfo<?> saveApplicate(@Valid @RequestBody SaveApplicationRequest saveRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return applicationService.saveApplication(saveRequest);
    }

    /**
     * 批量删除应用（逻辑删除）
     */
    @ApiOperation(value = "批量删除应用", notes = "批量删除应用")
    @PostMapping(value = "/api/auth/v1/application/delete")
    public ResultInfo<?> deleteApplication(@Valid @RequestBody DeleteApplicationRequest deleteRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return applicationService.deletedApplication(deleteRequest);
    }

    /**
     * 修改应用
     */
    @ApiOperation(value = "修改应用", notes = "修改应用")
    @PostMapping(value = "/api/auth/v1/application/update")
    public ResultInfo<?> updateApplication(@Valid @RequestBody UpdateApplicationRequest updateRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);

        if (StringUtils.isBlank(updateRequest.getCode()) && StringUtils.isBlank(updateRequest.getName()) && StringUtils.isBlank(updateRequest.getRemark())) {
            return  new  ResultInfo(ResultInfo.FAILURE,ResultInfo.MSG_FAILURE,"修改内容不能为空");
        }
        return applicationService.updateApplication(updateRequest);
    }

    /**
     * 用户和应用关系
     */
    @ApiOperation(value = "用户和应用关系", notes = "用户和应用关系")
    @PostMapping(value = "/api/auth/v1/application/binding/user")
    public ResultInfo<?> bindingUser(@Valid @RequestBody BindingUserRequest bindingUserRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return applicationService.bindingUser(bindingUserRequest);
    }


}
