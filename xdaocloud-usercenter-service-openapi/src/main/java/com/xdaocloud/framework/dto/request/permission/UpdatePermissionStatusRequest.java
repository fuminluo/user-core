package com.xdaocloud.framework.dto.request.permission;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import com.xdaocloud.base.common.IBaseRequest;

public class UpdatePermissionStatusRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "组织Ids不能为空")
    private List<Long> ids;

    @NotNull(message = "状态不能为空")
    @Range(message = "状态不正确(0-禁用,1-可用)", min = 0, max = 1)
    private Integer status;

    public UpdatePermissionStatusRequest() {}

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
