package com.xdaocloud.framework.dto.request.role;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import com.xdaocloud.base.common.IBaseRequest;

public class UpdateRoleRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "角色名称不能为空")
    @Pattern(message = "角色名称必须为2~10位，可包含汉字/数字/字母", regexp = "[\u4e00-\u9fa50-9A-Za-z]{2,10}")
    private String name;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
