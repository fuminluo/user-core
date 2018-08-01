package com.xdaocloud.framework.dto.request.role;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.xdaocloud.base.common.IBaseRequest;

public class SaveRoleUserRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "角色Id不能为空")
    private Long roleId;

    private List<Long> userIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

}
