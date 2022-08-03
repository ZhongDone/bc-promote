package com.bc.promote.module.eportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author 万爷
 * @since 2022-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EportalRole对象", description="角色表")
public class EportalRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色编码")
    @TableField("ROLE_CODE")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    @TableField("ROLE_NAME")
    private String roleName;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_DATE")
    private Date createdDate;

    @ApiModelProperty(value = "创建用户")
    @TableField("CREATED_USER")
    private String createdUser;

    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFIED_DATE")
    private Date modifiedDate;

    @ApiModelProperty(value = "修改用户")
    @TableField("MODIFIED_USER")
    private String modifiedUser;

    @ApiModelProperty(value = "删除标志；0-未删除；1-已删除")
    @TableField("IS_DELETE")
    private String isDelete;


}
