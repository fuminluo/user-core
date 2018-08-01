package com.xdaocloud.framework.dto.request.organization;

import com.xdaocloud.base.page.PageRequest;

public class FindAllOrganizationRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    private String keyword;

    private Long parentId;

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
