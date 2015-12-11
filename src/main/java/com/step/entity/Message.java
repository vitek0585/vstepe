package com.step.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;


@Entity
@Table(name = "message")
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "sender_id")
    private long senderId;

    @ManyToOne(optional = true)
    @JoinColumn(name = "owner_id", nullable = true, updatable = false, insertable = false)
    private User owner;

    @ManyToOne(optional = true)
    @JoinColumn(name = "sender_id", nullable = true, updatable = false, insertable = false)
    private User sender;

    public Message() {
    }

    public Message(String text, long ownerId, long senderId) {
        this.text = text;
        this.ownerId = ownerId;
        this.senderId = senderId;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
    @JsonManagedReference
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonManagedReference
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }
}
