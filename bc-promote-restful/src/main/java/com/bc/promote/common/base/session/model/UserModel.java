package com.bc.promote.common.base.session.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * Token使用的 UserModel
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Token使用的 UserModel", description = "Token使用的 UserModel")
public class UserModel implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "父id")
	private Long parentId;

	@ApiModelProperty(value = "父账号")
	private String parentUserName;

	@ApiModelProperty(value = "父账户名")
	private String parentAccountName;

	@ApiModelProperty(value = "账号")
	private String userName;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "账户名")
	private String accountName;

	@ApiModelProperty(value = "角色id")
	private Long roleId;

	@ApiModelProperty(value = "角色编码")
	private String roleCode;

	@ApiModelProperty(value = "角色名称")
	private String roleName;

	@ApiModelProperty(value = "管理机构编码")
	private String manageCode;

	@ApiModelProperty(value = "管理机构名称")
	private String manageName;

	@ApiModelProperty(value = "状态；0-有效；1-失效")
	private String status;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "电话")
	private String mobeil;


}
