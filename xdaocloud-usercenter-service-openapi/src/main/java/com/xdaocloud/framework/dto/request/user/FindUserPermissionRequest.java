package com.xdaocloud.framework.dto.request.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.xdaocloud.base.common.IBaseRequest;

public class FindUserPermissionRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户Id不能为空")
    private Long userId;

    // @NotBlank(message = "appId不能为空")
    private String appId;

    public FindUserPermissionRequest() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}
