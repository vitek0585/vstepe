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

    @Query(value = "SELECT tmp.message_id,tmp.owner_id,tmp.sender_id,text,date_msg\n" +
            "FROM \n" +
            "(SELECT message_id,IF(owner_id=:owner,:owner,sender_id) as owner_id, IF(sender_id=:owner,owner_id,sender_id) as sender_id,text,date_msg\n" +
            "        FROM message \n" +
            "        WHERE owner_id = :owner or sender_id = :owner\n" +
            "\t\tORDER by  owner_id, sender_id, date_msg DESC) as tmp\n" +
            "GROUP by tmp.sender_id,tmp.owner_id",
            nativeQuery = true)
    Collection<Message> findAllUserMessage(@Param(value = "owner") long ownerId);

    @Query(value = "SELECT * \n" +
            "FROM message \n" +
            "WHERE owner_id = :owner and sender_id = :sender or owner_id = :sender and sender_id = :owner",
            nativeQuery = true)
    Collection<Message> getUserMessageBySender(@Param(value = "owner")long id, @Param(value = "sender")long sender);
}
