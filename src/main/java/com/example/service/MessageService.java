package com.example.service;

import java.util.List;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.ClientErrorException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message newMessage) throws ClientErrorException {
        int messageLength = newMessage.getMessageText().length();
        boolean validMessageText = (0 < messageLength) && (messageLength <= 255);

        Account foundUser = accountRepository.getAccountById(newMessage.getPostedBy());

        if(!validMessageText || (foundUser == null)) {
            throw new ClientErrorException();
        }

        return messageRepository.insertMessage(newMessage);
    }

    public List<Message> getAllMessages() {
        return messageRepository.getAllMessages();
    }

    public Message getMessage(int messageId) {
        return messageRepository.getMessageById(messageId);
    }

    public int deleteMessage(int messageId) {
        return messageRepository.deleteMessage(messageId);
    }

    public int updateMessage(int messageId, String messageText) throws ClientErrorException {
        int rowsUpdated = messageRepository.updatedMessageText(messageId, messageText);

        if(rowsUpdated == 0) {
            throw new ClientErrorException();
        }

        return rowsUpdated;
    }

    public List<Message> getAccountMessages(int accountId) {
        return messageRepository.getAllMessagesByAccountId(accountId);
    }
}
