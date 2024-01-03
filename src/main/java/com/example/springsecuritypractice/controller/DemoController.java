package com.example.springsecuritypractice.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DemoController {
    AuthenticationManager authManager;
    @RequestMapping("/demo")
    public String demo(@RequestParam("username") String userName,@RequestParam("password") String password){
        var authentication = new UsernamePasswordAuthenticationToken(userName,password);
        authManager.authenticate(authentication);
        System.out.println("access demo endpoint ");
        var auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getCredentials());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.getPrincipal());
        return "Demo";
    }
    @GetMapping("admin")
    @PreAuthorize("hasAuthority('read')")
    public String helloAdmin(){
        return "Hello admin";
    }
    @GetMapping("/d")
    public String message(){
        return "test message";
    }
}
