package com.prajwal.Whatsapp.model;



import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String storyPic;

    @ManyToOne // Many stories can belong to one user
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Reference to the user who posted the story

    public Story() {

    }

    public Story(Integer id, String storyPic, User user) {
        this.id = id;
        this.storyPic = storyPic;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoryPic() {
        return storyPic;
    }

    public void setStoryPic(String storyPic) {
        this.storyPic = storyPic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return Objects.equals(id, story.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}