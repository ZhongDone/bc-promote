package com.bc.promote.common.base.session.resolver;

import cn.hutool.json.JSONUtil;
import com.bc.promote.common.base.annotation.CurrentAgent;
import com.bc.promote.common.base.annotation.CurrentUser;
import com.bc.promote.common.base.session.model.AgentModel;
import com.bc.promote.common.base.session.model.TokenModel;
import com.bc.promote.common.base.session.model.UserModel;
import com.bc.promote.common.constant.TOKEN_COMMON_VALUE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;


/**
 * 代理参数解析器
 *
 * @author 万爷
 * @date 2022/07/23
 */
@Slf4j
public class AgentArgumentResolver implements HandlerMethodArgumentResolver {

	
	@Override  
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果参数类型是TokenModel则支持
		Class<?> klass = parameter.getParameterType(); 
		if (klass.isAssignableFrom(UserModel.class)) {
	            Annotation[] annotations = parameter.getParameterAnnotations();  
	            for (Annotation annotation : annotations) {  
	                if (annotation.annotationType() == CurrentAgent.class) {
	                	log.info("当前访问接口需要进行参数解析！");
	                    return true;  
	                }
	            }  
	        }  
	        return false;  
	}
	

	@Override  
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	AgentModel agentModel = null;
    	try {
	    	Class<?> klass = parameter.getParameterType();  
			Object obj = webRequest.getAttribute("tokenModel", RequestAttributes.SCOPE_REQUEST);
			log.info("获取到当前的请求体中的tokenModel："+obj);
			if (klass.isAssignableFrom(UserModel.class) && null != obj ){
				
				 TokenModel tokenModel = (TokenModel)obj;
				 Object uobj = tokenModel.getProperty(TOKEN_COMMON_VALUE.AUTHORIZATION_AGENTKEY);
				 log.info("获取到tokenModel中的agentModel："+uobj);
				 if(null != uobj){
					 agentModel = JSONUtil.toBean(uobj.toString(), AgentModel.class);
					 log.info("agentModel的信息为："+agentModel);
				 }
	        }  
    	} catch (Exception e) {
			agentModel = null;
			log.info("代理人信息解析器出现异常："+e.getMessage());
			e.printStackTrace();
		}finally{
			return agentModel;
		}
	}

}
