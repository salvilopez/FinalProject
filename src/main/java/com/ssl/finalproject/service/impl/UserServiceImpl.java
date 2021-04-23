package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.model.User;
import com.ssl.finalproject.repository.UserRepository;
import com.ssl.finalproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

        if(repository.existsUserByEmail(user.getEmail())){
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
            return repository.save(user);

    }

    @Override
    public User editarUser(User user) {
        log.info("editarUser");
        return repository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        if(username!=null)
        return repository.findUserByEmail(username);

        return null;
    }

    @Override
    public Boolean findByEmailAndPassword(String email, String password) {
        log.info("findByUsernameAndPassword");
                if(repository.existsUserByEmail(email)){
                    User user=repository.findUserByEmail(email);
                    return passwordEncoder.matches(password,user.getPassword());
                }
        return false;
    }



}
