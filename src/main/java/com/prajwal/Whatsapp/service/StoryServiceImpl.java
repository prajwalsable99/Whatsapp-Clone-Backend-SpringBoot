package com.prajwal.Whatsapp.service;

import com.prajwal.Whatsapp.model.Story;
import com.prajwal.Whatsapp.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoryServiceImpl implements StoryService{

    @Autowired
    private StoryRepository storyRepository;


    @Override
    public Story createStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public List<Story> getStoriesByUserId(Integer userId) {
       return storyRepository.findByUserId(userId);
    }

    public Map<Integer, List<Story>> getStoriesGroupedByUserId() {
        List<Object[]> results = storyRepository.findStoriesGroupByUserId();
        Map<Integer, List<Story>> storiesGroupedByUserId = new HashMap<>();

        for (Object[] result : results) {
            Integer userId = (Integer) result[0];
            Story story = (Story) result[1];

            // Check if user ID already exists in the map
            if (storiesGroupedByUserId.containsKey(userId)) {
                storiesGroupedByUserId.get(userId).add(story);
            } else {
                List<Story> stories = new ArrayList<>();
                stories.add(story);
                storiesGroupedByUserId.put(userId, stories);
            }
        }
        return storiesGroupedByUserId;
    }
}
