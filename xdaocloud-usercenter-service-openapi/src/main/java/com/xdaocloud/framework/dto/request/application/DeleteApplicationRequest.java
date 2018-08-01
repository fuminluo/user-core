package com.xdaocloud.framework.dto.request.application;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class DeleteApplicationRequest implements Serializable{

    private static final long serialVersionUID = -7954049505400331431L;

    @NotNull(message = "应用ids不能为空")
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
