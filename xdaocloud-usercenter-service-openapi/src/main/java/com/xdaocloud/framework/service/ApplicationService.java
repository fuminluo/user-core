package com.xdaocloud.framework.service;

import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageRequest;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.request.application.*;


public interface ApplicationService {
    /**
     * 查询应用列表
     */
    ResultInfo<PageResponse> findAll(PageRequest pageRequest) throws Exception;

    /**
     * 查询用户所拥有的应用
     *
     * @param userId 用户id
     */
    ResultInfo<?> findByUserId(Long userId) throws Exception;

    /**
     * 查询应用所拥有的用户
     *
     * @param applicationId 用户id
     */
    ResultInfo<?> findUserByApplicationId(Long applicationId) throws Exception;

    /**
     * 添加一个应用
     */
    ResultInfo<?> saveApplication(SaveApplicationRequest saveRequest) throws Exception;

    /**
     * 批量删除应用
     */
    ResultInfo<?> deletedApplication(DeleteApplicationRequest deleteRequest) throws Exception;

    /**
     * 修改应用
     */
    ResultInfo<?> updateApplication(UpdateApplicationRequest updateRequest) throws Exception;

    /**
     * 用户和应用关系
     */
    ResultInfo<?> bindingUser(BindingUserRequest bindingUserRequest) throws Exception;

    /**
     * 搜索应用列表
     */
    ResultInfo<PageResponse> search(SearchApplicationRequest searchRequest) throws Exception;

    /**
     * 根据应用编码查询应用
     */
    ResultInfo<?> findApplicationByCode(String code) throws Exception;


}
