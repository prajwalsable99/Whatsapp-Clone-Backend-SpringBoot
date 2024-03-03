package com.prajwal.Whatsapp.controller;


import com.prajwal.Whatsapp.config.TokenProvider;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.LoginRequest;
import com.prajwal.Whatsapp.response.AuthResponse;
import com.prajwal.Whatsapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

        User newuser=authService.createUser(user);

        Authentication authentication=new UsernamePasswordAuthenticationToken(newuser.getEmail(),newuser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=tokenProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setAuth(true);
        authResponse.setJwt(jwt);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);



    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest req){

            String email= req.getEmail();
            String password= req.getPassword();
            Authentication authentication=authService.authenticate(email,password);

           SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=tokenProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setAuth(true);
        authResponse.setJwt(jwt);
        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);

    }


}
