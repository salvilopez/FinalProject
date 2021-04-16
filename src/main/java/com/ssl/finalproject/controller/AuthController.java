package com.ssl.finalproject.controller;

import com.ssl.finalproject.model.AuthenticationRequest;
import com.ssl.finalproject.model.AuthenticationResponse;
import com.ssl.finalproject.model.User;
import com.ssl.finalproject.security.JWTUtil;
import com.ssl.finalproject.service.UserService;
import com.ssl.finalproject.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final JWTUtil jwtUtil;


    public AuthController(AuthenticationManager authenticationManager, DaoAuthenticationProvider daoAuthenticationProvider, UserDetailsServiceImpl userDetailsService, UserService userService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if(userService.findByUsernameAndPassword(request.getUsername(),request.getPassword())){
                UserDetails userDetails = userDetailsService.loadUserByUsernameEncript(request.getUsername(),request.getPassword());
                String jwt = jwtUtil.generateToken(userDetails);
                return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
            }

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    @PostMapping("/registro")
    public ResponseEntity<User> registro(@RequestBody User user) {
        if(ObjectUtils.isEmpty(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else {
            return ResponseEntity.ok().body(userService.createUser(user));
        }
    }
}
