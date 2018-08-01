package com.xdaocloud.framework.dto.request.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import com.xdaocloud.base.common.IBaseRequest;

public class ResetUserPwdRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户Ids不能为空")
    private Long[] ids;

    @NotBlank(message = "密码不能为空")
    private String password;

    public ResetUserPwdRequest() {}

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
