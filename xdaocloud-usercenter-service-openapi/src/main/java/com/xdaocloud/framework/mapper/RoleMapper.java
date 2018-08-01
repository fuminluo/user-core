package com.xdaocloud.framework.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.xdaocloud.framework.datasource.XdaoBaseMapper;
import com.xdaocloud.framework.dto.request.role.FindAllRoleRequest;
import com.xdaocloud.framework.dto.response.role.FindAllResponse;
import com.xdaocloud.framework.dto.response.role.FindUserByRoleIdResponse;
import com.xdaocloud.framework.model.Role;

@Mapper
public interface RoleMapper extends XdaoBaseMapper<Role> {

    List<FindAllResponse> findAll(FindAllRoleRequest findAllRequest);

    List<FindUserByRoleIdResponse> findUserByRoleId(Long roleId);
}
