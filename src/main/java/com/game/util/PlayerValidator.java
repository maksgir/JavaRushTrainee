package com.game.util;

import com.game.entity.Player;
import com.game.exceptions.InvalidPlayerParamsException;
import org.springframework.stereotype.Component;

@Component
public class PlayerValidator {
    public void validatePlayer(Player player) throws InvalidPlayerParamsException {
        validateRequiredParams(player);

    }

    private void validateRequiredParams(Player player) throws InvalidPlayerParamsException {
        if (player.getName() == null || player.getName().isEmpty()) {
            throw new InvalidPlayerParamsException("Player name is unset or empty");
        }
        if (player.getTitle() == null) {
            throw new InvalidPlayerParamsException("Player title is unset");
        }

        if (player.getRace() == null) {
            throw new InvalidPlayerParamsException("Player race is unset");
        }

        if (player.getProfession() == null) {
            throw new InvalidPlayerParamsException("Player profession is unset");
        }

        if (player.getBirthday() == null || player.getBirthday().getTime() < 0) {
            throw new InvalidPlayerParamsException("Player date is unset or < 0");
        }

    }
}
