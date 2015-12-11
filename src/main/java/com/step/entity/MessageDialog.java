package com.step.entity;

import com.step.entity.Message;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Виктор on 11.12.2015.
 */
public class MessageDialog  {
    public MessageDialog() {
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

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    private String text;
    private String name;
    private Date dateMsg;
    private long ownerId;
    private long senderId;

    public MessageDialog(String name,String text1, long ownerId1, long senderId1) {

        this.name=name;
        text = text1;
        ownerId = ownerId1;
        senderId = senderId1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateMsg() {
        return dateMsg;
    }

    public void setDateMsg(Date dateMsg) {
        this.dateMsg = dateMsg;
    }
}
