package com.project.services;

import java.util.List;
import java.util.Optional;

import com.project.models.Questions;


public interface QuestionService {
    List<Questions> getAllQuestion();
    void saveQuestion(Questions questions);
    Optional<Questions> getQuestionById(long id);
    void deleteQestionById(long id);
}
