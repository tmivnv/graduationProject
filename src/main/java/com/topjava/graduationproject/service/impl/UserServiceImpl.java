package com.topjava.graduationproject.service.impl;

import com.topjava.graduationproject.dto.UserLogin;
import com.topjava.graduationproject.entities.User;
import com.topjava.graduationproject.repositories.UserRepository;
import com.topjava.graduationproject.security.PasswordUtils;
import com.topjava.graduationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).get();
    }


    //Just for testing
    @Override
    public Boolean setPass(String pass, Long userId) {
        User user = getUserById(userId);
        if (user == null) return false;
        String securePassword = PasswordUtils.getSecurePassword(pass, user.getPassSalt().getBytes(Charset.forName("UTF-8")));
        user.setPassHash(securePassword);
        repository.saveAndFlush(user);
        return true;
    }

    @Override
    public User vote(User user, Long restId) {
        if (user.getAlreadyVotedFor()==-1||user.getLatestVote()==null||!user.getLatestVote().toLocalDate().equals(LocalDate.now())||user.getLatestVote().toLocalTime().isBefore(LocalTime.of(11,00)))
        {
            user.setAlreadyVotedFor(restId);
            user.setLatestVote(LocalDateTime.now());
        }
        repository.saveAndFlush(user);
        return user;
    }

    @Override
    @Transactional
    public User login(String login, String password) {
        User user = repository.findByName(login);

        return user.getPassHash().equals(PasswordUtils.getSecurePassword(password, user.getPassSalt().getBytes(Charset.forName("UTF-8")))) ? user : null;

    }

    @Override
    public User findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassHash(), getAuthority(user));
    }



    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();


            authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return authorities;
    }

}
