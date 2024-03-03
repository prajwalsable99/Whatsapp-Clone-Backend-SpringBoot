package com.prajwal.Whatsapp.controller;

import com.prajwal.Whatsapp.exception.UserException;
import com.prajwal.Whatsapp.model.Story;
import com.prajwal.Whatsapp.model.User;
import com.prajwal.Whatsapp.request.StoryRequest;
import com.prajwal.Whatsapp.service.StoryService;
import com.prajwal.Whatsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Story> createStory(@RequestBody StoryRequest req, @RequestHeader("Authorization") String token ) throws UserException {

        User user=userService.findUserProfile(token);
        Story story=new Story();
        story.setStoryPic(req.getStoryPic());
        story.setUser(user);
        Story savedStory = storyService.createStory(story);

        // Return the saved story in the response
        return new ResponseEntity<>(savedStory, HttpStatus.CREATED);
    }

    @GetMapping("/mystory")
    public ResponseEntity<List<Story>> getStoriesByUserId(@RequestHeader("Authorization") String token) throws UserException {

        User user=userService.findUserProfile(token);
        List<Story> stories = storyService.getStoriesByUserId(user.getId());
        return ResponseEntity.ok(stories);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, List<Story>>> getStoriesGroupedByUserId() {
        Map<Integer, List<Story>> storiesGroupedByUserId = storyService.getStoriesGroupedByUserId();
        return ResponseEntity.ok(storiesGroupedByUserId);
    }

}
