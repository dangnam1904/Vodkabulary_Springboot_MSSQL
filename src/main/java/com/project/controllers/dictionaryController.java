package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dictionaryController {
    @GetMapping("/dictionary")
    public String viewDictionary(){
        return "web/dictionary/dictionary";
    }
}
