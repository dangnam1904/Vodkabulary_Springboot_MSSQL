package com.project.Vodkabulary.services;

import java.util.List;

import com.project.Vodkabulary.models.TestHistory;

public interface TestHistoryService {
    List<TestHistory> getAllTestHistory();
    void saveTestHistory(TestHistory testHistory);
    List<TestHistory> getTestHistoryById(long id);
    void deleteTestHistoryById(long id);
}
