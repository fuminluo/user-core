package com.xdaocloud.framework.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import com.xdaocloud.framework.datasource.XdaoBaseMapper;
import com.xdaocloud.framework.dto.request.user.FindAllUserRequest;
import com.xdaocloud.framework.dto.response.user.FindAllUserResponse;
import com.xdaocloud.framework.dto.response.user.FindUserPermissionResponse;
import com.xdaocloud.framework.model.User;

public interface UserMapper extends XdaoBaseMapper<User> {

    @Select("select * from t_user where (username=#{username} or phone=#{username} or email=#{username})")
    User login(String username);

    List<FindAllUserResponse> findAllUser(FindAllUserRequest findAllRequest);

    List<FindUserPermissionResponse> findPermissionByUserId(Map<String, Object> map);

    /**
     * 获取用户接受
     *
     * @param id 用户id
     * @return list
     */
    List<String> getUserRoles(Long id);
}
