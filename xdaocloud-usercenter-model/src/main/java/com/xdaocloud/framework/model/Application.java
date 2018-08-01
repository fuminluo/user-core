package com.xdaocloud.framework.model;

import javax.persistence.Table;
import java.io.Serializable;

/*
*  应用
* @author LuoFuMin
* */
@Table(name = "t_application")
public class Application extends BaseModel implements Serializable{

    private static final long serialVersionUID = 8847659266658449265L;
    /**
    * 应用编码
    */
    private String code;
    /**
    * 应用名
    */
    private String name;

    public Application() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


}