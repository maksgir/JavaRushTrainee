package com.game.repository;

import com.game.entity.Player;

import java.util.List;

public interface PlayerDAO {
    List<Player> getAllPlayers();
    Integer getPlayerCount();
    void addPlayer(Player player);
}
