package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.config.TokenProvider;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.repository.UserRepository;
import com.prajwal.Whatsapp.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;
    @Override
    public User findUserById(Integer userId) throws UserException {

       Optional<User> user =userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new UserException("User does not exist with id: "+userId.toString());
        }
    }

    @Override
    public User findUserProfile(String jwt) throws UserException {
        String email=tokenProvider.getEmailFromToken(jwt);

        if(email==null){
           throw  new UserException("Invalid Email id");
        }
        User user;
        user = userRepository.findByEmail(email);

        if(user==null){
            throw  new UserException("User does not exist with Email id");
        }


        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException {

        User user=findUserById(userId);
        if(req.getFull_name()!=null){
            user.setFull_name(req.getFull_name());
        }
        if(req.getProfile_picture()!=null){
            user.setProfile_picture(req.getProfile_picture());

        }
        return userRepository.save(user);


    }

    @Override
    public List<User> searchUsers(String query) {
       List<User> users=userRepository.searchUser(query);

       return users;
    }
}
