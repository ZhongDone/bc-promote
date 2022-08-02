package com.bc.promote.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

	/***
	 * 权限字符串  使用逗号隔开  如  nologin,bpmquery
	 * @return
	 */
	String authorize() default "";

}
