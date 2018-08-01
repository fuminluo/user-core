package com.xdaocloud.framework.dto.request.role;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.xdaocloud.base.common.IBaseRequest;

public class SaveRolePermissionRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "角色Id不能为空")
    private Long roleId;

    private List<Long> permissionIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

}
