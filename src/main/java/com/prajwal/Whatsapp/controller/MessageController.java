package com.prajwal.Whatsapp.controller;

import com.prajwal.Whatsapp.exception.ChatException;
import com.prajwal.Whatsapp.exception.MessageException;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.Message;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.SendMessageRequest;
import com.prajwal.Whatsapp.response.ApiResponse;
import com.prajwal.Whatsapp.service.MessageService;
import com.prajwal.Whatsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest sendMessageRequest, @RequestHeader("Authorization") String token) throws ChatException, UserException {

        User user=userService.findUserProfile(token);
        sendMessageRequest.setUserId(user.getId());
        Message message=messageService.sendMessage(sendMessageRequest);

        return new ResponseEntity<>(message, HttpStatus.OK);

    }


    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> findMessageHandler(@PathVariable("chatId") Integer chatId,@RequestHeader("Authorization") String token) throws ChatException, UserException {

        User user=userService.findUserProfile(token);
        List<Message> messages=messageService.getChatsMessages(chatId,user);

        return new ResponseEntity<>(messages, HttpStatus.OK);

    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> delMessageHandler(@PathVariable("messageId") Integer messageId, @RequestHeader("Authorization") String token) throws ChatException, UserException, MessageException {

        User user=userService.findUserProfile(token);
        messageService.deleteMessage(messageId,user);

        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("message deleted");
        apiResponse.setStatus(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }



}
