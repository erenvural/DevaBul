package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 26.03.2017.
 */

public class Message {

    private int id;
    private String sender;
    private String receiver;
    private String messageContent;
    private String messageDate;

    //MessageList
    public static ArrayList<Message> messageList = new ArrayList<>();

    public Message (){

    }

    public Message(int id, String sender, String receiver, String messageContent, String messageDate) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.messageContent = messageContent;
        this.messageDate=messageDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public static ArrayList<Message> getMessageList() {
        return messageList;
    }

    public static void setMessageList(ArrayList<Message> messageList) {
        Message.messageList = messageList;
    }
}

