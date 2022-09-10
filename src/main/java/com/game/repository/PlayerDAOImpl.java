package com.game.repository;

import com.game.entity.Player;
import com.game.exceptions.PlayerNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Controller
public class PlayerDAOImpl implements PlayerDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Player> getAllPlayers() {
        Session session = em.unwrap(Session.class);
        List<Player> playerList = session.createQuery("from Player", Player.class).getResultList();

        return playerList;
    }

    @Override
    public Integer getPlayerCount() {
        Query query = em.createQuery("SELECT COUNT(p) FROM Player p");
        return (int) (long) query.getSingleResult();
    }

    @Override
    public void savePlayer(Player player) {
        Session session = em.unwrap(Session.class);
        session.saveOrUpdate(player);
    }

    @Override
    public Player getPlayerById(long id) throws PlayerNotFoundException {
        Player player = em.find(Player.class, id);
        if (player == null) {
            throw new PlayerNotFoundException("Player with ID=" + id + " wasn't found");
        }
        return player;
    }

    @Override
    public void deletePlayer(long id) throws PlayerNotFoundException {
        Player player = getPlayerById(id);
        em.remove(player);
    }

}
