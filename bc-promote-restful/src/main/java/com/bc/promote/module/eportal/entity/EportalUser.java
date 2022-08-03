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
 * 用户表
 * </p>
 *
 * @author 万爷
 * @since 2022-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EportalUser对象", description="用户表")
public class EportalUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父id")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "账号")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "账户名")
    @TableField("ACCOUNT_NAME")
    private String accountName;

    @ApiModelProperty(value = "角色id")
    @TableField("ROLE_ID")
    private Long roleId;

    @ApiModelProperty(value = "角色编码")
    @TableField("ROLE_CODE")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    @TableField("ROLE_NAME")
    private String roleName;

    @ApiModelProperty(value = "管理机构编码")
    @TableField("MANAGE_CODE")
    private String manageCode;

    @ApiModelProperty(value = "管理机构名称")
    @TableField("MANAGE_NAME")
    private String manageName;

    @ApiModelProperty(value = "状态；0-有效；1-失效")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "电话")
    @TableField("MOBILE")
    private String mobile;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_DATE")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdDate;

    @ApiModelProperty(value = "创建用户")
    @TableField("CREATED_USER")
    private String createdUser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("MODIFIED_DATE")
    private Date modifiedDate;

    @ApiModelProperty(value = "修改用户")
    @TableField("MODIFIED_USER")
    private String modifiedUser;

    @ApiModelProperty(value = "删除标志；0-未删除；1-已删除")
    @TableField("IS_DELETE")
    private String isDelete;


}
