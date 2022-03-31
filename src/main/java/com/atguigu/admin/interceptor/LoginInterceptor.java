package com.atguigu.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查
 * 1.配置好拦截器拦截那些请求
 * 2.把这些配置放在容器中
 * 3.
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURI();
        log.info("preHandle拦截器的请求路径是"+requestURL);

        HttpSession session =request.getSession();

        Object loginUser=session.getAttribute("loginUser");
        if(loginUser!=null){
            //放行
            return true;
        }
        //拦截,未登录，跳转到登录页面
        request.setAttribute("msg","去登陆啊，不要给我耍小聪明");
//        response.sendRedirect("/");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    /**
     * 目标方法完成以后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle拦截器的请求路径是",modelAndView);
    }


    /**
     * 页面渲染以后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion拦截器的请求路径是",ex);
    }
}
