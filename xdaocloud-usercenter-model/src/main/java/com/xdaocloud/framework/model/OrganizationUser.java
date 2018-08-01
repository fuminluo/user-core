package com.xdaocloud.framework.model;

import java.io.Serializable;
import javax.persistence.Table;

/**
 * 组织用户
 * 
 * @author WangPengHua
 */
@Table(name = "t_organization_user")
public class OrganizationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long organizationId;

    private Long userId;

    private Boolean isManager;

    public OrganizationUser() {}

    public OrganizationUser(Long organizationId) {
        this.organizationId = organizationId;
    }

    public OrganizationUser(Long organizationId, Long userId) {
        super();
        this.organizationId = organizationId;
        this.userId = userId;
    }

    public OrganizationUser(Long organizationId, Long userId, Boolean isManager) {
        super();
        this.organizationId = organizationId;
        this.userId = userId;
        this.isManager = isManager;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

}
