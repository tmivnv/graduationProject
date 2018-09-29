package com.topjava.graduationproject.controllers;

import com.topjava.graduationproject.dto.UserLogin;
import com.topjava.graduationproject.entities.AuthToken;
import com.topjava.graduationproject.entities.User;
import com.topjava.graduationproject.security.TokenProvider;
import com.topjava.graduationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    UserService userService;




    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<Object> login(@RequestBody UserLogin userLogin) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getName(),
                        userLogin.getPassword()
                )
        );
        if (authentication == null) return new ResponseEntity<>("Bad credentials", HttpStatus.UNAUTHORIZED);
        //
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "vote/{restId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    @ResponseBody
    ResponseEntity<Object> upVote(@PathVariable Long restId) {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ResponseEntity<>(userService.vote(user, restId), HttpStatus.OK);


    }
}
