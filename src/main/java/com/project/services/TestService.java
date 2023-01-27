package com.project.services;

import java.util.List;

import com.project.models.Test;

public interface TestService {
    List<Test> getAllTest();
    void saveTest(Test test);
    List<Test> getTestById(long id);
    void deleteTestById(long id);
}
