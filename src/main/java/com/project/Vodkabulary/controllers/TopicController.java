package com.project.Vodkabulary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Vodkabulary.models.Games;
import com.project.Vodkabulary.models.Topic;
import com.project.Vodkabulary.models.User;
import com.project.Vodkabulary.services.GameService;
import com.project.Vodkabulary.services.TopicService;
import com.project.Vodkabulary.services.UserService;
import com.project.Vodkabulary.services.WordService;
import com.project.Vodkabulary.servicesimpl.StorageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired

    private GameService gameService;

    @Autowired
    private WordService wordService;
    @Autowired
    private UserService userService;
    private StorageService storageService;

    @GetMapping("/topic")
    public ModelAndView topic(Model model, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("users/topic");
        List<Topic> listTopic = topicService.getAllTopic();
        mv.addObject("listTopic",listTopic);
        List<Games> listGame = (List<Games>) gameService.getAllGame();
        mv.addObject("listGame", listGame);
        HttpSession session = request.getSession();
        String userId = session.getAttribute("user").toString();
        User user = userService.getUserById(Long.valueOf(userId));
        model.addAttribute("user", user);
        return mv;
    }

    @GetMapping({"/admin/topic","/teach/topic"})
    public String viewHomePage(Model model,@RequestParam(name = "s", required = false) String s){
        List<Topic> topicList= topicService.getAllTopic();
        if(StringUtils.hasText(s)){
            topicList= topicService.findByNameTopicContaining(s);
        }
        model.addAttribute("topiclist",topicList);
        return "admin/topic/topic";
    }

    @GetMapping({"/admin/topic/add","/teach/topic/add"})
    public String addTopic(Model model){
        model.addAttribute("topic",new Topic());
        model.addAttribute("pageTitle","Add Topic");
        return "/admin/topic/topic_form";
    }

    @GetMapping({"/admin/topic/edit/{id}","/teach/topic/edit/{id}"})
    public String editWord(@PathVariable(name = "id") long id, Model model, RedirectAttributes ra){
        try {
            Topic topic = topicService.getTopicById(id);
            model.addAttribute("pageTitle", "Edit topic word");
            model.addAttribute("topic", topic);
            return  "admin/topic/topic_form";
        }catch (RuntimeException ex) {
            return "redirect:/admin/topic";
        }
    }
    @PostMapping({"admin/topic/save","teach/topic/save"})
    public String saveTypeWord(@ModelAttribute("topic") Topic topic, RedirectAttributes ra,
                               @RequestParam(name = "imgae",required = false) MultipartFile img){

        if(storageService.storeFile(img)==null){
            topicService.customUpdateTopic(topic.getNameTopic(),
                    topic.getDescription(), topic.getTopicID());
        }else {
            topic.setImg("../../../ImgAudio/"+storageService.storeFile(img));
            topicService.saveTopic(topic);
        }
        ra.addFlashAttribute("messege","Save success");
        return "redirect:/admin/topic";
    }

    @GetMapping({"admin/topic/delete/{id}","teach/topic/delete/{id}"})
    public String deleteWord(@PathVariable(name = "id") long id,RedirectAttributes ra){
        try{
            topicService.deleteTopicById(id);
        }catch ( RuntimeException e){
            ra.addFlashAttribute("messege","Không tìm thấy");
        }
        return "redirect:/admin/topic"; // return url
    }
}
