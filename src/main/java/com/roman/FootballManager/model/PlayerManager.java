package com.roman.FootballManager.model;

import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.entity.Player;
import com.roman.FootballManager.exception.IncorrectCommandException;
import com.roman.FootballManager.exception.IncorrectDataException;
import com.roman.FootballManager.exception.IncorrectPlayerException;
import com.roman.FootballManager.service.serviceImpl.CommandServiceImpl;
import com.roman.FootballManager.service.serviceImpl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("footballManager")
public class PlayerManager {

    @Autowired
    private PlayerServiceImpl playerService;
    @Autowired
    private CommandServiceImpl commandService;

    public void createInitialPlayers(){
        Player player1 = new Player("Андрій","Ярмоленко",32,228,"Динамо");
        Player player2 = new Player("Андрій","П'ятов",38,288,"Шахтар");
        Player player3 = new Player("Кріштіану","Роналду",37,360,"Ювентус");
        playerService.addPlayers(List.of(player1,player2,player3));
    }

    public void createPlayer(String firstName, String lastName, String age, String experience, String commandName) throws IncorrectDataException, IncorrectCommandException {
        try {
            if (firstName.isEmpty()||lastName.isEmpty()||Integer.parseInt(age)<=0||Integer.parseInt(experience)<=0) throw new IncorrectDataException();
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
            Command command = commandService.getCommandByName(commandName);
            if (command==null) {
                throw new IncorrectCommandException();
            }
        try {
            Player player = new Player(firstName,lastName,Integer.parseInt(age),Integer.parseInt(experience),commandName);
            playerService.addPlayer(player);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }

    public Player findPlayer(String id){
        return playerService.getPlayerById(Long.parseLong(id));
    }

    public void updatePlayer(String id,String firstName, String lastName, String age, String experience, String commandName) throws IncorrectCommandException, IncorrectDataException, IncorrectPlayerException {
        if (firstName.isEmpty()||lastName.isEmpty()) throw new IncorrectDataException();
        Command command = commandService.getCommandByName(commandName);
        if (command==null) {
            throw new IncorrectCommandException();
        }
        try {
            playerService.getPlayerById(Long.parseLong(id));
        } catch (Exception e){
            throw new IncorrectPlayerException();
        }
        try {
            playerService.updatePlayer(Long.parseLong(id),firstName,lastName,Integer.valueOf(age),Integer.parseInt(experience),commandName);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }

    public void deletePlayer(String id) throws IncorrectPlayerException {
        Player player;
        try {
            player = findPlayer(id);
        } catch (Exception e) {
            throw new IncorrectPlayerException();
        }
        playerService.deletePlayer(player);
    }
}
