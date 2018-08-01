package com.xdaocloud.framework.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.xdaocloud.framework.datasource.XdaoBaseMapper;
import com.xdaocloud.framework.dto.request.permission.FindAllPermissionRequest;
import com.xdaocloud.framework.dto.response.permission.FindAllResponse;
import com.xdaocloud.framework.dto.response.permission.FindByRoleIdResponse;
import com.xdaocloud.framework.model.Permission;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionMapper extends XdaoBaseMapper<Permission> {

    List<FindAllResponse> findAll(FindAllPermissionRequest findAllRequest);

    List<FindByRoleIdResponse> findCheckedByRoleId(Long roleId);

    /**
     * 根据角色和应用查询列表
     *
     * @author LuoFuMin
     * @data 2018/7/14
     */
    List<String> findPermission(@Param("userId") Long userId,@Param("appId") String appId);
}
