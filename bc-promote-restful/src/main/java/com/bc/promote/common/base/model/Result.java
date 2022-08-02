package com.bc.promote.common.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

	public static final Integer ERROR = 1;

	public static final Integer SUCCESS = 0;


	@ApiModelProperty(value = "处理结果")
	private Integer code = 0;

	@ApiModelProperty(value = "返回处理消息")
	private String message = "操作成功！";

	@ApiModelProperty(value = "返回内容")
	private T content;

	private Result() {

	}

	public Result<T> success(String message) {
		this.message = message;
		this.code = SUCCESS;
		return this;
	}


	public static Result<Object> ok() {
		Result<Object> result = new Result<Object>();
		result.setCode(SUCCESS);
		result.setMessage("成功");
		return result;
	}

	public static Result<Object> ok(String msg) {
		Result<Object> result = new Result<Object>();
		result.setCode(SUCCESS);
		result.setMessage(msg);
		return result;
	}

	public static Result ok(Object data) {
		Result<Object> result = new Result<Object>();
		result.setCode(SUCCESS);
		result.setContent(data);
		return result;
	}


	public static Result<Object> error(String msg) {
		return error(ERROR, msg);
	}

	public static Result<Object> error(Integer code, String msg) {
		Result<Object> result = new Result<Object>();
		result.setCode(code);
		result.setMessage(msg);
		return result;
	}
	
}