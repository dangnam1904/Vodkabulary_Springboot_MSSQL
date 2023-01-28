package com.project.Vodkabulary.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Vodkabulary.models.Test;
import com.project.Vodkabulary.repositories.TestRepository;
import com.project.Vodkabulary.services.TestService;

public class TestImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> getAllTest() {
        return null;
    }

    @Override
    public void saveTest(Test test) {

    }

    @Override
    public List<Test> getTestById(long id) {
        return null;
    }

    @Override
    public void deleteTestById(long id) {

    }
}
