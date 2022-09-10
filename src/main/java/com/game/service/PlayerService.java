package com.game.service;

import com.game.entity.Player;
import com.game.exceptions.InvalidPlayerParamsException;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Integer gatPlayerCount();
    void addPlayer(Player player) throws InvalidPlayerParamsException;
}
