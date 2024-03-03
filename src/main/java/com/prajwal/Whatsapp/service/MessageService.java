package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.exception.ChatException;
import com.prajwal.Whatsapp.exception.MessageException;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.Message;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.SendMessageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageService {

    public Message sendMessage(SendMessageRequest sendMessageRequest) throws UserException, ChatException;

    public List<Message> getChatsMessages(Integer chatId, User reqUser) throws ChatException, UserException;

    public Message findMessageById(Integer messageId) throws MessageException;

    public void deleteMessage(Integer messageId,User reqUser) throws MessageException;


}
