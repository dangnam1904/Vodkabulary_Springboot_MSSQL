package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.models.ChangePwdDTO;
import com.project.models.User;
import com.project.services.UserService;
import com.project.servicesimpl.StorageService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private StorageService storageService;
    @GetMapping("/profilepage")
    public String showProfile(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = session.getAttribute("user").toString();
        User user = userService.getUserById(Long.valueOf(userId));
        model.addAttribute("user",user);
        return "users/profilepage";
    }

    @GetMapping("/editinfo")
    public String changeInfo(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = session.getAttribute("user").toString();
        User user = userService.getUserById(Long.valueOf(userId));
        model.addAttribute("user",user);
        return "users/editinfo";
    }

    @PostMapping("/editprofile")
    public String editProfile(@ModelAttribute("user") User user,
                              @RequestParam(value = "image_ava",required = false)MultipartFile multipartFile,
                              HttpServletRequest request){
        User user1 = userService.getUserById(user.getUserID());

        user1.setAvatar("../../ImgAudio/"+storageService.storeFile(multipartFile));


        if (user1.getName().equals(user.getName())) {
            user1.setName(user.getName());
        }
        if (user1.getEmail().equals(user.getEmail())) {
            user1.setName(user.getName());
        }
        user1.setDob(user.getDob());
        userService.saveUser(user1);
        return "redirect:/profilepage";
    }

    @GetMapping("/changepwd")
    public String changePwd(Model model){
        ChangePwdDTO changePwdDTO = new ChangePwdDTO();
        model.addAttribute("changePwdDTO",changePwdDTO);
        return "users/changepwd";
    }
    @PostMapping("/changepass")
    public String changePassword(@ModelAttribute("changePwdDTO") ChangePwdDTO changePwdDTO, HttpServletRequest request) {

        String password = changePwdDTO.getPassword();
        String newPassword = changePwdDTO.getNewPassword();
        String confirmPassword = changePwdDTO.getConfirmPassword();

        HttpSession session =request.getSession();
        String userId = session.getAttribute("user").toString();
        User user = userService.getUserById(Long.valueOf(userId));

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        if(!result.verified){
            System.out.println("Sai mat khau hien tai");
            return "users/changepwd";
        }

        System.out.println(newPassword+" "+confirmPassword);

        if(!newPassword.equals(confirmPassword)){
            System.out.println("Confirm password khong trung voi new password");
            return "users/changepwd";
        }
        else {
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, changePwdDTO.newPassword.toCharArray());
            changePwdDTO.setPassword(bcryptHashString);
            user.setPassword(bcryptHashString);
            userService.saveUser(user);
        }

        return "redirect:/profilepage";
    }
}
