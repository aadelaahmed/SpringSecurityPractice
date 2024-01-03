package com.example.springsecuritypractice.security.filters;

import com.example.springsecuritypractice.security.authentication.CustomAuthentication;
import com.example.springsecuritypractice.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@AllArgsConstructor
public class CustomAuthenticationFiler extends OncePerRequestFilter {
    CustomAuthenticationManager customAuthenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("here we are in auth filter");
        var key = request.getHeader("key");
        CustomAuthentication customAuthentication = (CustomAuthentication) customAuthenticationManager.authenticate(new CustomAuthentication(false,key));
        if (customAuthentication.isAuthenticated()){
            customAuthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(customAuthentication);
            filterChain.doFilter(request,response);
        }
        else
            throw new RuntimeException("can't authenticated");
    }
}
