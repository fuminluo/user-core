package com.xdaocloud.framework.dto.request.permission;

import com.xdaocloud.base.common.IBaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author LuoFuMin
 * @data 2018/7/14
 */
public class FindPermissionRequest implements IBaseRequest,Serializable {

    private static final long serialVersionUID = -6452318467446007103L;

    @NotNull
    private Long userId;

    @NotBlank
    private String appId;

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
