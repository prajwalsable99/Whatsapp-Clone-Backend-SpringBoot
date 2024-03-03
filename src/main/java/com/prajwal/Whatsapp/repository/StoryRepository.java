package com.prajwal.Whatsapp.repository;

import com.prajwal.Whatsapp.model.Story;
import com.prajwal.Whatsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story,Integer> {
    List<Story> findByUserId(Integer userId);

    @Query("SELECT s.user.id, s FROM Story s")
    List<Object[]> findStoriesGroupByUserId();
}
