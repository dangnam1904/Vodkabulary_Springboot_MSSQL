package com.project.Vodkabulary.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Vodkabulary.models.Questions;
import com.project.Vodkabulary.repositories.QuestionRepository;
import com.project.Vodkabulary.services.QuestionService;

@Service
public class QuestionImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public List<Questions> getAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public void saveQuestion(Questions questions) {
        questionRepository.save(questions);
    }

    @Override
    public Optional<Questions> getQuestionById(long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void deleteQestionById(long id) {
        questionRepository.deleteById(id);
    }
}
