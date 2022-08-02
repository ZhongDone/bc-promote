package com.bc.promote.module.eportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author 万爷
 * @since 2022-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EportalRoleResource对象", description="角色权限表")
public class EportalRoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色id")
    @TableField("ROLE_ID")
    private Long roleId;

    @ApiModelProperty(value = "资源id")
    @TableField("RESOURCE_ID")
    private Long resourceId;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_DATE")
    private LocalDateTime createdDate;

    @ApiModelProperty(value = "创建用户")
    @TableField("CREATED_USER")
    private String createdUser;

    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @ApiModelProperty(value = "修改用户")
    @TableField("MODIFIED_USER")
    private String modifiedUser;

    @ApiModelProperty(value = "删除标志；0-未删除；1-已删除")
    @TableField("IS_DELETE")
    private String isDelete;


}
