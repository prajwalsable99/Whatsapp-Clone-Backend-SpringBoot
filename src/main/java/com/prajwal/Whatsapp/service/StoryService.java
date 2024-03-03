package com.prajwal.Whatsapp.service;



import com.prajwal.Whatsapp.model.Story;

import java.util.List;
import java.util.Map;

public interface StoryService {
    public Story createStory(Story story);

    public List<Story> getStoriesByUserId(Integer userId);
    public Map<Integer, List<Story>> getStoriesGroupedByUserId();

}
