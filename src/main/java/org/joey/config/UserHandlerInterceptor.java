package org.joey.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: JoeyShelby
 * @date: 2022-06-16 09:12
 */

/**
 * 拦截器：查看请求中的 session，判断用户是否已经登录，保护敏感资源
 */
public class UserHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            return true;
        } else { // 尚未登录，跳回登录页
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
    }

}
