package com.project.Vodkabulary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.Vodkabulary.models.LoginDTO;
import com.project.Vodkabulary.models.RegisterDTO;
import com.project.Vodkabulary.models.Role;
import com.project.Vodkabulary.models.Topic;
import com.project.Vodkabulary.models.User;
import com.project.Vodkabulary.services.RoleService;
import com.project.Vodkabulary.services.TopicService;
import com.project.Vodkabulary.services.UserService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String homeView(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = "";
        if (session.getAttribute("user") != null) {
            userId = session.getAttribute("user").toString();
        }
        if (!userId.isEmpty()) {
            User user = userService.getUserById(Long.valueOf(userId));
            model.addAttribute("user", user);
        }
        List<User> userList = userService.getAllUser();
        model.addAttribute("listUsers", userList);
        List<Topic> topicList = topicService.getAllTopic();
        model.addAttribute("listTopics", topicList);
        return "users/index";
    }


    @GetMapping("/signin")
    public String signIn(Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("loginDTO", loginDTO);
        return "users/signin";
    }

    @PostMapping("/signin")
    public String signInForm(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpServletRequest request) {
        if (userService.getUserByUsername(loginDTO.Username) == null) {
            return "users/signin";
        }
        String Username = loginDTO.Username;
        String Password = loginDTO.Password;
        HttpSession session = request.getSession(true);
        User user = userService.getUserByUsername(Username);

        BCrypt.Result result = BCrypt.verifyer().verify(Password.toCharArray(), user.getPassword());

        if (result.verified) {
            System.out.println(loginDTO.Username+"dang nhap thanh cong");
            session.setAttribute("user", user.getUserID());
            if (user.getRoles().getRoleName().equals("admin")) {
                return "redirect:/admin";
            } else if (user.getRoles().getRoleName().equals("teacher")){
                return "redirect:/teacher";
            }
            return "redirect:/";
        } else {
            System.out.println("Sai mat khau or tai khoan");
            return "users/signin";
        }

    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute("registerDTO", registerDTO);
        return "users/signup";
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute ("registerDTO") RegisterDTO registerDTO) {
        if (userService.getUserByUsername(registerDTO.Username) != null) {
            return "redirect:/";
        }

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, registerDTO.Password.toCharArray());

        User user = new User(registerDTO.Username, bcryptHashString, registerDTO.Email);
        Role role = new Role(1, "admin");
        System.out.println(role.getRoleName());
        user.setRoles(role);
        System.out.println(user.getRoles().getRoleName()+"o day a");
        userService.saveUser(user);

        System.out.println(registerDTO.Username);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String Logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/ipa")
    public String viewIPA(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = session.getAttribute("user").toString();
        User user = userService.getUserById(Long.valueOf(userId));
        model.addAttribute("user", user);
        return "users/ipa";
    }

}
