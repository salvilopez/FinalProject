package com.ssl.finalproject.service;

import com.ssl.finalproject.model.User;

public interface UserService {

    User createUser(User user);
    User editarUser(User user);
    User findUserByUsername(String username);
    Boolean findByEmailAndPassword(String email , String password);
    boolean existsEmail(String email);
}
