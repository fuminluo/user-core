package com.xdaocloud.framework.dto.response.application;

import java.io.Serializable;

public class FindApplicationByUserIdRequest implements Serializable {

    private static final long serialVersionUID = 7194452199834099087L;
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 应用编码
     */
    private String code;
    /**
     * 应用名
     */
    private String name;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 是否选中
     */
    private Boolean selected;

    public FindApplicationByUserIdRequest() {
        super();
    }

    public FindApplicationByUserIdRequest(Integer id, String code, String name, String remark, Boolean selected) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.remark = remark;
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
