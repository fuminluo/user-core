package com.xdaocloud.framework.dto.request.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.xdaocloud.base.common.IBaseRequest;

public class DeleteUserRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户Ids不能为空")
    private Long[] ids;

    public DeleteUserRequest() {}

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
