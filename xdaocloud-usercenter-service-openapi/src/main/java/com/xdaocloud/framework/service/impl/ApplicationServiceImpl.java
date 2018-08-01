package com.xdaocloud.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdaocloud.base.exception.XdaoException;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageRequest;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.base.utils.ListUtils;
import com.xdaocloud.framework.dto.request.application.*;
import com.xdaocloud.framework.dto.response.application.FindAllApplicationResponse;
import com.xdaocloud.framework.dto.response.application.FindApplicationByUserIdRequest;
import com.xdaocloud.framework.dto.response.user.FindAllUserResponse;
import com.xdaocloud.framework.mapper.ApplicationMapper;
import com.xdaocloud.framework.model.Application;
import com.xdaocloud.framework.service.ApplicationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用管理
 *
 * @author LuoFuMin
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    @Autowired
    private ApplicationMapper applicationMapper;

    /**
     * 查询应用列表
     *
     * @param pageRequest 分页信息
     */
    @Override
    public ResultInfo<PageResponse> findAll(PageRequest pageRequest) throws Exception {
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getSize());
        List<FindAllApplicationResponse> list = applicationMapper.findAll(pageRequest);
        if (com.xdaocloud.base.utils.ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        PageInfo<FindAllApplicationResponse> pageInfo = new PageInfo<FindAllApplicationResponse>(list);
        PageResponse response = new PageResponse(pageInfo);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    /**
     * 查询用户所拥有的应用
     *
     * @param userId 用户id
     */
    @Override
    public ResultInfo<?> findByUserId(Long userId) throws Exception {
        List<FindApplicationByUserIdRequest> list = applicationMapper.findByUserId(userId);
        if (com.xdaocloud.base.utils.ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, list);
    }

    /**
     * 查询应用所拥有的用户
     *
     * @param applicationId 用户id
     */
    @Override
    public ResultInfo<?> findUserByApplicationId(Long applicationId) throws Exception {
        List<FindAllUserResponse> list = applicationMapper.findUserByApplicationId(applicationId);
        if (com.xdaocloud.base.utils.ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, list);
    }

    /**
     * 添加一个应用
     */
    @Override
    @Transactional
    public ResultInfo<?> saveApplication(SaveApplicationRequest saveRequest) throws Exception {

        int findByCodeCount = applicationMapper.findByCodeCount(saveRequest.getCode());
        if (findByCodeCount > 0) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE, "应用编码已存在");
        }
        int insertSelective = applicationMapper.insertSelective(saveRequest);
        if (insertSelective == 0) {
            return new ResultInfo(ResultInfo.ERROR, ResultInfo.MSG_ERROR);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 批量删除应用（逻辑删除）
     */
    @Override
    @Transactional
    public ResultInfo<?> deletedApplication(DeleteApplicationRequest deleteRequest) throws Exception {

        if (ListUtils.isEmpty(deleteRequest.getIds())) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        int deleteApplication = applicationMapper.deleteApplication(deleteRequest.getIds());
        //如果id有错，将会回滚事物
        if (deleteApplication != deleteRequest.getIds().size()) {
            for (Long id : deleteRequest.getIds()) {
                log.error("id::" + id);
            }
            throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE, "id无效");
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 修改应用
     */
    @Override
    @Transactional
    public ResultInfo<?> updateApplication(UpdateApplicationRequest updateRequest) throws Exception {

        //果然修改应用编码，先查询编码是否已经存在
        if (updateRequest.getCode() != null) {
            int findByCodeCount = applicationMapper.findByCodeCount(updateRequest.getCode());
            if (findByCodeCount > 0) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE, "编码已存在");
            }
        }
        int updateApplication = applicationMapper.updateApplicationById(updateRequest);
        if (updateApplication == 0) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 绑定用户和应用关系
     */
    @Override
    @Transactional
    public ResultInfo<?> bindingUser(BindingUserRequest bindingUserRequest) throws Exception {

        Long userId = bindingUserRequest.getUserId();
        List<Long> applicationIds = bindingUserRequest.getApplicationIds();
        List<Long> existApplicationIds = applicationMapper.findApplicationUserByUserId(userId);
        //如果applicationIds数组为空解除用户与应用的所有关系
        if (applicationIds.isEmpty()) {
           int relieveUserAll =  applicationMapper.relieveUserAll(userId);
           if (relieveUserAll>0){
               return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
           }else{
               return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_DATA_NOT_FOUND);
           }
        }
        //添加选中的用户和应用关系
        for (Long applicationId : applicationIds) {
            int count = applicationMapper.findByUserIdAndApplicationIdCount(userId,applicationId);
            if (count == 0){
                int bindingUser = applicationMapper.bindingUser(userId, applicationId);
                if (bindingUser == 0){
                    throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
                }
            }
            existApplicationIds.remove(applicationId);
        }

        //删除未选中的应用
      if (!existApplicationIds.isEmpty()){
          applicationMapper.relieveUser(userId,existApplicationIds);
      }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 搜索应用列表
     *
     * @param searchRequest
     */
    @Override
    public ResultInfo<PageResponse> search(SearchApplicationRequest searchRequest) throws Exception {

        PageHelper.startPage(searchRequest.getPage(), searchRequest.getSize());
        List<FindAllApplicationResponse> list = applicationMapper.search(searchRequest);
        if (com.xdaocloud.base.utils.ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        PageInfo<FindAllApplicationResponse> pageInfo = new PageInfo<FindAllApplicationResponse>(list);
        PageResponse response = new PageResponse(pageInfo);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);

    }

    /**
     * 根据应用编码查询应用
     *
     * @param code 应用编码
     */
    @Override
    public ResultInfo<?> findApplicationByCode(String code) throws Exception {
       Application a = applicationMapper.findApplicationByCode(code);
        return  new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS,a.getCode());
    }

}
