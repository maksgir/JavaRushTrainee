package com.game.repository;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p where " +
            "(:name is null or (p.name) like (concat('%', :name,'%'))) and" +
            "(:title is null or (p.title) like (concat('%', :title,'%'))) and" +
            "(:race is null or p.race=:race) and " +
            "(:profession is null or p.profession=:profession) and " +
            "(:banned is null or p.banned=:banned) and " +
            "((:before is null and :after is null) or " +
            "(:before is null and p.birthday < :after) or " +
            "(:after is null and p.birthday > :before) or " +
            "(p.birthday > :before and p.birthday < :after)) and " +
            "((:minExperience is null and :maxExperience is null) or " +
            "(:minExperience is null and p.experience < :maxExperience) or " +
            "(:maxExperience is null and p.experience > :minExperience) or " +
            "(p.experience < :maxExperience and p.experience > :minExperience)) "
    )
    List<Player> findByParams(@Param("name") String name,
                              @Param("title") String title,
                              @Param("race") Race race,
                              @Param("profession") Profession profession,
                              @Param("banned") Boolean banned,
                              @Param("before") Date before,
                              @Param("after") Date after,
                              @Param("minExperience") Integer minExperience,
                              @Param("maxExperience") Integer maxExperience
//                              @Param("minLevel") Integer minLevel,
//                              @Param("maxLevel") Integer maxLevel
//                              @Param("order") PlayerOrder order,
//                              @Param("pageNumber") Integer pageNumber,
//                              @Param("pageSize") Integer pageSize,
//
//                              Pageable pageable
    );
}
