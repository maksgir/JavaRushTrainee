package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    List<Player> findByParams(String name, String title,
                              Race race, Profession profession,
                              Boolean banned,
                              Long before,
                              Long after,
                              Integer minExperience,
                              Integer maxExperience);
    Integer gatPlayerCount();
    void savePlayer(Player player) throws InvalidPlayerParamsException;
    Player getPlayerById(long id) throws PlayerNotFoundException, InvalidPlayerParamsException;
    void updatePlayer(long id, Player newPlayer) throws PlayerNotFoundException, InvalidPlayerParamsException;
    void deletePlayer(long id) throws PlayerNotFoundException, InvalidPlayerParamsException;
}
