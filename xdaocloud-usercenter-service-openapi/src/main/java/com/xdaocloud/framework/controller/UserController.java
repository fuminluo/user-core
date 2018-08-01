package com.xdaocloud.framework.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import com.xdaocloud.base.config.QiniuConfig;
import com.xdaocloud.framework.dto.request.user.*;
import com.xdaocloud.framework.qiniu.FileDTO;
import com.xdaocloud.framework.qiniu.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.xdaocloud.base.common.BaseController;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.request.role.DeleteRoleRequest;
import com.xdaocloud.framework.dto.response.user.FindUserPermissionResponse;
import com.xdaocloud.framework.dto.response.user.UserDetailResponse;
import com.xdaocloud.framework.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "用户管理API")
@RestController
public class UserController extends BaseController {

    /**
     * 七牛云储存配置
     */
    @Autowired
    private QiniuConfig qiniuConfig;

    /**
     * 用户业务类
     */
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/api/v1/user/register")
    public ResultInfo<?> register(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.register(request);
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/api/v1/user/login")
    public ResultInfo<?> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.login(request);
    }

    /**
     * 退出登录
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/api/v1/user/logout/{id}")
    public ResultInfo<?> login(@PathVariable Long id)
            throws Exception {
        return userService.logout(id);
    }

    /**
     * 查询用户角色
     */
    @ApiOperation(value = "查询用户角色", notes = "查询用户角色")
    @GetMapping("/api/v1/user/get_roles/{id}")
    public ResultInfo<?> getUserRoles(@PathVariable Long id)
            throws Exception {
        return userService.getUserRoles(id);
    }


    @ApiOperation(value = "用户详情", notes = "用户详情")
    @GetMapping("/api/auth/v1/user/{id}")
    public ResultInfo<UserDetailResponse> detail(@PathVariable Long id)
            throws Exception {
        return userService.detail(id);
    }

    /**
     * 修改资料
     */
    @ApiOperation(value = "修改用户资料", notes = "修改用户资料")
    @PostMapping("/api/auth/v1/user/profile")
    public ResultInfo<?> updateProfile(@Valid @RequestBody UpdateUserRequest request, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.updateProfile(request);
    }

    /**
     * 保存身份证信息
     */
    @ApiOperation(value = "保存身份证信息", notes = "保存身份证信息")
    @PostMapping("/api/auth/v1/user/idcard")
    public ResultInfo<?> saveIdCard(@Valid @RequestBody IdCardRequest idCardRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.saveIdCard(idCardRequest);
    }

    /**
     * 修改密码
     */
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @PostMapping("/api/auth/v1/user/pwd")
    public ResultInfo<?> resetPwd(@Valid @RequestBody UpdatePwdRequest request, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.updatePwd(request);
    }

    /**
     * 重置密码
     */
    @ApiOperation(value = "重置用户密码", notes = "重置用户密码")
    @PostMapping("/api/auth/v1/user/resetpwd")
    public ResultInfo<?> updatePwd(@Valid @RequestBody ResetUserPwdRequest request, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.resetPwd(request);
    }

    /**
     * 用户列表
     */
    @ApiOperation(value = "用户列表", notes = "获取用户列表")
    @GetMapping(value = "/api/auth/v1/user/list")
    public ResultInfo<PageResponse> findAllUser(@Valid @ModelAttribute FindAllUserRequest findAllRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.findAllUser(findAllRequest);
    }

    /**
     * 批量启用/禁用
     */
    @ApiOperation(value = "批量启用/禁用用户", notes = "批量启用/禁用用户")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/user/status")
    public ResultInfo<?> updateStatus(@Valid @RequestBody UpdateUserStatusRequest updateUserStatusRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.updateStatus(updateUserStatusRequest);
    }

    /**
     * 获取用户拥有的权限
     */
    @ApiOperation(value = "获取用户拥有的权限", notes = "获取用户拥有的权限")
    @GetMapping("/api/auth/v1/user/permission/list")
    public ResultInfo<List<FindUserPermissionResponse>> userPermissions(@Valid @ModelAttribute FindUserPermissionRequest request, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.findPermissionByUserId(request);
    }

    /**
     * 获取用户拥有的权限(树形结构)
     */
    @ApiOperation(value = "获取用户拥有的权限(树形结构)", notes = "获取用户拥有的权限(树形结构)")
    @GetMapping("/api/auth/v1/user/permission/tree")
    public ResultInfo<List<FindUserPermissionResponse>> userPermissionsAsTree(@Valid @ModelAttribute FindUserPermissionRequest request, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.findPermissionAsTreeByUserId(request);
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "批量删除用户", notes = "批量删除用户(逻辑删除)")
    @ResponseBody
    @PostMapping(value = "/api/auth/v1/user/delete")
    public ResultInfo<?> delete(@Valid @RequestBody DeleteRoleRequest deleteRequest, BindingResult bindingResult)
            throws Exception {
        parseBindingResult(bindingResult);
        return userService.deleteLogic(deleteRequest.getIds());
    }


    /**
     * 上传头像
     *
     * @param multipartFile 待上传的文件
     * @return Result 全局返回对象
     * @throws Exception 异常信息
     * @date 2018年3月7日
     * @author LuoFuMin
     */
    @ApiOperation(value = "上传头像", notes = "上传头像")
    @PostMapping(value = "/api/auth/v1/user/file/avatar/upload")
    public ResultInfo<?> avatarUpload(@RequestParam(name = "file") MultipartFile multipartFile, @RequestParam(name = "userid") Long id)
            throws Exception {
        // 上传文件
        FileDTO fileInfo = null;
        String originalName = null;
        if (multipartFile != null && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
            originalName = multipartFile.getOriginalFilename();
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String key = path + "/" + UUID.randomUUID().toString() + "." + extension;
            fileInfo = FileUtils.upload(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey(), qiniuConfig.getBucket(), key, multipartFile.getBytes());
        }
        if (fileInfo != null) {
            fileInfo.setOriginalName(originalName);
            fileInfo.setDomain(qiniuConfig.getDomain());
            //保存图片地址
            String avatar = qiniuConfig.getDomain() + fileInfo.getUrl();
            int i = userService.avatarUpload(avatar, id).getCode();
            if (i != 200) {
                return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
            return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, fileInfo);
        } else {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
    }


}
