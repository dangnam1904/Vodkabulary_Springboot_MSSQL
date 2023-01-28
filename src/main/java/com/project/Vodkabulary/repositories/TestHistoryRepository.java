package com.project.Vodkabulary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Vodkabulary.models.TestHistory;

public interface TestHistoryRepository extends JpaRepository<TestHistory,Long> {
}
