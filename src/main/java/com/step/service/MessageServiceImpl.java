package com.step.service;

import com.step.entity.Message;
import com.step.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void addOrUpdate(Message s) {
        messageRepository.save(s);
    }

    @Override
    public Message findMessageById(Long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Collection<Message> findAllMessage() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.delete(id);
    }

    @Override
    public Collection<Message> getAllMessageByUser(long ownerId) {
        return messageRepository.findAllUserMessage(ownerId);
    }

    @Override
    public Collection<Message> getUserMessageBySender(long id, long sender) {
        return messageRepository.getUserMessageBySender(id,sender);
    }
}
