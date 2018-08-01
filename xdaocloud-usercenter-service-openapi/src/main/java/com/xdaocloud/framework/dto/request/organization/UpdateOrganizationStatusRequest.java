package com.xdaocloud.framework.dto.request.organization;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import com.xdaocloud.base.common.IBaseRequest;

public class UpdateOrganizationStatusRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "组织Ids不能为空")
    private Long[] ids;

    @NotNull(message = "状态不能为空")
    @Range(message = "状态不正确(0-禁用,1-可用)", min = 0, max = 1)
    private Integer status;

    public UpdateOrganizationStatusRequest() {}

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
