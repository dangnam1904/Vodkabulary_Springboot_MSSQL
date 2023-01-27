package com.project.repositories;

import com.project.models.TestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestHistoryRepository extends JpaRepository<TestHistory,Long> {
}
