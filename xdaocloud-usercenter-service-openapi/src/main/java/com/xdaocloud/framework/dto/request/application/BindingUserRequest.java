package com.xdaocloud.framework.dto.request.application;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class BindingUserRequest implements Serializable {
    private static final long serialVersionUID = 5740752667887939437L;

    @NotNull(message = "applicationIds不能为空")
    private List<Long> applicationIds;

    @NotNull(message = "userId不能为空")
    private Long userId;

    public List<Long> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<Long> applicationIds) {
        this.applicationIds = applicationIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
