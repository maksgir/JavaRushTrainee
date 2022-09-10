package com.game.util;

import com.game.entity.Player;
import com.game.exceptions.InvalidPlayerParamsException;
import org.springframework.stereotype.Component;

import java.sql.Date;


//TODO add banned == null case
@Component
public class PlayerValidator {
    public void validatePlayer(Player player) throws InvalidPlayerParamsException {
        validateRequiredParams(player);
        validateExperience(player.getExperience());
        validateDate(player.getBirthday());

    }

    private void validateDate(Date birthday) throws InvalidPlayerParamsException {
        int year = birthday.getYear() + 1900;
        if (year < 2000 || year > 3000) {
            throw new InvalidPlayerParamsException("Date year is out of range");
        }
    }

    private void validateExperience(Integer experience) throws InvalidPlayerParamsException {
        if (experience < 0 || experience > 10000000) {
            throw new InvalidPlayerParamsException("Experience is out of range");
        }
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
