package com.step.controller;

import com.step.entity.Message;
import com.step.entity.User;
import com.step.service.MessageService;
import com.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @PreAuthorize(value = "")
    @RequestMapping(value = "/")
    public String index(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", user);
        return "index";
    }

//    @RequestMapping("/notes")
//    public String notes(Model model) {
//
//        model.addAttribute("notes", messageService.findAllNotes());
//        return "notes";
//    }
//
//    @RequestMapping("/notes/add")
//    public String add() {
//
//        return "add";
//    }
//
//    @RequestMapping(value = "/notes/add", method = RequestMethod.POST)
//    public String addPost(@RequestParam("title") String title, @RequestParam("text") String text) {
//        messageService.addOrUpdate(new Message(title, text, 1));
//        return "redirect:/notes";
//    }
//
//    @RequestMapping("/notes/{id}")
//    public String edit(@PathVariable("id") Long noteId, Model model) {
//        model.addAttribute("note", messageService.findNoteById(noteId));
//        return "edit";
//    }
//    @RequestMapping(value = "/notes/{id}",method = RequestMethod.POST)
//    public String editPost(@PathVariable("id") Long noteId,@RequestParam("title") String title, @RequestParam("text") String text) {
//        messageService.addOrUpdate(new Message(noteId,title, text, 1));
//        return "redirect:/notes";
//    }
}
