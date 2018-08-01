package com.xdaocloud.framework.dto.response.permission;

import java.io.Serializable;

public class FindOneResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String appId;

    private String name;

    private String permissionKey;

    private String type;

    private String url;

    private String method;

    private FindOneResponse parent;

    private String remark;

    private Integer status;

    public FindOneResponse() {}

    public FindOneResponse(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public FindOneResponse(Long id, String appId, String name, String permissionKey, String type, String url, String method, FindOneResponse parent, String remark, Integer status) {
        super();
        this.id = id;
        this.appId = appId;
        this.name = name;
        this.permissionKey = permissionKey;
        this.type = type;
        this.url = url;
        this.method = method;
        this.parent = parent;
        this.remark = remark;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public FindOneResponse getParent() {
        return parent;
    }

    public void setParent(FindOneResponse parent) {
        this.parent = parent;
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
