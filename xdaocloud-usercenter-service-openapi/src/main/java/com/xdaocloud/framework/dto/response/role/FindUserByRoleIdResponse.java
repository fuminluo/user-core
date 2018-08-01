package com.xdaocloud.framework.dto.response.role;

import java.io.Serializable;

public class FindUserByRoleIdResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Integer status;

    private Boolean selected;

    public FindUserByRoleIdResponse() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
