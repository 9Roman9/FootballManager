package com.roman.FootballManager.service.serviceImpl;

import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.entity.Player;
import com.roman.FootballManager.repository.PlayerRepository;
import com.roman.FootballManager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Override
    public void addPlayer(Player player) {
        repository.save(player);
    }

    @Override
    public Player getPlayerById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void updatePlayer(long id, String firstName, String lastName, int age, int experience, String command) {
        repository.updatePlayer(id,firstName,lastName,age,experience, command);
    }

    @Override
    public void deletePlayer(Player player) {
        repository.delete(player);
    }

    @Override
    public void addPlayers(List<Player> players) {
        repository.saveAll(players);
    }

    @Override
    public void changeCommandOfPlayer(long id, String nameCommand) {
        repository.changeCommandOfPlayer(id, nameCommand);
    }
}