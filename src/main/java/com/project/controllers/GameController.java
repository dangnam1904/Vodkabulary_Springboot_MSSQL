package com.project.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.models.Games;
import com.project.models.Word;
import com.project.services.GameService;
import com.project.services.WordService;

@Controller
public class GameController {
    @Autowired
    private WordService wordService;

    @Autowired
    private GameService gameService;

    private Long topicId, gameId;



    @GetMapping("/topic/game")
    public String game(@RequestParam("topicId") Long topicId, @RequestParam("gameId") Long gameId){
        Games games = gameService.getGameById(gameId);
        this.topicId = topicId;
        this.gameId = gameId;
        if(this.gameId == 2){
            return "redirect:/Scramble";
        } else if (this.gameId == 1) {
            return "redirect:/flashcard/"+topicId;
        }
        else
            return "redirect:/topic";
    }

    @GetMapping("/Scramble")
    public String scramble(Model model){
        List<String> wordByTopic = wordService.getWordByIdTopic(topicId);
        model.addAttribute("listWord", wordByTopic);
        System.out.println(wordByTopic);
        return "users/scramble";
    }

    @GetMapping("/flashcard/{topic}")
    public String showFlashcard(Model model, @PathVariable("topic") Long idTopic){
        List<Word> wordList= wordService.findWordByTopicTopicID(idTopic);
        //Collection<List<Word>> list1 = partitionBasedOnSize(wordList,20);
        Collections.shuffle(wordList);
        final int MAX_ARRAY=20;
        List<Word> listFinall= new ArrayList<>();
        for (int i=0; i< wordList.size();i++){
            if( i<MAX_ARRAY){
                listFinall.add(wordList.get(i));
            }else {
                break;
            }
        }
        Collections.shuffle(listFinall);
        System.out.print(listFinall);
        model.addAttribute("listWordOrderTopic",listFinall);
        return  "users/flashcard";
    }

//    static <T> Collection<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
//        final AtomicInteger counter = new AtomicInteger(0);
//        return inputList.stream()
//                .collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size))
//                .values();
//    }
}
