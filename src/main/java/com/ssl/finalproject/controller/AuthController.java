package com.ssl.finalproject.controller;

import com.ssl.finalproject.model.AuthenticationResponse;
import com.ssl.finalproject.model.EmailUser;
import com.ssl.finalproject.model.User;
import com.ssl.finalproject.security.JWTUtil;
import com.ssl.finalproject.service.UserService;
import com.ssl.finalproject.service.impl.EnvioEmailService;
import com.ssl.finalproject.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://proyecto-ingenia-angular-eight.vercel.app", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AuthController {
    private final EnvioEmailService envioEmailService;
    private final AuthenticationManager authenticationManager;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final JWTUtil jwtUtil;


    public AuthController(EnvioEmailService envioEmailService, AuthenticationManager authenticationManager, DaoAuthenticationProvider daoAuthenticationProvider, UserDetailsServiceImpl userDetailsService, UserService userService, JWTUtil jwtUtil) {
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
            return ResponseEntity.ok().body(userCreado);
        }
    }




    @GetMapping("/email/{email}")
    public ResponseEntity<Void> passolvidada(@PathVariable  String email) throws URISyntaxException {
        if(userService.existsEmail(email)){

            User user=userService.findUserByUsername(email);

            //TODO LA Contraseña se envia cifrada por que no he encontrado descifrarla , hay un metodo que las compara
            //TODO  pero no las descifra
                    envioEmailService.sendEmail(email,"Redordatorio de Contraseña de "+email,"Le enviamos su Contraseña  \n contraseña: "+user.getPassword());
                return new  ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);


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
