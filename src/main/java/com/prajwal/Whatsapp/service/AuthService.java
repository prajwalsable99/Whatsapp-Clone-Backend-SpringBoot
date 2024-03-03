package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.User;
import org.springframework.security.core.Authentication;

public interface AuthService {

    public User createUser(User user) throws UserException;

    Authentication authenticate(String email, String password);
}
