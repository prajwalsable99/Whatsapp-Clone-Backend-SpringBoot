package com.prajwal.Whatsapp.controller;

import com.prajwal.Whatsapp.exception.ChatException;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.Chat;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.GroupChatRequest;
import com.prajwal.Whatsapp.request.SingleChatRequest;
import com.prajwal.Whatsapp.response.ApiResponse;
import com.prajwal.Whatsapp.service.ChatService;
import com.prajwal.Whatsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization") String token) throws UserException {

        User requser=userService.findUserProfile(token);
        Chat chat=chatService.createChat(requser, singleChatRequest.getUserId());

        return new ResponseEntity<>(chat, HttpStatus.CREATED);

    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler(@RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String token) throws UserException {

        User requser=userService.findUserProfile(token);
        Chat chat=chatService.createGroup(groupChatRequest,requser);

        return new ResponseEntity<>(chat, HttpStatus.CREATED);

    }

    @GetMapping("/id/{chatid}")
    public ResponseEntity<Chat> FindChatByIdHandler(@PathVariable("chatid") Integer chatid ) throws UserException, ChatException {

        Chat chat=chatService.findChatById(chatid);

        return new ResponseEntity<>(chat, HttpStatus.OK);

    }

    @GetMapping("/userchats")
    public ResponseEntity<List<Chat>> FindChatByIdHandler(@RequestHeader("Authorization") String token) throws UserException, ChatException {

        User requser=userService.findUserProfile(token);

        List<Chat> chats=chatService.findAllChatOfUserByUserId(requser.getId());

        return new ResponseEntity<>(chats, HttpStatus.OK);

    }

    @PutMapping("/group/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler( @RequestHeader("Authorization") String token,@PathVariable("chatId") Integer chatId,@PathVariable("userId") Integer userId) throws UserException, ChatException {

        User requser=userService.findUserProfile(token);
        Chat chat=chatService.addUserTOGroup(userId,chatId,requser);

        return new ResponseEntity<>(chat, HttpStatus.OK);

    }

    @PutMapping("/group/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserFromGroupHandler( @RequestHeader("Authorization") String token,@PathVariable("chatId") Integer chatId,@PathVariable("userId") Integer userId) throws UserException, ChatException {

        User requser=userService.findUserProfile(token);
        Chat chat=chatService.removeUserFromGroup(chatId,userId,requser);

        return new ResponseEntity<>(chat, HttpStatus.OK);

    }


    @PutMapping("/group/{chatId}/rename/{gname}")
    public ResponseEntity<Chat> renameGroupHandler( @RequestHeader("Authorization") String token,@PathVariable("chatId") Integer chatId,@PathVariable("gname") String gname) throws UserException, ChatException {

        User requser=userService.findUserProfile(token);
        Chat chat=chatService.renameGroup(chatId,gname,requser);

        return new ResponseEntity<>(chat, HttpStatus.OK);

    }


    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteChatHandler(@RequestHeader("Authorization") String token , @PathVariable("chatId") Integer chatId) throws UserException, ChatException {
                 User requser=userService.findUserProfile(token);
        chatService.deleteChat(chatId, requser.getId());

        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("chat deleted");
        apiResponse.setStatus(true);

              return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
