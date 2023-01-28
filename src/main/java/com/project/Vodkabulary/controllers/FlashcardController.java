package com.project.Vodkabulary.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class FlashcardController {
//    @Autowired
//    private WordService wordService;
//
//    @GetMapping("/flashcard/{topic}")
//    public String showFlashcard(Model model, @PathVariable("topic") Long idTopic){
//        List<Word> wordList= wordService.findWordByTopicTopicID(idTopic);
//        Collection<List<Word>> list1 = partitionBasedOnSize(wordList,20);
//        System.out.println("after  random");
//        Collections.shuffle(wordList);
//        List<Word> listFinall= new ArrayList<>();
//        for (int i=0; i< wordList.size();i++){
//            if( i<20){
//                listFinall.add(wordList.get(i));
//            }else {
//                break;
//            }
//        }
//        System.out.println(listFinall.toString());
//        Collections.shuffle(listFinall);
//        //System.out.print(wordList.toString());
//        model.addAttribute("listWordOrderTopic",listFinall);
//        return  "web/flashcard";
//    }
//
//    static <T> Collection<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
//        final AtomicInteger counter = new AtomicInteger(0);
//        return inputList.stream()
//                .collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size))
//                .values();
//    }
}
