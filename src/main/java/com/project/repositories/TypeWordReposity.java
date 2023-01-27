package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.TypeWord;

@Repository
public interface TypeWordReposity extends JpaRepository<TypeWord, Long> {
}
