package com.game.service;

import com.game.entity.Player;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.repository.PlayerDAO;
import com.game.util.CharacteristicCounter;
import com.game.util.PlayerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private CharacteristicCounter chCounter;

    @Autowired
    private PlayerValidator validator;

    @Override
    public List<Player> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }

    @Override
    public Integer gatPlayerCount() {
        return playerDAO.getPlayerCount();
    }

    @Override
    public void addPlayer(Player player) throws InvalidPlayerParamsException {
        validator.validatePlayer(player);
        chCounter.setCurrentLevel(player);
        chCounter.setUntilNextLevelExp(player);
        playerDAO.addPlayer(player);
    }

    @Override
    public Player getPlayerById(long id) throws PlayerNotFoundException {
        return playerDAO.getPlayerById(id);
    }
}
