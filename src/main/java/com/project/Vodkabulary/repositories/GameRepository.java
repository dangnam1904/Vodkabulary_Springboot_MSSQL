package com.project.Vodkabulary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Vodkabulary.models.Games;

@Repository
public interface GameRepository extends JpaRepository<Games,Long> {
}


