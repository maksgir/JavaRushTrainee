package com.game.service;

import com.game.entity.Player;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Integer gatPlayerCount();
    void savePlayer(Player player) throws InvalidPlayerParamsException;
    Player getPlayerById(long id) throws PlayerNotFoundException;
    void updatePlayer(long id, Player newPlayer) throws PlayerNotFoundException, InvalidPlayerParamsException;
    void deletePlayer(long id) throws PlayerNotFoundException;
}
