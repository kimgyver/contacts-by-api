package com.jason.userContactInfo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuideController {
    @RequestMapping("/")
    @GetMapping
    public String guideHtml() {
        return "test-guide";
    }
}
