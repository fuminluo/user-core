package com.xdaocloud.framework.mapper;


import com.xdaocloud.framework.model.ApplicationUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationUserMapper {
    int insert(ApplicationUser record);

    int insertSelective(ApplicationUser record);
}