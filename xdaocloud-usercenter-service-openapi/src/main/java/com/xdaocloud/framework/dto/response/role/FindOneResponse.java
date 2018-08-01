package com.xdaocloud.framework.dto.response.role;

import java.io.Serializable;

public class FindOneResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String permissionKey;

    private String name;

    private String remark;

    private Integer status;

    public FindOneResponse() {}

    public FindOneResponse(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public FindOneResponse(Long id, String name, String remark, Integer status) {
        super();
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.status = status;
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

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
