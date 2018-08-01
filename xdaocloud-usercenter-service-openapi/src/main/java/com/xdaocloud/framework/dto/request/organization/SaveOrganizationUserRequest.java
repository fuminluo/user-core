package com.xdaocloud.framework.dto.request.organization;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.xdaocloud.base.common.IBaseRequest;

public class SaveOrganizationUserRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "组织Id不能为空")
    private Long organizationId;

    private List<Long> userIds;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

}
