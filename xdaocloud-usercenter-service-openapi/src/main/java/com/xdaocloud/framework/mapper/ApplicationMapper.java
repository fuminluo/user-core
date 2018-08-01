package com.xdaocloud.framework.mapper;


import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageRequest;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.framework.dto.request.application.SaveApplicationRequest;
import com.xdaocloud.framework.dto.request.application.SearchApplicationRequest;
import com.xdaocloud.framework.dto.request.application.UpdateApplicationRequest;
import com.xdaocloud.framework.dto.response.application.FindAllApplicationResponse;
import com.xdaocloud.framework.dto.response.application.FindApplicationByUserIdRequest;
import com.xdaocloud.framework.dto.response.user.FindAllUserResponse;
import com.xdaocloud.framework.model.Application;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApplicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Application record);

    int insertSelective(SaveApplicationRequest record);

    Application selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    /**
     * 查询所有有效应用
     * @param pageRequest 分页信息
     */
    @Select("select a.id,a.code,a.name,a.remark from t_application as a where a.deleted = false")
    List<FindAllApplicationResponse> findAll(PageRequest pageRequest);

    /**
     * 搜索应用列表
     */
    List<FindAllApplicationResponse> search(SearchApplicationRequest searchRequest);

    /**
     * 查询用户所拥有的应用
     * @param userId 用户id
     * return FindApplicationByUserIdRequest
     */
    /*@Select("select a.id,a.code,a.name from t_application as a where a.deleted = false and a.id in(select au.application_id from t_application_user as au where au.user_id = #{userId})")*/
    List<FindApplicationByUserIdRequest> findByUserId(@Param("userId") Long userId);

    /**
     * 查询应用所拥有的用户
     * @param applicationId 用户id
     */
    @Select("select u.id,u.name,u.username,u.avatar,u.sex,u.birthday,u.phone,u.email,u.last_login_time from t_user as u where u.deleted = false and u.id in " +
            "(select au.user_id from t_application_user as au where au.application_id = #{applicationId})")
    List<FindAllUserResponse> findUserByApplicationId(@Param("applicationId") Long applicationId);

    /**
     * 根据code查询应用
     * @param code 应用编码
     */
    @Select("select count(0) from t_application where deleted = false and code = #{code}")
    int findByCodeCount(@Param("code")String code);

    /**
    *批量删除应用（逻辑删除）
     * @param ids  应用id数组
     */
    int deleteApplication(@Param("ids")List<Long> ids);

    /**
     * 修改应用
     */
    int updateApplicationById(UpdateApplicationRequest updateRequest);

    /**
     * 添加用户和应用关系
     *  @param userId 用户id
     *  @param applicationId 应用id
     */
    @Insert(" insert into t_application_user (user_id, application_id) values (#{userId}, #{applicationId})")
    int bindingUser(@Param("userId") Long userId,@Param("applicationId") Long applicationId);

    /**
     * 查询是否存在用户与应用关系
     *  @param userId 用户id
     *  @param applicationId 应用id
     */
    @Select("select count(0) from t_application_user where user_id = #{userId} and application_id = #{applicationId}")
    int findByUserIdAndApplicationIdCount(@Param("userId") Long userId,@Param("applicationId") Long applicationId);

    /**
     * 查询用户所拥有的应用的id
     * @param userId 用户id
     * return application_id
     */
    @Select("select application_id from t_application_user where user_id = #{userId}")
    List<Long> findApplicationUserByUserId(@Param("userId") Long userId);

    /**
     * 解除用户与应用关系
     * @param userId 用户id
     * @param applicationIds 应用id数组
     */
    int relieveUser(@Param("userId") Long userId,@Param("applicationIds") List<Long> applicationIds);

    /**
     * 解除用户的所有应用关系
     * @param userId 用户id
     */
    @Delete("delete from t_application_user where user_id = #{userId}")
    int relieveUserAll(@Param("userId") Long userId);

    /**
     * 根据应用编码查询应用
     * @param code 应用编码
     */
    @Select("select * from t_application_user where code = #{code} and deleted = false")
    Application findApplicationByCode(@Param("code") String code);

}