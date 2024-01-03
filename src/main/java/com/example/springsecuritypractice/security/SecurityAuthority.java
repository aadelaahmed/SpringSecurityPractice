package com.example.springsecuritypractice.security;

import com.example.springsecuritypractice.entities.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
