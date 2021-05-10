package com.ssl.finalproject.service.impl;


import com.ssl.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return new User("salvi","{noop}salvi", new ArrayList<>());
    }

    public UserDetails loadUserByEmailEncript(String email,String password) throws UsernameNotFoundException {

        return new User(email, "{noop}email", new ArrayList<>());
    }



}
