package com.game.service;

import com.game.entity.Player;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.repository.PlayerDAO;
import com.game.util.CharacteristicCounter;
import com.game.util.PlayerUpdater;
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

    @Autowired
    private PlayerUpdater updater;

    @Override
    public List<Player> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }

    @Override
    public Integer gatPlayerCount() {
        return playerDAO.getPlayerCount();
    }

    @Override
    public void savePlayer(Player player) throws InvalidPlayerParamsException {
        validator.validatePlayer(player);
        chCounter.setCurrentLevel(player);
        chCounter.setUntilNextLevelExp(player);
        playerDAO.savePlayer(player);
    }

    @Override
    public Player getPlayerById(long id) throws PlayerNotFoundException {
        return playerDAO.getPlayerById(id);
    }

    @Override
    public void updatePlayer(long id, Player newPlayer) throws PlayerNotFoundException, InvalidPlayerParamsException {
        if (id <= 0) {
            throw new InvalidPlayerParamsException("Id must be >0");
        }
        Player player = getPlayerById(id);

        updater.updateParams(player, newPlayer);
        savePlayer(player);
    }

    @Override
    public void deletePlayer(long id) throws PlayerNotFoundException {
        playerDAO.deletePlayer(id);
    }
}
