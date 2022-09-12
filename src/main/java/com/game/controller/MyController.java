package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class MyController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public List<Player> getAllPlayer(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "race", required = false) Race race,
                                     @RequestParam(value = "profession", required = false) Profession profession,
                                     @RequestParam(value = "banned", required = false) Boolean banned,
                                     @RequestParam(value = "after", required = false) Long after,
                                     @RequestParam(value = "before", required = false) Long before,
                                     @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                     @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                     @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                     @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                     @RequestParam(value = "order", required = false) PlayerOrder order,
                                     @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        int pageN = (pageNumber == null ? 0: pageNumber);
        int pageS = (pageSize == null ? 3: pageSize);
        PlayerOrder playerOrder = (order == null ? PlayerOrder.ID : order);

        Sort sort = Sort.by(playerOrder.getFieldName()).ascending();

        PageRequest pageRequest = PageRequest.of(pageN, pageS, sort);


        Page result = playerService.findByParams(name, title,
                race, profession, banned,
                before, after,
                minExperience, maxExperience,
                minLevel, maxLevel, pageRequest);

//        System.out.println((Player)result.stream().findFirst().get());

        return result.getContent();
    }

    @GetMapping("/players/count")
    public Integer getPlayersCount(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "race", required = false) Race race,
                                   @RequestParam(value = "profession", required = false) Profession profession,
                                   @RequestParam(value = "banned", required = false) Boolean banned,
                                   @RequestParam(value = "after", required = false) Long after,
                                   @RequestParam(value = "before", required = false) Long before,
                                   @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                   @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                   @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                   @RequestParam(value = "maxLevel", required = false) Integer maxLevel) {
        return playerService.gatPlayerCount(name, title,
                race, profession, banned,
                before, after,
                minExperience, maxExperience,
                minLevel, maxLevel);
    }

    @PostMapping("/players")
    public void addPlayer(@RequestBody Player player) throws InvalidPlayerParamsException {
        playerService.savePlayer(player);
    }

    @GetMapping("players/{id}")
    public Player getPlayerById(@PathVariable long id) throws PlayerNotFoundException, InvalidPlayerParamsException {
        return playerService.getPlayerById(id);
    }

    @PostMapping("players/{id}")
    public void updatePlayerById(@PathVariable long id, @RequestBody Player newPlayer) throws PlayerNotFoundException, InvalidPlayerParamsException {
        playerService.updatePlayer(id, newPlayer);
    }

    @DeleteMapping("players/{id}")
    public void deletePlayer(@PathVariable long id) throws PlayerNotFoundException, InvalidPlayerParamsException {
        playerService.deletePlayer(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(PlayerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
