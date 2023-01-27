package com.project.services;

import java.util.List;

import com.project.models.Games;

public interface GameService{
    List<Games> getAllGame();
    void saveGame(Games games);
    Games getGameById(long id);
    void deleteGameById(long id);
}
