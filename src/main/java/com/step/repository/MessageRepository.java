package com.step.repository;

import com.step.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Collection;

/**
 * Created by Виктор on 24.11.2015.
 */
public interface MessageRepository extends CrudRepository<Message,Long>{
    @Override
    Message save(Message s);
    @Override
    Message findOne(Long id);
    @Override
    Collection<Message> findAll();
    @Override
    void delete(Long id);

    @Query(value = "SELECT * FROM (SELECT * FROM message WHERE owner_id = :owner order by message_id desc) m group by m.owner_id,m.sender_id",
            nativeQuery = true)
    Collection<Message> findAllUserMessage(@Param(value = "owner") long ownerId);

    @Query(value = "SELECT * \n" +
            "FROM message \n" +
            "WHERE owner_id = :owner and sender_id = :sender or owner_id = :sender and sender_id = :owner",
            nativeQuery = true)
    Collection<Message> getUserMessageBySender(@Param(value = "owner")long id, @Param(value = "sender")long sender);
}
