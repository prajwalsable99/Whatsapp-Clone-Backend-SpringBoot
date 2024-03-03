package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    public User findUserById(Integer userId) throws UserException;
    public User findUserProfile(String jwt) throws UserException;
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;
    public List<User> searchUsers(String query);


}
