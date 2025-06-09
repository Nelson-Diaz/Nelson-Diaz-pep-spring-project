package com.example.repository;

import java.util.List;

import com.example.entity.Message;

public interface MessageRepository {

    Message insertMessage(Message newMessage);

    List<Message> getAllMessages();

    Message getMessageById(int messageId);

    int deleteMessage(int messageId);

    int updatedMessageText(int messageId, String messageText);

    List<Message> getAllMessagesByAccountId(int accountId);
}
