package com.ssl.finalproject.controller;

import com.ssl.finalproject.model.AuthenticationRequest;
import com.ssl.finalproject.model.AuthenticationResponse;
import com.ssl.finalproject.security.JWTUtil;
import com.ssl.finalproject.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

@PostMapping("/authenticate")
public ResponseEntity<AuthenticationResponse> createToken( @RequestBody AuthenticationRequest userRequest){
try {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

    UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getUsername());

    String jwt = jwtUtil.generateToken(userDetails);
    return new ResponseEntity<>(new AuthenticationResponse(jwt),HttpStatus.OK);

}catch (BadCredentialsException e) {
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
}
}

}