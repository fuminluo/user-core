package com.xdaocloud.framework.dto.request.application;

import com.xdaocloud.base.page.PageRequest;


public class SearchApplicationRequest extends PageRequest {
    private static final long serialVersionUID = -1026722756306997703L;
    /**
     * 主键ID
     */
    private Long id;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
