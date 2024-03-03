package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.exception.ChatException;
import com.prajwal.Whatsapp.exception.MessageException;
import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.Chat;
import com.prajwal.Whatsapp.model.Message;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.repository.MessageRepository;
import com.prajwal.Whatsapp.request.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    @Override
    public Message sendMessage(SendMessageRequest sendMessageRequest) throws UserException, ChatException {
        User user=userService.findUserById(sendMessageRequest.getUserId());
        Chat chat=chatService.findChatById(sendMessageRequest.getChatId());

        Message message=new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(sendMessageRequest.getContent());
        message.setTimestamp(LocalDateTime.now());


        return messageRepository.save(message);
    }

    @Override
    public List<Message> getChatsMessages(Integer chatId,User reqUser) throws ChatException, UserException {

        Chat chat=chatService.findChatById(chatId);
        if(chat.getUsers().contains(reqUser)){
            return messageRepository.findMessagesByChatId(chatId);
        }
        throw new UserException("Not Authorized to get data");
    }


    @Override
    public Message findMessageById(Integer messageId) throws MessageException {
        Optional<Message> opt= messageRepository.findById(messageId);
        if(opt.isPresent())return opt.get();

        throw  new MessageException("message not found");
    }

    @Override
    public void deleteMessage(Integer messageId,User reqUser) throws MessageException {

        Message message=findMessageById(messageId);
        if(message.getUser().getId().equals(reqUser.getId())){
            messageRepository.delete(message);
        }
        throw new MessageException("Not Authorized to delete message");
    }
}
