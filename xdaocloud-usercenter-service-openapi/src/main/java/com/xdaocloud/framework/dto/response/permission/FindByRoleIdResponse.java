package com.xdaocloud.framework.dto.response.permission;

import java.io.Serializable;

public class FindByRoleIdResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long parentId;

    private Boolean checked;

    public FindByRoleIdResponse() {
        super();
    }

    public FindByRoleIdResponse(Long id, String name, Long parentId, Boolean checked) {
        super();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
