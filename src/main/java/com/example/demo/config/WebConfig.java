package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/",              // ログイン画面
                        "/login",         // ログイン処理
                        "/h2-console/**", // H2コンソール
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/error"          // エラー画面
                );
    }
}
