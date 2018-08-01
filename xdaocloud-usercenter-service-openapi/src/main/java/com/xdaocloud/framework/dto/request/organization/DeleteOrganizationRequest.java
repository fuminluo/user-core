package com.xdaocloud.framework.dto.request.organization;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.xdaocloud.base.common.IBaseRequest;

public class DeleteOrganizationRequest implements IBaseRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "组织Ids不能为空")
    private List<Long> ids;

    public DeleteOrganizationRequest() {}

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

}
