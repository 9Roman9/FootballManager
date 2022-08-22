package com.roman.FootballManager.service;

import com.roman.FootballManager.entity.Command;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommandService {
    void addCommand(Command command);
    void addCommands(List<Command> commands);
    Command getCommandByName(String name);
    void updateCommand(String oldName, String newName, int fee);
    void deleteCommand(Command command);
    void setNewSumOfMoney(String name, long money);
}
