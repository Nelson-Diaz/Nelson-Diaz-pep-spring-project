package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    Message save(Message newMessage);

    List<Message> findAll();

    Optional<Message> findById(int messageId);

    void deleteById(int messageId);

    List<Message> findAllByPostedBy(int postedBy);
}
