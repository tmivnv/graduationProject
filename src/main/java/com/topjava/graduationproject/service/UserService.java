package com.topjava.graduationproject.service;

import com.topjava.graduationproject.entities.User;

public interface UserService {

    User getUserById(Long id);
    Boolean setPass(String pass, Long userId);
    User login(String login, String password);
    User findByName(String name);
    User vote(User user, Long restId);
}
