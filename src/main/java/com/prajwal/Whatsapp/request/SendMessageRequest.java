package com.prajwal.Whatsapp.request;

public class SendMessageRequest {

    private  Integer chatId;
    private String content;

    private Integer userId;

    public SendMessageRequest() {
    }

    public SendMessageRequest(Integer chatId, String content, Integer userId) {
        this.chatId = chatId;
        this.content = content;
        this.userId = userId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
