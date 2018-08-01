package com.xdaocloud.framework.model;

import java.io.Serializable;
import javax.persistence.Table;

/**
 * 角色权限
 * 
 * @author WangPengHua
 */
@Table(name = "t_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;

    public RolePermission() {}

    public RolePermission(Long roleId) {
        this.roleId = roleId;
    }

    public RolePermission(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
