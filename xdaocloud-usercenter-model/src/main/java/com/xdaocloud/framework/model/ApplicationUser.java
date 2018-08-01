package com.xdaocloud.framework.model;

import java.io.Serializable;

public class ApplicationUser implements Serializable{
    private static final long serialVersionUID = -706592541726281881L;

    private Integer userId;

    private Integer applicationId;

    public ApplicationUser(Integer userId, Integer applicationId) {
        this.userId = userId;
        this.applicationId = applicationId;
    }

    public ApplicationUser() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }
}