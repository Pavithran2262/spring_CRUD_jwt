package com.example.CRUD.jwt_tokens;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
   TokenGeneration tokenGeneration;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        String jwtToken = null;
        if(token!=null&&token.startsWith("Bearer")){
            jwtToken = token.substring(7,token.length());
        }
        if(!(request.getRequestURI().contains("/users/add")||request.getRequestURI().contains("/users/login"))){
            Claims claims = tokenGeneration.verifyToken(jwtToken);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
