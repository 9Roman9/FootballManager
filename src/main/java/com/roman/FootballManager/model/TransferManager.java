package com.roman.FootballManager.model;

import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.entity.Player;
import com.roman.FootballManager.exception.IncorrectCommandException;
import com.roman.FootballManager.exception.IncorrectPlayerException;
import com.roman.FootballManager.exception.NotEnoughMoneyException;
import com.roman.FootballManager.service.serviceImpl.CommandServiceImpl;
import com.roman.FootballManager.service.serviceImpl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("transferManager")
public class TransferManager {
    @Autowired
    private CommandServiceImpl commandService;
    @Autowired
    private PlayerServiceImpl playerService;

    public void makeTransfer(String idPlayer, String nameCommand) throws IncorrectCommandException, IncorrectPlayerException, NotEnoughMoneyException {
        Command newCommand = commandService.getCommandByName(nameCommand);
        if (newCommand == null) throw new IncorrectCommandException();
        Player player;
        try {
            player = playerService.getPlayerById(Long.parseLong(idPlayer));
        } catch (Exception e){
            throw new IncorrectPlayerException();
        }
        Command oldCommand = commandService.getCommandByName(player.getCommand());
        long transferSum = (long) player.getExperience() * 1000000 / player.getAge();
        long fee = transferSum * oldCommand.getFee() / 100;
        long finalSum = transferSum + fee;
        if (newCommand.getMoney()<finalSum) throw new NotEnoughMoneyException();
        playerService.changeCommandOfPlayer(Long.parseLong(idPlayer),nameCommand);
        commandService.setNewSumOfMoney(player.getCommand(),oldCommand.getMoney()+finalSum);
        commandService.setNewSumOfMoney(nameCommand,newCommand.getMoney()-finalSum);
    }
}
