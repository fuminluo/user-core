package com.xdaocloud.framework.service.impl;

import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.framework.dto.request.application_user.BindingApplicationUserRequest;
import com.xdaocloud.framework.mapper.ApplicationMapper;
import com.xdaocloud.framework.mapper.ApplicationUserMapper;
import com.xdaocloud.framework.model.Application;
import com.xdaocloud.framework.model.ApplicationUser;
import com.xdaocloud.framework.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService{

    @Autowired
    private ApplicationUserMapper applicationUserMapper;

    @Autowired
    private ApplicationMapper applicationMapper;


    @Override
    @Transactional
    public ResultInfo<?> bindingApplicationUser(BindingApplicationUserRequest bindingRequest) throws Exception {

       Application application = applicationMapper.findApplicationByCode(bindingRequest.getAppCode());
       if (application == null){
           return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
       }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setApplicationId(application.getId().intValue());
        applicationUser.setUserId(bindingRequest.getUserId().intValue());
        applicationUserMapper.insert(applicationUser);

        return  new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }
}
