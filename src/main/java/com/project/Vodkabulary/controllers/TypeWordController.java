package com.project.Vodkabulary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Vodkabulary.models.TypeWord;
import com.project.Vodkabulary.services.TypeWordService;

@Controller
public class TypeWordController {
    @Autowired
    private TypeWordService typeWordService;

    @GetMapping({"/admin/typeword","teach/typeword"})
    public String viewList(Model model){
        List<TypeWord> typeWordList= typeWordService.getAllTypeWord();
        model.addAttribute("listType",typeWordList);
        return  "admin/typeword/typeword";
    }


    @GetMapping({"admin/typeword/add","teach/typeword/add"})
    public  String addTypeWord(Model model){
        model.addAttribute("typeWord",new TypeWord());
        model.addAttribute("pageTitle","Add Type Word");
        return "/admin/typeword/typeword_form";
    }

    @GetMapping({"/admin/typeword/edit/{id}","/teach/typeword/edit/{id}"})
    public String editWord(@PathVariable(name = "id") long id, Model model, RedirectAttributes ra){
        try {
            TypeWord typeWord = typeWordService.getTypeWordById(id);
            model.addAttribute("pageTitle", "Edit type word");
            model.addAttribute("typeWord", typeWord);
            return  "admin/typeword/typeword_form";
        }catch (RuntimeException ex) {
            return "redirect:/admin/typeword";
        }
    }
    @PostMapping({"admin/typeword/save","teach/typeword/save"})
    public String saveTypeWord(@ModelAttribute("typeWord") TypeWord typeWord, RedirectAttributes ra){
            typeWordService.saveTypeWord(typeWord);
            ra.addFlashAttribute("messege","Save success");
            return "redirect:/admin/typeword";
    }

    @GetMapping({"admin/typeword/delete/{id}","teach/typeword/delete/{id}"})
    public String deleteWord(@PathVariable(name = "id") long id,RedirectAttributes ra){
        try{
            typeWordService.deleteTypeWordById(id);
        }catch ( RuntimeException e){
            ra.addFlashAttribute("messege","Không tìm thấy");
        }
        return "redirect:/admin/typeword"; // return url
    }
}
