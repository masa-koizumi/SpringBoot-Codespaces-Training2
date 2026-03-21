package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        String uri = request.getRequestURI();

        // デバッグ用（必要ならON）
        // System.out.println("URI: " + uri);

        // ログイン不要URL
        if (uri.equals("/") || uri.equals("/login")) {
            return true;
        }

        // 未ログインならログイン画面へ
        if (user == null) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
