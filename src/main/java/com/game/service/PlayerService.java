package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.util.PlayerComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Page<Player> findByParams(String name, String title,
                              Race race, Profession profession,
                              Boolean banned,
                              Long before,
                              Long after,
                              Integer minExperience,
                              Integer maxExperience,
                              Integer minLevel,
                              Integer maxLevel,
                              Pageable pageRequest);
    Integer gatPlayerCount(String name, String title,
                           Race race, Profession profession, Boolean banned,
                           Long before, Long after,
                           Integer minExperience, Integer maxExperience,
                           Integer minLevel, Integer maxLevel);
    Player savePlayer(Player player) throws InvalidPlayerParamsException;
    Player getPlayerById(long id) throws PlayerNotFoundException, InvalidPlayerParamsException;
    Player updatePlayer(long id, Player newPlayer) throws PlayerNotFoundException, InvalidPlayerParamsException;
    void deletePlayer(long id) throws PlayerNotFoundException, InvalidPlayerParamsException;
}
