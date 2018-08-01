package com.xdaocloud.framework.service;

import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.framework.dto.request.application.BindingUserRequest;
import com.xdaocloud.framework.dto.request.application_user.BindingApplicationUserRequest;

public interface ApplicationUserService{

    /**
     * 用户和应用关系
     */
    ResultInfo<?> bindingApplicationUser(BindingApplicationUserRequest bindingRequest) throws Exception;
}
