package com.xdaocloud.framework.dto.request.application_user;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BindingApplicationUserRequest {

    @NotNull(message = "app编码名称不能为空")
    private String appCode;

    @NotBlank(message = "userId不能为空")
    @Pattern(message = "userId必须为数字", regexp = "[0-9]")
    private Long userId;

    public String getAppCode() {
        return appCode;
    }

    public Long getUserId() {
        return userId;
    }
}
