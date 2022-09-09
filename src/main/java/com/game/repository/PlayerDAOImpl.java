package com.game.repository;

import com.game.entity.Player;
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
    public List<Player> getAllPlayers(){
        System.out.println("nigga");
        Session session = em.unwrap(Session.class);
        List<Player> playerList = session.createQuery("from Player", Player.class).getResultList();

        for (Player pl : playerList) {
            System.out.println(pl);
        }

        return playerList;
    }

    @Override
    public Integer getPlayerCount() {
        Query query = em.createQuery("SELECT COUNT(p) FROM Player p");
        return  (int) (long) query.getSingleResult();
    }
}
