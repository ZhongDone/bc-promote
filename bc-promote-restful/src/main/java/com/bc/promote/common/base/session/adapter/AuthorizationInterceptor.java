package com.bc.promote.common.base.session.adapter;

import com.bc.promote.common.base.annotation.Authorization;
import com.bc.promote.common.base.session.model.TokenModel;
import com.bc.promote.common.base.session.token.TokenManager;
import com.bc.promote.common.enums.ENUM_RESTFUL_COMMON_CODE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 授权拦截器
 *
 * @author 万爷
 * @date 2022/07/23
 */
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


	@Resource
	private TokenManager tokenManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
 
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			log.info("如果不是映射到方法直接通过");
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		// 无需验证直接返回
		if (null == method.getAnnotation(Authorization.class)) {
			log.info("无需验证直接返回");
			return true;
		}

		// 从header中得到token
		String token = null==request.getHeader("token")?"":request.getHeader("token").trim();
		log.info("从header中得到token["+token+"]");
		if(null==token || "".equals(token)) {
			token = null==request.getParameter("token")?"":request.getParameter("token").trim();
			log.info("从request中得到token["+token+"]");
		}
		
		if ("null".equals(token) || StringUtils.isEmpty(token)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("text/html; charset=utf-8");  
			response.getWriter().write(ENUM_RESTFUL_COMMON_CODE.NOT_AUTHORIZATION.toJsonString());
			return false;
		}

		// 获取Token信息
		TokenModel model = tokenManager.getToken(token);
		log.info("从TokenModel中得到TokenModel["+model+"]");
		if (model == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("text/html; charset=utf-8");  
			response.getWriter().write(ENUM_RESTFUL_COMMON_CODE.NOT_AUTHORIZATION.toJsonString());
			return false;
		}
		
		request.setAttribute("tokenModel", model);
		return true;

	}
}
