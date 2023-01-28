package com.project.Vodkabulary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Vodkabulary.models.Test;

public interface TestRepository extends JpaRepository<Test,Long> {
}
