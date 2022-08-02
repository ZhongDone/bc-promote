package com.bc.promote.module.eportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "机构信息--DTO")
public class ManageInfoDTO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父节点id")
    private Long parentId;

    @ApiModelProperty(value = "级别")
    private String level;

    @ApiModelProperty(value = "管理机构代码")
    private String manageCode;

    @ApiModelProperty(value = "管理机构名称")
    private String manageName;

    @ApiModelProperty(value = "管理机构名称")
    private String label;

    @ApiModelProperty(value = "子机构")
    private List<ManageInfoDTO> children;

    public void setManageName(String manageName) {
        this.manageName = manageName;
        this.label = manageName;
    }
}
