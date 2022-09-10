package com.game.controller;

import com.game.entity.Player;
import com.game.exceptions.InvalidPlayerParamsException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Player> getAllPlayer() {
        return playerService.getAllPlayers();
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
    public Player getPlayerById(@PathVariable long id) throws PlayerNotFoundException {
        return playerService.getPlayerById(id);
    }

    @PostMapping("players/{id}")
    public void updatePlayerById(@PathVariable long id, @RequestBody Player newPlayer) throws PlayerNotFoundException, InvalidPlayerParamsException {
        playerService.updatePlayer(id, newPlayer);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(PlayerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
