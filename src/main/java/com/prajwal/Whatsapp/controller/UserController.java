package com.prajwal.Whatsapp.controller;

import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.UpdateUserRequest;
import com.prajwal.Whatsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String token) throws UserException {

        User user= userService.findUserProfile(token);

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);

    }
    @GetMapping("id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer userId) throws UserException {

        User user= userService.findUserById(userId);

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);

    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestHeader("Authorization") String token, @RequestBody UpdateUserRequest req) throws UserException {
        User user=userService.findUserProfile(token);
        User newuser= userService.updateUser(user.getId(),req);
        return new ResponseEntity<>(newuser,HttpStatus.OK);
    }



}
