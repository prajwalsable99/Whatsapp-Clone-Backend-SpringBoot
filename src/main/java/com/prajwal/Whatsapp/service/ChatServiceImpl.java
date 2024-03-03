package com.prajwal.Whatsapp.service;


import com.prajwal.Whatsapp.exception.ChatException;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.Chat;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.repository.ChatRepository;
import com.prajwal.Whatsapp.request.GroupChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    UserService userService;
    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, Integer user2Id) throws UserException {

        User user2=userService.findUserById(user2Id);

        Chat isChatExist=chatRepository.findSingleChatByUserIds(reqUser,user2);
        if(isChatExist!=null){
            return isChatExist;
        }
        Chat chat=new Chat();
        chat.setCreatedBy(reqUser);
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setGroup(false);


        return chatRepository.save(chat);


    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {
        Optional<Chat> chat=chatRepository.findById(chatId);
        if(chat.isPresent()){
            return  chat.get();
        }
        throw new ChatException("Chat does not found with id" + chatId);
    }

    @Override
    public List<Chat> findAllChatOfUserByUserId(Integer userId) throws UserException {

      User user=userService.findUserById(userId);
        return chatRepository.findChatByUserId(user.getId());

    }

    @Override
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
        Chat chat=new Chat();
        chat.setGroup(true);
        chat.setChat_name(req.getChat_name());
        chat.setChat_image(req.getChat_image());
        chat.setCreatedBy(reqUser);
        for(Integer ids: req.getUserIds()){
            User u=userService.findUserById(ids);
            chat.getUsers().add(u);
        }

        return  chatRepository.save(chat);
    }

    @Override
    public Chat addUserTOGroup(Integer userId, Integer chatId,User reqUser) throws UserException, ChatException {

        Chat chat=findChatById(chatId);
        User user=userService.findUserById(userId);

        if(chat.getCreatedBy().getId().equals(reqUser.getId())){
            chat.getUsers().add(user);
            return chatRepository.save(chat);
        }
        throw  new ChatException("You don't have access to add ");

    }

    @Override
    public Chat removeUserFromGroup(Integer chatId, Integer userId, User reqUser) throws UserException, ChatException {
        Chat chat=findChatById(chatId);
        User user=userService.findUserById(userId);

        if(chat.getCreatedBy().getId().equals(reqUser.getId()) || chat.getUsers().contains(user)){
            chat.getUsers().remove(user);
            return chatRepository.save(chat);
        }
        throw  new ChatException("You don't have access to remove ");

    }

    @Override
    public Chat renameGroup(Integer chatId, String group_name, User reqUser) throws UserException, ChatException {
        Chat chat=findChatById(chatId);
        if(chat.getUsers().contains(reqUser)){
            chat.setChat_name(group_name);
            return chatRepository.save(chat);
        }
        throw new ChatException("You are not part of Group");
    }

    @Override
    public void deleteChat(Integer chatId, Integer userId) throws UserException, ChatException {
        Chat chat=findChatById(chatId);
        User user=userService.findUserById(userId);
        if(chat.getUsers().contains(user)){
            chatRepository.delete(chat);
        }

    }
}
