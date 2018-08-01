package com.xdaocloud.framework.dto.response.application;

import java.io.Serializable;

public class FindAllApplicationResponse implements Serializable {

    private static final long serialVersionUID = -533415441733311886L;
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

    public FindAllApplicationResponse() {
        super();
    }

    public FindAllApplicationResponse(Integer id, String code, String name, String remark) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.remark = remark;
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
}
