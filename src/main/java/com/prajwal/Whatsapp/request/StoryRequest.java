package com.prajwal.Whatsapp.request;

public class StoryRequest {
    public String getStoryPic() {
        return storyPic;
    }

    public StoryRequest(){

    }

    public StoryRequest(String storyPic) {
        this.storyPic = storyPic;
    }

    public void setStoryPic(String storyPic) {
        this.storyPic = storyPic;
    }

    private String storyPic;

}
