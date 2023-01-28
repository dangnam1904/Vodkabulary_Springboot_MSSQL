package com.project.Vodkabulary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Vodkabulary.models.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
}
