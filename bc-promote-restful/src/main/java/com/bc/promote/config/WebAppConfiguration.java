package com.bc.promote.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.bc.promote.common.base.session.adapter.AuthorizationInterceptor;
import com.bc.promote.common.base.session.resolver.AgentArgumentResolver;
import com.bc.promote.common.base.session.resolver.UserArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;


/**
 * web应用程序配置
 *
 * @author 万爷
 * @date 2022/07/23
 */
@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    /**
     * 注册拦截器
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        resolvers.add(new UserArgumentResolver());
        resolvers.add(new AgentArgumentResolver());
    }

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    /**
     * 方案一： 默认访问根路径跳转 doc.html页面 （swagger文档页面）
     * 方案二： 访问根路径改成跳转 index.html页面 （简化部署方案： 可以把前端打包直接放到项目的 webapp，上面的配置）
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
    }


    /**
     * 唯一ID工具类
     * @return
     */
    @Bean
    public Snowflake snowflake(){
        return IdUtil.getSnowflake(1, 1);
    }
}
