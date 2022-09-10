package com.game.util;

import com.game.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class CharacteristicCounter {
    public void setCurrentLevel(Player player) {
        int exp = player.getExperience();
        int level = (int) (Math.sqrt(2500 + 200 * exp) - 50) / 100;
        player.setLevel(level);

    }

    public void setUntilNextLevelExp(Player player) {
        int exp = player.getExperience();
        int lvl = player.getLevel();
        int unlExp = 50 * (lvl + 1) * (lvl + 2) - exp;

        player.setUntilNextLevel(unlExp);
    }
}
