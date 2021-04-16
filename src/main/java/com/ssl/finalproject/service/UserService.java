package com.ssl.finalproject.service;

import com.ssl.finalproject.model.User;

public interface UserService {

    User createUser(User user);

    Boolean findByEmailAndPassword(String email , String password);
}
