package com.xdaocloud.framework.model;

import java.io.Serializable;
import javax.persistence.Table;

/**
 * 角色用户
 * 
 * @author WangPengHua
 */
@Table(name = "t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long userId;

    public UserRole() {}

    public UserRole(Long roleId) {
        this.roleId = roleId;
    }

    public UserRole(Long roleId, Long userId) {
        super();
        this.roleId = roleId;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
