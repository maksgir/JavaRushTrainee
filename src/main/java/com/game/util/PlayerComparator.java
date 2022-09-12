package com.game.util;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

    private PlayerOrder order;

    public PlayerComparator(PlayerOrder order) {
        this.order = (order == null ? PlayerOrder.ID : order);
    }

    @Override
    public int compare(Player o1, Player o2) {
        switch (order) {
            case ID: {
                return Long.compare(o1.getId(), o2.getId());
            }
            case NAME: {
                return o1.getName().compareTo(o2.getName());
            }
            case LEVEL: {
                return Integer.compare(o1.getLevel(), o2.getLevel());
            }
            case BIRTHDAY: {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
            case EXPERIENCE: {
                return Integer.compare(o1.getExperience(), o2.getExperience());
            }
        }
        return 0;
    }
}
