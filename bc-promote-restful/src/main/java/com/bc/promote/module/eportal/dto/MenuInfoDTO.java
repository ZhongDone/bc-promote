package com.bc.promote.module.eportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单信息--DTO
 */
@Data
@ApiModel(value = "菜单信息--DTO")
public class MenuInfoDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父节点id")
    private Long parentId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "菜单名")
    private String label;

    @ApiModelProperty(value = "资源名")
    private String resourceName;

    @ApiModelProperty(value = "资源图标")
    private String resourceIcon;

    @ApiModelProperty(value = "资源路径")
    private String resourcePath;

    @ApiModelProperty(value = "子菜单")
    private List<MenuInfoDTO> children;

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
        this.label = resourceName;
    }
}
