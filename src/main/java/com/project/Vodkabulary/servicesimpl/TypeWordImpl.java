package com.project.Vodkabulary.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Vodkabulary.models.TypeWord;
import com.project.Vodkabulary.repositories.TypeWordReposity;
import com.project.Vodkabulary.services.TypeWordService;

@Service
public class TypeWordImpl implements TypeWordService {
    @Autowired
    private TypeWordReposity typeWordReposity;

    @Override
    public List<TypeWord> getAllTypeWord() {
        return typeWordReposity.findAll();
    }

    @Override
    public void saveTypeWord(TypeWord typeWord) {
        typeWordReposity.save(typeWord);
    }

    @Override
    public TypeWord getTypeWordById(long id) {

        Optional<TypeWord> optional = typeWordReposity.findById(id);
        TypeWord typeWord = null;

        if( optional.isPresent()) {
            typeWord = optional.get();
        } else {
            throw new RuntimeException("word not found for id: " + id);
        }

        return typeWord;
    }

    @Override
    public void deleteTypeWordById(long id) {
        typeWordReposity.deleteById(id);
    }
}
