package com.prajwal.Whatsapp.repository;

import com.prajwal.Whatsapp.model.Chat;
import com.prajwal.Whatsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

    @Query("select c from Chat c join c.users u where u.id=:userid")
    public List<Chat> findChatByUserId(@Param("userid") Integer userId);


    @Query("select c from Chat c where c.isGroup=false and :user1 Member of c.users and :user2 Member of c.users ")
    public Chat findSingleChatByUserIds(@Param("user1")User user1,@Param("user2")User user2);
}
