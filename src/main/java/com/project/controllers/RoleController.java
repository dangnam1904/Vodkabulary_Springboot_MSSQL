package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.models.Role;
import com.project.services.RoleService;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("admin/role")
    public String getViewRole(Model model){
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("listRole",listRole);
        return  "admin/role/role";
    }
    @GetMapping("admin/role/add")
    public String addRole(Model model){
        Role role= new Role();
        model.addAttribute("role",role);
        return  "admin/role/role_form";
    }

    @GetMapping("admin/role/edit/{id}")
    public String editRole(Model model, @PathVariable(name = "id") Long id){
        Role role= roleService.getRoleById(id);
        model.addAttribute("role",role);
        return  "admin/role/role_form";
    }
    @GetMapping("admin/role/delete/{id}")
    public String deleteRole(Model model,  @PathVariable(name = "id") Long id){
        roleService.deleteRoleById(id);
        return  "redirect:/admin/role";
    }

    @PostMapping("admin/role/save")
    public  String saveRole(@ModelAttribute("role") Role role, Model model, RedirectAttributes ra){
        roleService.saveRole(role);
        ra.addFlashAttribute("messege", "Lưu thành công");
        return "redirect:/admin/role";
    }
}
