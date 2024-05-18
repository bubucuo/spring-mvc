package org.example.springmvc.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gaoshaozhen
 */
@Slf4j
@Component // 这里不访问数据库，就不用@Service了
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;

    /**
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return 返回true 则通过  返回 false 则不能进入controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取访问的url
        String uri = request.getRequestURI();
        // 检查访问接口是否需要登录鉴权
        if (!uri.equals("/authDemo")) {
            return true;
        }
        log.debug("check request:{}", uri);
        String token = request.getHeader("token");
        User user = userService.findByToken(token);
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }
        return true;
    }
}
