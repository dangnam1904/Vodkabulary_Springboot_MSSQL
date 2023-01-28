package com.project.Vodkabulary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Vodkabulary.models.Questions;
import com.project.Vodkabulary.models.User;
import com.project.Vodkabulary.services.QuestionService;
import com.project.Vodkabulary.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TestingController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/test")
    public String testing(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = session.getAttribute("user").toString();
        User user = userService.getUserById(Long.valueOf(userId));
        List<Questions> questionsList= questionService.getAllQuestion();
        model.addAttribute("questionList",questionsList);
        model.addAttribute("user", user);
        return "users/test";
    }
}
