package com.project.services;

import com.project.models.TestHistory;

import java.util.List;

public interface TestHistoryService {
    List<TestHistory> getAllTestHistory();
    void saveTestHistory(TestHistory testHistory);
    List<TestHistory> getTestHistoryById(long id);
    void deleteTestHistoryById(long id);
}
