package com.xdaocloud.framework.dto.request.permission;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import com.xdaocloud.base.common.IBaseRequest;

public class SavePermissionRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "权限名称不能为空")
    @Pattern(message = "权限名称必须为2~10位，可包含汉字/数字/字母", regexp = "[\u4e00-\u9fa50-9A-Za-z]{2,10}")
    private String name;

    @NotBlank(message = "权限Key不能为空")
    @Pattern(message = "权限Key只能包含为数字、字母或下划线，长度2~64位", regexp = "[0-9A-Za-z_]{2,64}")
    private String permissionKey;

    @NotBlank(message = "请求方式不能为空")
    private String method;

    @NotBlank(message = "权限类型不能为空")
    private String type;

    private String url;

    @NotBlank(message = "appId不能为空")
    private String appId;

    private Long parentId;

    private String remark;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
