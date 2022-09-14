package org.joey.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: JoeyShelby
 * @date: 2022-09-07 09:12
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    // url 对应视图
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/gradeReport").setViewName("gradeReport");
        registry.addViewController("/charts").setViewName("charts");


    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserHandlerInterceptor()).excludePathPatterns("/login","/signin","/css/*","/img/*","/js/*", "/images/*", "/fonts/*");
    }

}
