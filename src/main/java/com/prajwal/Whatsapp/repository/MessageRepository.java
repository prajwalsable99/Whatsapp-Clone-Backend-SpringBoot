package com.prajwal.Whatsapp.repository;

import com.prajwal.Whatsapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    @Query("select m from Message m join m.chat c where c.id=:chatId")
    public List<Message> findMessagesByChatId(@Param("chatId") Integer chatId);
}
