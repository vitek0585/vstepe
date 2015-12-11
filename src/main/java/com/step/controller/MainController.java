package com.step.controller;

import com.step.entity.Message;

import com.step.entity.MessageDialog;
import com.step.entity.User;
import com.step.service.MessageService;
import com.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;


@Controller
public class MainController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    SimpMessagingTemplate template;

    @PreAuthorize(value = "")
    @RequestMapping(value = "/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", user);
        return "index";
    }

    @MessageMapping("/chat")
    public void sendMessage(MessageDialog message) {

        User u = userService.findUserById(message.getOwnerId());
        User uSender = userService.findUserById(message.getSenderId());

        message.setDateMsg(Calendar.getInstance().getTime());
        message.setName(u.getName());

        Message messageFromSave = new Message(message);
        messageService.addOrUpdate(messageFromSave);

        //message.getSenderId() кому шлем
        template.convertAndSendToUser(uSender.getEmail(),"/topic/message/" + message.getSenderId(), message);
        //self send
        template.convertAndSendToUser(u.getEmail(),"/topic/message/" + message.getOwnerId(), message);
    }


}



//        messageFromSave.setSenderId(message.getOwnerId());
//        messageFromSave.setOwnerId(message.getSenderId());
//        messageFromSave.setText(message.getText());
//        messageFromSave.setDateMsg(message.getDateMsg());