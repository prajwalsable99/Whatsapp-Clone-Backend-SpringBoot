package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @Override
    public User createUser(User user) throws UserException {

        String email= user.getEmail();
        String full_name=user.getFull_name();
        String password=user.getPassword();
        String profile_picture=user.getProfile_picture();

        User isUser=userRepository.findByEmail(email);
        if(isUser!=null){
            throw  new UserException("Email Already exists.");
        }

        User newuser=new User();
        newuser.setProfile_picture(profile_picture);
        newuser.setFull_name(full_name);
        newuser.setProfile_picture(profile_picture);
        newuser.setEmail(email);
        newuser.setPassword(passwordEncoder.encode(password));
        return  userRepository.save(newuser);


    }

    @Override
    public Authentication authenticate(String email, String password){
        UserDetails userDetails=customUserService.loadUserByUsername(email);
        if(userDetails==null){
            throw  new BadCredentialsException("invalid details");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());


    }
}
