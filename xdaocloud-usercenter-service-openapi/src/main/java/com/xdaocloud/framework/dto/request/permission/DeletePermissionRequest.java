package com.xdaocloud.framework.dto.request.permission;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.xdaocloud.base.common.IBaseRequest;

public class DeletePermissionRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户Ids不能为空")
    private List<Long> ids;

    public DeletePermissionRequest() {}

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

}
