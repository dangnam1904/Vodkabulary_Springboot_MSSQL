package com.project.Vodkabulary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Vodkabulary.models.User;
import com.project.Vodkabulary.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminHomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/admin")
    public String getAdminHome(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = session.getAttribute("user").toString();
        User user = userService.getUserById(Long.valueOf(userId));
        if (user.getRoles().getRole_ID() == 3) {
            return "redirect:/";
        }
        return "admin/admin";
    }
}
