package com.xdaocloud.framework.dto.request.permission;

import com.xdaocloud.base.page.PageRequest;

public class FindAllPermissionRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    private String appId;

    private String keyword;

    private Long parentId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
