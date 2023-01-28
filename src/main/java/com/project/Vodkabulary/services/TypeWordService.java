package com.project.Vodkabulary.services;

import java.util.List;

import com.project.Vodkabulary.models.TypeWord;

public interface TypeWordService {
    List<TypeWord> getAllTypeWord();
    void saveTypeWord(TypeWord typeWord);
    TypeWord getTypeWordById(long id);
    void deleteTypeWordById(long id);
}
