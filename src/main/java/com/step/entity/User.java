package com.step.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виктор on 26.11.2015.
 */
@Entity
@Table(name = "user")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="user_id")
    private long id;

    @Column(name="`name`")
    private String name;

    @Column(name="`last_name`")
    private String lastname;

    @Column(name="`group`")
    private String group;

    @Column(name="`photo`")
    private byte[] photo;

    @Column(name="`email`")
    private String email;

    @Column(name="`password`")
    private String password;


    @JsonBackReference
    @OneToMany(mappedBy = "owner")
    private List<Message> messagesOwner;
    @JsonBackReference
    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSender;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("User"));
        return list;
    }


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Message> getMessagesOwner() {
        return messagesOwner;
    }

    public void setMessagesOwner(Message messagesOwner) {
        this.messagesOwner.add(messagesOwner);
    }

    public List<Message> getMessagesSender() {
        return messagesSender;
    }

    public void setMessagesSender(Message messagesSender) {
        this.messagesSender.add(messagesSender);
    }
}
