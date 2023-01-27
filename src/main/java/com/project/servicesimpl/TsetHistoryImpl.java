package com.project.servicesimpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.models.TestHistory;
import com.project.repositories.TestHistoryRepository;
import com.project.services.TestHistoryService;

public class TsetHistoryImpl implements TestHistoryService {
    @Autowired
    private TestHistoryRepository testHistoryRepository;
    @Override
    public List<TestHistory> getAllTestHistory() {
        return testHistoryRepository.findAll();
    }

    @Override
    public void saveTestHistory(TestHistory testHistory) {
        testHistoryRepository.save(testHistory);
    }

    @Override
    public List<TestHistory> getTestHistoryById(long id) {
        return testHistoryRepository.findAllById(Collections.singleton(id));
    }

    @Override
    public void deleteTestHistoryById(long id) {
        testHistoryRepository.deleteById(id);
    }
}
