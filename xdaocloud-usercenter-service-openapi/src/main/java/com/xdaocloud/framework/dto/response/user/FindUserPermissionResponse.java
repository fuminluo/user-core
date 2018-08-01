package com.xdaocloud.framework.dto.response.user;

import java.io.Serializable;
import com.xdaocloud.framework.tree.TreeData;

public class FindUserPermissionResponse extends TreeData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    private String name;

    private String permissionKey;

    private String type;

    private String url;

    private String method;

    public FindUserPermissionResponse() {}

    public FindUserPermissionResponse(Long id, String appId, Long parentId, String name, String permissionKey, String type, String url, String method) {
        super();
        this.id = id;
        this.appId = appId;
        this.parentId = parentId;
        this.name = name;
        this.permissionKey = permissionKey;
        this.type = type;
        this.url = url;
        this.method = method;
    }

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
}
