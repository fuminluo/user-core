package com.xdaocloud.framework.model;

import javax.persistence.Table;

/**
 * 权限资源
 * 
 * @author WangPengHua
 */
@Table(name = "t_permission")
public class Permission extends BaseModel {

    private static final long serialVersionUID = 1L;

    public static final String TYPE_PAGE = "PAGE";
    public static final String TYPE_BUTTON = "BTN";
    public static final String TYPE_ELEMENT = "ELE";
    public static final String TYPE_INTERFACE = "IMPL";

    private String appId;

    private String permissionKey;

    private String name;

    private String method;

    private String type;

    private String url;

    private Integer status;

    private Long parentId;

    private Integer sort;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
