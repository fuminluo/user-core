package com.xdaocloud.framework.dto.request.application;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UpdateApplicationRequest implements Serializable{

    /**
     * 主键ID
     */
    @NotNull(message = "应用id不能为空")
    private Long id;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 应用编码
     */
    private String code;
    /**
     * 应用名
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
