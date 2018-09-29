package com.topjava.graduationproject.security;

import com.topjava.graduationproject.entities.User;
import com.topjava.graduationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    @Autowired
    UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userService.login(authentication.getPrincipal().toString(), authentication.getCredentials().toString());

        if (user != null) {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();

            authorities.add(new SimpleGrantedAuthority(user.getRole()));

            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
        } else
            return null;
    }
}