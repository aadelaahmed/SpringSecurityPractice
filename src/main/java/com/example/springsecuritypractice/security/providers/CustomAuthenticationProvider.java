package com.example.springsecuritypractice.security.providers;

import com.example.springsecuritypractice.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Value("${custom.auth.key}")
    String authKey;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("here we are in auth provider");
        var auth = (CustomAuthentication) authentication;
        var key = auth.getKey();
        System.out.println("authkey -> "+authKey);
        System.out.println("key -> "+key);
        if (authKey.equals(key))
            return new CustomAuthentication(true,null);
        throw new RuntimeException("the keys are not been matched");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
