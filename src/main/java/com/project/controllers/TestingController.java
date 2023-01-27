package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.models.Questions;
import com.project.models.User;
import com.project.services.QuestionService;
import com.project.services.UserService;

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
