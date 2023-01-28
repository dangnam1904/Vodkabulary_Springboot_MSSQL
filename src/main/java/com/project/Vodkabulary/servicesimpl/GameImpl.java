package com.project.Vodkabulary.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Vodkabulary.models.Games;
import com.project.Vodkabulary.repositories.GameRepository;
import com.project.Vodkabulary.services.GameService;

@Service
public class GameImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Games> getAllGame() {
        return gameRepository.findAll();
    }

    @Override
    public void saveGame(Games games) {

    }

    @Override
    public Games getGameById(long id) {
        Optional<Games> optional = gameRepository.findById(id);
        Games games= null;

        if( optional.isPresent()) {
            games = optional.get();
        } else {
            throw new RuntimeException("game not found for id: " + id);
        }
        return games;
    }

    @Override
    public void deleteGameById(long id) {

    }
}
