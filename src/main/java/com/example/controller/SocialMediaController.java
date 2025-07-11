package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register")
    public Account createAccount(@RequestBody Account newAccount) {
        return accountService.createAccount(newAccount);
    }

    @PostMapping("login")
    public Account login(@RequestBody Account account) {
        return accountService.login(account);
    }

    @PostMapping("messages")
    public Message createMessage(@RequestBody Message newMessage) {
        return messageService.createMessage(newMessage);
    }

    @GetMapping("messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("messages/{messageId}")
    public Message getMessage(@PathVariable int messageId) {
        return messageService.getMessage(messageId);
    }

    @DeleteMapping("messages/{messageId}")
    public Integer deleteMessage(@PathVariable int messageId) {
        boolean messageDeleted = messageService.deleteMessage(messageId);
        return messageDeleted ? 1 : null;
    }

    @PatchMapping("messages/{messageId}")
    public Integer updateMessage(@RequestBody Message updatedMessage, @PathVariable int messageId) {
        return messageService.updateMessage(messageId, updatedMessage.getMessageText());
    }

    @GetMapping("accounts/{accountId}/messages")
    public List<Message> getAccountMessages(@PathVariable int accountId) {
        return messageService.getAccountMessages(accountId);
    }

}
