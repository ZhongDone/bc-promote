package com.bc.promote.common.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PageReqDTO<T> {

    @ApiModelProperty(value = "页码",example = "1")
    private Long pageNo= 1L;

    @ApiModelProperty(value = "条数",example = "10")
    private Long pageSize= 10L;

    @ApiModelProperty(value = "查询条件")
    private T entity;

}
