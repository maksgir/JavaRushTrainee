package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.repository.PlayerRepository;
import com.game.util.CharacteristicCounter;
import com.game.util.PlayerUpdater;
import com.game.util.PlayerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private CharacteristicCounter chCounter;

    @Autowired
    private PlayerValidator validator;

    @Autowired
    private PlayerUpdater updater;

    @Override
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    @Override
    public List<Player> findByParams(String name, String title,
                                     Race race, Profession profession,
                                     Boolean banned,
                                     Long before,
                                     Long after,
                                     Integer minExperience,
                                     Integer maxExperience) {

        java.sql.Date beforeDate = (before != null?new java.sql.Date(new java.util.Date(before).getTime()):null);
        java.sql.Date afterDate = (after != null?new java.sql.Date(new java.util.Date(after).getTime()):null);
        return repository.findByParams(name, title, race, profession, banned, beforeDate, afterDate, minExperience, maxExperience);
    }

    @Override
    public Integer gatPlayerCount() {
        return (int) repository.count();
    }

    @Override
    public void savePlayer(Player player) throws InvalidPlayerParamsException {
        validator.validatePlayer(player);
        chCounter.setCurrentLevel(player);
        chCounter.setUntilNextLevelExp(player);
        repository.save(player);
    }

    @Override
    public Player getPlayerById(long id) throws PlayerNotFoundException, InvalidPlayerParamsException {
        if (id <= 0) {
            throw new InvalidPlayerParamsException("Id must be >0");
        }
        Optional<Player> playerOptional = repository.findById(id);
        if (!playerOptional.isPresent()) {
            throw new PlayerNotFoundException("Player with ID=" + id + " wasn't found");
        }
        return playerOptional.get();
    }

    @Override
    public void updatePlayer(long id, Player newPlayer) throws PlayerNotFoundException, InvalidPlayerParamsException {
        Player player = getPlayerById(id);

        updater.updateParams(player, newPlayer);
        savePlayer(player);
    }

    @Override
    public void deletePlayer(long id) throws PlayerNotFoundException, InvalidPlayerParamsException {
        Player player = getPlayerById(id);
        repository.delete(player);
    }
}
