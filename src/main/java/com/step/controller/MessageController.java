package com.step.controller;

import com.step.entity.Message;
import com.step.entity.User;
import com.step.service.MessageService;
import com.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Виктор on 10.12.2015.
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Collection<Message> getAllMsg() {

        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageService.getAllMessageByUser(auth.getId());
    }
    @RequestMapping(value = "/message",method = RequestMethod.GET)
    public Collection<Message> getMsg(@RequestParam("id") long id) {

        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return messageService.getUserMessageBySender(auth.getId(),id);
    }
}
