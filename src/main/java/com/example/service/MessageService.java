package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.ClientErrorException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message newMessage) {
        int messageLength = newMessage.getMessageText().length();
        boolean validMessageText = (0 < messageLength) && (messageLength <= 255);

        Optional<Account> foundUser = accountRepository.findById(newMessage.getPostedBy());

        if(!validMessageText || foundUser.isEmpty()) {
            throw new ClientErrorException();
        }

        return messageRepository.save(newMessage);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(int messageId) {
        Optional<Message> foundMessage = messageRepository.findById(messageId);
        return foundMessage.isPresent() ? foundMessage.get() : null;
    }

    public boolean deleteMessage(int messageId) {
        Optional<Message> foundMessage = messageRepository.findById(messageId);
        if(foundMessage.isPresent()) {
            messageRepository.deleteById(messageId);
        }
        return foundMessage.isPresent();
    }

    public int updateMessage(int messageId, String messageText) {
        boolean messageTextValid = (0 < messageText.length()) && (messageText.length() <= 255);

        Optional<Message> foundMessage = messageRepository.findById(messageId);
        if(foundMessage.isEmpty() || !messageTextValid) {
            throw new ClientErrorException();
        }

        Message foundMessageObj = foundMessage.get();

        foundMessageObj.setMessageText(messageText);
        messageRepository.save(foundMessageObj);

        return 1;
    }

    public List<Message> getAccountMessages(int accountId) {
        return messageRepository.findAllByPostedBy(accountId);
    }
}
