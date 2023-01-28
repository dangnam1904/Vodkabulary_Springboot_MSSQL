package com.project.Vodkabulary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Vodkabulary.models.TypeWord;

@Repository
public interface TypeWordReposity extends JpaRepository<TypeWord, Long> {
}
