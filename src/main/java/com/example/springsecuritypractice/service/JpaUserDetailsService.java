package com.example.springsecuritypractice.service;

import com.example.springsecuritypractice.entities.User;
import com.example.springsecuritypractice.repositories.UserRepository;
import com.example.springsecuritypractice.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    public final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("success access to the username: "+username);
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (!optionalUser.isEmpty()) {
            User user = optionalUser.get();
            System.out.println("success access to the username: "+username);
            return new SecurityUser(user);
        } else {
            throw new UsernameNotFoundException("Can't find user with this name: " + username);
        }
    }
}
