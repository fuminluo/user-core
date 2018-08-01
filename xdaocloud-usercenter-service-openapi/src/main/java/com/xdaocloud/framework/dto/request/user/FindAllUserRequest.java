package com.xdaocloud.framework.dto.request.user;

import com.xdaocloud.base.page.PageRequest;

public class FindAllUserRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
