package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.exception.ChatException;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.Chat;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.GroupChatRequest;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, Integer user2Id) throws UserException;

    public Chat findChatById(Integer chatId) throws ChatException;

    public List<Chat> findAllChatOfUserByUserId(Integer userId) throws UserException;



    Chat createGroup(GroupChatRequest req, User reqUser) throws UserException;

    public Chat addUserTOGroup(Integer userId, Integer chatId,User reqUser) throws UserException,ChatException;
    public Chat removeUserFromGroup(Integer chatId,Integer userId ,User reqUser) throws UserException,ChatException;
    public Chat renameGroup(Integer chatId,String group_name,User reqUser) throws UserException,ChatException;
    public void deleteChat(Integer chatId,Integer userId ) throws UserException,ChatException;



}
