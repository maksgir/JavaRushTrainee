package com.game.util;

import com.game.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerUpdater {
    public void updateParams(Player oldPlayer, Player newPlayer){
        if (newPlayer.getName() != null){
            oldPlayer.setName(newPlayer.getName());
        }
        if (newPlayer.getTitle() != null){
            oldPlayer.setTitle(newPlayer.getTitle());
        }
        if (newPlayer.getRace() != null){
            oldPlayer.setRace(newPlayer.getRace());
        }
        if (newPlayer.getProfession() != null){
            oldPlayer.setProfession(newPlayer.getProfession());
        }
        if (newPlayer.getBirthday() != null){
            oldPlayer.setBirthday(newPlayer.getBirthday());
        }

        if (newPlayer.getBanned() == null){
            oldPlayer.setBanned(false);
        } else {
            oldPlayer.setBanned(newPlayer.getBanned());
        }

        if (newPlayer.getExperience() != null){
            oldPlayer.setExperience(newPlayer.getExperience());
        }

    }
}
