package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                     @RequestParam(value = "banned", required = false) Boolean banned) {
        return playerService.findByParams(name, title, race, profession, banned);
    }

    @GetMapping("/players/count")
    public Integer getPlayersCount() {
        return playerService.gatPlayerCount();
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
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(PlayerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
