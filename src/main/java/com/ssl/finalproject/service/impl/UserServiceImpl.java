package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.model.User;
import com.ssl.finalproject.repository.UserRepository;
import com.ssl.finalproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.PasswordAuthentication;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
     UserRepository repository;

    @Autowired
     PasswordEncoder passwordEncoder;



    @Override
    public User createUser(User user) {
        log.info("createUser");
        if(ObjectUtils.isEmpty(user))
        return null;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public Boolean findByUsernameAndPassword(String username, String password) {
        System.out.println("Username"+username);
        System.out.println("Password"+password);
        log.info("findByUsernameAndPassword");
            System.out.println("primer iff");
                if(repository.existsUserByUsername(username)){
                    System.out.println("segundo iff");
                    User user=repository.findUserByUsername(username);
                    System.out.println(user.getUsername());
                    System.out.println(user.getPassword());
                    Boolean iguales = passwordEncoder.matches(password,user.getPassword());
                    System.out.println(iguales);
                    return iguales;
              //      BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
             //       return  bcrypt.matches(password, user.getPassword());
                }

    return false;
    }



}
