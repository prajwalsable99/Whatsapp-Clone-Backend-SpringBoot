package com.prajwal.Whatsapp.repository;

import com.prajwal.Whatsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmail(String email);

    @Query("select u from User u where u.full_name LIKE %:query% ")
    public List<User> searchUser(@Param("query") String query);
}
