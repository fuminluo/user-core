package com.xdaocloud.framework.service;

import java.util.List;

import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.request.user.*;
import com.xdaocloud.framework.dto.response.user.FindUserPermissionResponse;
import com.xdaocloud.framework.dto.response.user.UserDetailResponse;

/**
 * 用户管理业务类
 *
 * @author WangPengHua
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param request
     * @return
     * @throws Exception
     * @date 2018年1月16日
     * @author WangPengHua
     */
    ResultInfo<?> register(RegisterRequest request)
            throws Exception;

    /**
     * 用户登录
     *
     * @param request
     * @return
     * @date 2018年1月16日
     * @author WangPengHua
     */
    ResultInfo<?> login(LoginRequest request)
            throws Exception;

    /**
     * 查询用户详情
     *
     * @param id 用户Id
     * @return
     * @throws Exception
     * @date 2018年1月16日
     * @author WangPengHua
     */
    ResultInfo<UserDetailResponse> detail(Long id)
            throws Exception;

    /**
     * 修改资料
     *
     * @param request
     * @return
     * @throws Exception
     * @date 2018年1月16日
     * @author WangPengHua
     */
    ResultInfo<?> updateProfile(UpdateUserRequest request)
            throws Exception;

    /**
     * 根据原密码修改密码
     *
     * @param request
     * @return
     * @throws Exception
     * @date 2018年1月16日
     * @author WangPengHua
     */
    ResultInfo<?> updatePwd(UpdatePwdRequest request)
            throws Exception;

    /**
     * 用户列表
     *
     * @param findAllRequest
     * @return
     * @date 2018年1月24日
     * @author WangPengHua
     */
    ResultInfo<PageResponse> findAllUser(FindAllUserRequest findAllRequest);

    /**
     * 启用/禁用
     *
     * @param request
     * @return
     * @throws Exception
     * @date 2018年1月29日
     * @author WangPengHua
     */
    ResultInfo<?> updateStatus(UpdateUserStatusRequest request)
            throws Exception;

    /**
     * 退出登录
     *
     * @param id
     * @return
     * @date 2018年1月29日
     * @author WangPengHua
     */
    ResultInfo<?> logout(Long id);

    /**
     * 获取用户的权限
     *
     * @param userId
     * @return
     * @throws Exception
     * @date 2018年1月29日
     * @author WangPengHua
     */
    ResultInfo<List<FindUserPermissionResponse>> findPermissionByUserId(FindUserPermissionRequest request)
            throws Exception;

    /**
     * 获取用户的权限(树形结构)
     *
     * @param userId
     * @return
     * @throws Exception
     * @date 2018年1月29日
     * @author WangPengHua
     */
    ResultInfo<List<FindUserPermissionResponse>> findPermissionAsTreeByUserId(FindUserPermissionRequest request)
            throws Exception;

    /**
     * 逻辑删除
     *
     * @param ids
     * @return
     * @throws Exception
     * @date 2018年1月30日
     * @author WangPengHua
     */
    ResultInfo<?> deleteLogic(List<Long> ids)
            throws Exception;

    /**
     * 物理删除
     *
     * @param ids
     * @return
     * @throws Exception
     * @date 2018年1月30日
     * @author WangPengHua
     */
    ResultInfo<?> delete(List<Long> ids)
            throws Exception;

    /**
     * 重置密码
     *
     * @param request
     * @return
     * @date 2018年1月30日
     * @author WangPengHua
     */
    ResultInfo<?> resetPwd(ResetUserPwdRequest request);


    /**
     * 保存身份证信息
     *
     * @param idCardRequest
     * @return ResultInfo
     * @throws Exception
     * @date 2018年3月6日
     * @author LuoFuMin
     */
    ResultInfo<?> saveIdCard(IdCardRequest idCardRequest)
            throws Exception;

    /**
     * 保存图片地址
     *
     * @param avatar 图片地址
     * @param id     用户id
     * @return
     * @throws Exception
     */
    ResultInfo<?> avatarUpload(String avatar, Long id) throws Exception;

    /**
     * 查询用户角色
     *
     * @param id 用户id
     * @return
     */
    ResultInfo<?> getUserRoles(Long id);
}
