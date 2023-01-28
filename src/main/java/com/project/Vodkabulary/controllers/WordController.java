package com.project.Vodkabulary.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Vodkabulary.models.Topic;
import com.project.Vodkabulary.models.TypeWord;
import com.project.Vodkabulary.models.Word;
import com.project.Vodkabulary.services.TopicService;
import com.project.Vodkabulary.services.TypeWordService;
import com.project.Vodkabulary.services.WordService;
import com.project.Vodkabulary.servicesimpl.StorageService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WordController {
    @Autowired
    private WordService wordService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TypeWordService typeWordService;

    @Autowired
    private StorageService storageService;

    //Show list
    @RequestMapping({"/admin/words", "/teach/words"})
    public String viewHomePage(Model model,
                               @RequestParam(name = "s", required = false) String s,
                               @RequestParam(name = "type", required = false) Long type_id,
                               @RequestParam(name = "topic", required = false) Long topic_id) {
        List<Word> wordList = null;
        long totalItem=0;
        int totalPage=0;
        int currentPage=1;
        if ((type_id != null) && (topic_id != null)) {
            wordList = wordService.findWordByTopicTopicIDAndTypeWordTypeID(topic_id, type_id);
        } else if (StringUtils.hasText(s)) {
            wordList = wordService.searchByText(s);
        } else if (type_id != null) {
            wordList = wordService.findWordByTypeWordTypeID(type_id);

        } else if (topic_id != null) {
            wordList = wordService.findWordByTopicTopicID(topic_id);
        } else {
            wordList = wordService.getAllWord();
        }
        model.addAttribute("listwords", wordList);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);

        List<Topic> topicList = topicService.getAllTopic();
        model.addAttribute("topiclist", topicList);

        List<TypeWord> typeWordList = typeWordService.getAllTypeWord();
        model.addAttribute("typeword", typeWordList);
        model.addAttribute("searchKey", s);

        return "admin/words/word";
    }

    @RequestMapping({"/admin/word","/teach/word"})
    public String viewWord(Model model,
                               @RequestParam(name = "s", required = false) String s,
                               @RequestParam(name = "type", required = false) Long type_id,
                               @RequestParam(name = "topic", required = false) Long topic_id) {
        List<Word> wordList = null;
        wordList = wordService.getAllWord();
        if (StringUtils.hasText(s)) {
            wordList = wordService.searchByText(s);
        }
        if ((type_id != null) && (topic_id != null)) {
            wordList = wordService.findWordByTopicTopicIDAndTypeWordTypeID(topic_id, type_id);
        } else if (type_id != null) {
            wordList = wordService.findWordByTypeWordTypeID(type_id);

        } else if (topic_id != null) {
            wordList = wordService.findWordByTopicTopicID(topic_id);
        }
        model.addAttribute("listwords",wordList);
        return listByPage(1,"text","asc", model);
    }

    @GetMapping({"/admin/word/page/{pageNumber}", "/teach/word/page/{pageNumber}"})
    public String listByPage(@PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir, Model model){
        List<Word> wordList = null;
        long totalItem=0;
        int totalPage=0;
       wordList= wordService.getAllWord();
        //Get Topic
        List<Topic> topicList = topicService.getAllTopic();
        model.addAttribute("topiclist", topicList);
        //Get type word
        List<TypeWord> typeWordList = typeWordService.getAllTypeWord();
        model.addAttribute("typeword", typeWordList);
        //Paddding ang sortting
        Page<Word> page= wordService.listWordOnPage(currentPage,sortField,sortDir);
       // Page<Word> page1= (Page<Word>) wordService.findWordByTopicTopicID(1L);
        List<Word> list= new ArrayList<>();
       // list= page1.getContent();
        wordList= page.getContent();
        totalItem=page.getTotalElements();
        totalPage=page.getTotalPages();
        String reverseSortDir=sortDir.equals("asc")?  "desc": "asc";
        //Add model
        model.addAttribute("listwords", wordList);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "admin/words/word";
    }

    @GetMapping({"/admin/word/addword","teach/word/addword"})
    public String viewAddWord(Model model) {
        Word word = new Word();
        model.addAttribute("word", word);
        model.addAttribute("pageTitle", "Add word");

        List<Topic> topicList = topicService.getAllTopic();
        model.addAttribute("topiclist", topicList);

        List<TypeWord> typeWordList = typeWordService.getAllTypeWord();
        model.addAttribute("typeword", typeWordList);
        return "admin/words/word_form";
    }

    @GetMapping({"/admin/word/edit/{id}","/teach/word/edit/{id}"})
    public String editWord(@PathVariable(name = "id") long id, Model model) {
        try {
            Word word = wordService.getWordById(id);
            model.addAttribute("pageTitle", "Edit word");
            model.addAttribute("word", word);

            List<Topic> topicList = topicService.getAllTopic();
            model.addAttribute("topiclist", topicList);

            List<TypeWord> typeWordList = typeWordService.getAllTypeWord();
            model.addAttribute("typeword", typeWordList);
            return "admin/words/word_form";
        } catch (RuntimeException ex) {
            return "redirect:/admin/word";
        }
    }

    @PostMapping({"/admin/word/save", "/teach/word/save"})
    public String savaWord(@ModelAttribute("word") Word word, RedirectAttributes ra,
                           HttpServletRequest request,
                           @RequestParam(value = "image", required = false) MultipartFile image,
                           @RequestParam(value = "sounds", required = false) MultipartFile sounds) {
        String[] topics_id = request.getParameterValues("topic");
        Long topic_id = Long.parseLong(topics_id[0].toString());
        word.getTopic().setTopicID(topic_id);
        String[] type_word = request.getParameterValues("type_word");
        Long idtype = Long.parseLong(type_word[0].toString());
        word.setTypeWord(new TypeWord());
        word.getTypeWord().setTypeID(idtype);
        Long idword = word.getWordID();
        if (storageService.storeFile(image) == null || storageService.storeAudioFile(sounds) == null) {
            wordService.customUpdateWord(idword, idtype, topic_id, word.getText(),
                    word.getMeaning(), word.getPronunciation());
        } else {

            word.setImg("../../../ImgAudio/"+storageService.storeFile(image));
            word.setSound("../../../ImgAudio/"+storageService.storeAudioFile(sounds));
            wordService.saveWord(word);
        }
        ra.addFlashAttribute("messege", "Lưu thành công");
        return "redirect:/admin/word"; // return url
    }

    @GetMapping({"admin/word/delete/{id}","teach/word/delete/{id}"})
    public String deleteWord(@PathVariable(name = "id") long id, RedirectAttributes ra) {
        try {
            wordService.deleteWordById(id);
        } catch (RuntimeException e) {
            ra.addFlashAttribute("messege", "Không tìm thấy");
        }
        return "redirect:/admin/word"; // return url
    }

//    @GetMapping("admin/word/{field}")
//
//    public String SortByField(@PathVariable("field") String field,Model model){
//        List<Word> list= wordService.findWordWithSort(field);
//        model.addAttribute("listwords", list);
//        return "admin/words/word";
//    }


    public static void readListFileWord() {
        FileReader fileReader = null;
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {

            fileInputStream = new FileInputStream("C:\\tudien\\filetudien.csv.txt");
            inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_16LE);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    String[] param = line.split("\t");
                    //String text=param[0];
                    //String mean=param[1];
                    //String pronun= param[2];

                    System.out.println(line);
                }
            } catch (IOException ex) {

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
