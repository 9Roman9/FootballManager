package com.roman.FootballManager.service;

import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.entity.Player;

import java.util.List;

public interface PlayerService {
    void addPlayer(Player player);
    void addPlayers(List<Player> players);
    Player getPlayerById(long id);
    void updatePlayer(long id, String firstName, String lastName, int age, int experience, String command);
    void deletePlayer(Player player);
    void changeCommandOfPlayer(long id, String nameCommand);
}
