package com.xdaocloud.framework.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.xdaocloud.framework.datasource.XdaoBaseMapper;
import com.xdaocloud.framework.dto.request.organization.FindAllOrganizationRequest;
import com.xdaocloud.framework.dto.response.organization.FindAllResponse;
import com.xdaocloud.framework.dto.response.organization.FindUserByOrganizationIdResponse;
import com.xdaocloud.framework.model.Organization;

@Mapper
public interface OrganizationMapper extends XdaoBaseMapper<Organization> {

    List<FindAllResponse> findAll(FindAllOrganizationRequest findAllRequest);

    List<FindUserByOrganizationIdResponse> findUserByOrganizationId(Long organizationId);
}
