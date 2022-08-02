package com.bc.promote.module.eportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录返回DTO")
public class LoginResDTO {

    @ApiModelProperty(value = "主键")
    private Long userId;

    @ApiModelProperty(value = "令牌")
    private String token;

}
