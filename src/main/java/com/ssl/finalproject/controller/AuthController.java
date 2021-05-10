package com.ssl.finalproject.controller;

import com.sparkpost.exception.SparkPostException;
import com.ssl.finalproject.model.AuthenticationRequest;
import com.ssl.finalproject.model.AuthenticationResponse;
import com.ssl.finalproject.model.User;
import com.ssl.finalproject.security.JWTUtil;
import com.ssl.finalproject.service.UserService;
import com.ssl.finalproject.service.impl.EnvioEmailService;
import com.ssl.finalproject.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "https://proyecto-ingenia-angular-eight.vercel.app", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@CrossOrigin(origins = "http://localhost:8081", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AuthController {
    private final EnvioEmailService envioEmailService;
    private final AuthenticationManager authenticationManager;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Autowired
   PasswordEncoder passwordEncoder;


    public AuthController(EnvioEmailService envioEmailService, AuthenticationManager authenticationManager, DaoAuthenticationProvider daoAuthenticationProvider, UserDetailsServiceImpl userDetailsService, UserService userService, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.envioEmailService = envioEmailService;
        this.authenticationManager = authenticationManager;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {

        if (userService.findByEmailAndPassword(user.getEmail(), user.getPassword())) {
            UserDetails userDetails = userDetailsService.loadUserByEmailEncript(user.getEmail(), user.getPassword());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/registro")
    public ResponseEntity<User> registro(@RequestBody User user) {
        if (user==null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            User userCreado=userService.createUser(user);

            if(userCreado==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            envioEmailService.sendEmail(user.getEmail(),"Registro realizado correctamente  ","Registro correcto,  Logueate Con tus Credenciales para Iniciar Sesión");
            return ResponseEntity.ok().body(userCreado);
        }
    }



    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkearEmailPassOlvidada(@PathVariable  String email) throws URISyntaxException {
    if(userService.existsEmail(email)){
        return ResponseEntity.ok().body(true);
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @PostMapping("/newpass")
    public ResponseEntity<User> crearnewPass(@RequestBody User user) {
     User userR = userService.findUserByUsername(user.getEmail());
     userR.setPassword(passwordEncoder.encode(user.getPassword()));
       User resultado =userService.editarUser(userR);
      envioEmailService.sendEmail(resultado.getEmail(),"Redordatorio de Contraseña de "+resultado.getEmail(),"Le enviamos su Contraseña \n contraseña: "+user.getPassword());
        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/username/{email}")
    public ResponseEntity<User> findbyemail(@PathVariable String email) {
        if (email==null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            User user=userService.findUserByUsername(email);
            if(user==null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok().body(user);
        }
    }
    @PutMapping("/username")
    public ResponseEntity<User> editar(@RequestBody User user) {

            return ResponseEntity.ok().body(userService.editarUser(user));

    }


}
