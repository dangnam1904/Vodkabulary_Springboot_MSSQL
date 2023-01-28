package com.project.Vodkabulary.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.Vodkabulary.models.Role;
import com.project.Vodkabulary.models.User;
import com.project.Vodkabulary.services.RoleService;
import com.project.Vodkabulary.services.UserService;
import com.project.Vodkabulary.servicesimpl.StorageService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StorageService storageService;
    @GetMapping("admin/user")
    public String viewHome(Model model,  @RequestParam(name = "s", required = false) String s,
                           @RequestParam(name = "role", required = false) Long id){
       List<User> userList = userService.getAllUser();;
       if(id!=null){
      //     userList= userService.findByRolesRole_ID(id);
           userList= userService.filterUserByRold(id);
       }
        if (StringUtils.hasText(s)) {
            userList = userService.findByUsernameContaining(s);
        }

        model.addAttribute("userList",userList);

        List<Role> roleList= roleService.getAllRole();
        model.addAttribute("listRole",roleList);
        return "admin/user/user";
    }
    @GetMapping("/admin/user/add")
    public String showNewUserForm(Model model) {
        // create model attribute to bind from data
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roleList= roleService.getAllRole();
        model.addAttribute("listRole",roleList);
        return "admin/user/user_form";
    }

    @PostMapping("admin/user/save")
    public String saveuser(@ModelAttribute("user") User user, HttpServletRequest request,
                           @RequestParam(value = "password", required = true) String password,
                           @RequestParam(value = "img", required = false) MultipartFile img
                           ){
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        user.setPassword(bcryptHashString);
        user.setLearntime(null);
        LocalDateTime dateNow= LocalDateTime.now();
        user.setCreatedate(dateNow);
        user.setUpdatetime(dateNow);
        user.setImage("../../../ImgAudio/"+storageService.storeFile(img));
//        String[] arrRole = request.getParameterValues("type-role");
//        Long idrole = Long.parseLong(arrRole[0].toString());
        user.setRoles(new Role());
        user.getRoles().setRole_ID(1L);

//        if (storageService.storeFile(img) == null){
//            userService.customUpdateUser(user.getUsername(),user.getName(),
//                    user.getPassword(),user.getDob(),user.getEmail(),
//                    user.getPoint(),id_role, user.getUpdatetime(), user.getUserID());
//        }
        userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("admin/user/edit/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        // get user from the service
        User user = userService.getUserById(id);
        List<Role> roleList= roleService.getAllRole();
        //set user a model attribute to pre-population the form
        model.addAttribute("user", user);
        model.addAttribute("listRole",roleList);
        return "admin/user/user_form";
    }

    @GetMapping("admin/user/delete/{id}")
    public String deleteuser(@PathVariable(value = "id") long id) {

        // call delete user method
        this.userService.deleteUserById(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listusers", listUsers);
        return "index";
    }
}
