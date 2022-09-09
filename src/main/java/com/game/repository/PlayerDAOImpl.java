package com.game.repository;

import com.game.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Repository
public class PlayerDAOImpl implements PlayerDAO {
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<Player> getAllPlayers(){
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        List<Player> playerList = session.createQuery("from Player", Player.class).getResultList();

        for (Player pl : playerList) {
            System.out.println(pl);
        }

        return playerList;
    }
}
