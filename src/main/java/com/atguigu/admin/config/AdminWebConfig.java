package com.atguigu.admin.config;

import com.atguigu.admin.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 1.编写一个拦截器 实现HandlerInterceptor接口
 * 2.拦截器注册到容器中 （重写WebMvcConfigurer的addInterceptors方法）
 * 3.指定拦截规则（记得放行静态资源）
 * @EnableWebMvc:全面接管
 *      1、静态资源？视图解析器？欢迎页.....全部失效
 */
//@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {


//    /**
//     * 定义静态资源行为
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        /**
//         * 访问  /aa/** 所有请求都去 classpath:/static/ 下面进行匹配
//         */
//        registry.addResourceHandler("/aa/**")
//                .addResourceLocations("classpath:/static/");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  //所有请求都被拦截包括静态资源
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**",
                        "/js/**", "/aa/**","/sql"); //放行的请求
    }


//    @Bean
//    public WebMvcRegistrations WebMvcRegistrations(){
//        return new WebMvcRegistrations(){
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return null;
//            }
//
//        };
//    }
}
