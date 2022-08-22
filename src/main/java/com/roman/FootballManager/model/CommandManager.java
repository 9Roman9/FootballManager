package com.roman.FootballManager.model;

import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.exception.IncorrectCommandException;
import com.roman.FootballManager.exception.IncorrectDataException;
import com.roman.FootballManager.service.serviceImpl.CommandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("commandManager")
public class CommandManager {
    @Autowired
    private CommandServiceImpl commandService;
    public void createInitialCommands(){
        Command command1 = new Command("Динамо",8,500000);
        Command command2 = new Command("Шахтар",6,600000);
        Command command3 = new Command("Ювентус",10,10000000);
        commandService.addCommands(List.of(command1,command2,command3));
    }

    public void createCommand(String name, String fee, String money) throws IncorrectDataException {
        if (name.isEmpty()||Integer.parseInt(fee)<0||Integer.parseInt(fee)>10||Integer.parseInt(money)<0)
            throw new IncorrectDataException();
        Command command = new Command(name,Integer.parseInt(fee),Long.parseLong(money));
        commandService.addCommand(command);
    }

    public Command findCommand(String name){
        return commandService.getCommandByName(name);
    }

    public void updateCommand(String oldName, String newName, String fee) throws IncorrectDataException, IncorrectCommandException {
        if (newName.isEmpty()||Integer.parseInt(fee)<0||Integer.parseInt(fee)>10)
            throw new IncorrectDataException();
        Command command = commandService.getCommandByName(oldName);
        if (command == null) throw new IncorrectCommandException();
        commandService.updateCommand(oldName,newName,Integer.parseInt(fee));
    }

    public void deleteCommand(String name) throws IncorrectCommandException {
        Command command = commandService.getCommandByName(name);
        if (command == null) throw new IncorrectCommandException();
        commandService.deleteCommand(command);
    }
}
