package com.bc.promote.common.base.session.model;

import io.swagger.annotations.ApiModel;
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
public class AgentModel implements Serializable {

	private static final long serialVersionUID = 1L;



}
