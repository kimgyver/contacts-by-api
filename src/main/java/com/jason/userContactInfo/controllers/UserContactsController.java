package com.jason.userContactInfo.controllers;

import com.jason.userContactInfo.models.UserContact;
import com.jason.userContactInfo.services.UserContactsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContactsController {
    private UserContactsService userContactsService;

    public UserContactsController(UserContactsService userContactsService) {
        this.userContactsService = userContactsService;
    }

    @RequestMapping("/getusercontacts/{id}")
    @GetMapping
    public UserContact getContactById(@PathVariable String id) {
        UserContact result = userContactsService.findById(id);
        return result;
    }

    @RequestMapping("/getusercontacts/name/{name}")
    @GetMapping
    public UserContact [] getContactsByName(@PathVariable String name) {
        UserContact[] result = userContactsService.findByName(name);
        return result;
    }
}
