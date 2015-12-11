package com.step.controller;

import com.step.entity.User;
import com.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Collection;

/**
 * Created by Виктор on 10.12.2015.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String editUser(@RequestBody User user) {

        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        auth.setLastname(user.getLastname());
        auth.setName(user.getName());
        auth.setGroup(user.getGroup());

        userService.save(auth);
        return "Изменения сохранены";
    }
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Collection<User> findUser(@RequestParam("name") String name) {

        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUsers(name,auth.getId());
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadPhoto(@RequestParam("photo")File photo) {

        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Изображение загружено";
    }
}
