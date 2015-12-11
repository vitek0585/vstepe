package com.step.service;
import com.step.entity.Message;

import java.util.Collection;


public interface MessageService {
    void addOrUpdate(Message s);
    Message findMessageById(Long id);
    Collection<Message> findAllMessage();
    void deleteMessage(Long id);

    Collection<Message> getAllMessageByUser(long ownerId);

    Collection<Message> getUserMessageBySender(long id, long sender);
}
