package com.xdaocloud.framework.model;

import javax.persistence.Table;

/**
 * 角色
 * 
 * @author WangPengHua
 */
@Table(name = "t_role")
public class Role extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
