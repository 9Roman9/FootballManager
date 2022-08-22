package com.roman.FootballManager.service.serviceImpl;

import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.repository.CommandRepository;
import com.roman.FootballManager.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CommandServiceImpl implements CommandService {

    @Autowired
    private CommandRepository repository;

    @Override
    public void addCommand(Command command) {
        repository.save(command);
    }

    @Override
    public Command getCommandByName(String name) {
        return repository.getCommandByName(name);
    }

    @Override
    public void updateCommand(String oldName, String newName, int fee) {
        repository.updateCommand(oldName,newName,fee);
    }

    @Override
    public void deleteCommand(Command command) {
        repository.delete(command);
    }

    @Override
    public void addCommands(List<Command> commands) {
        repository.saveAll(commands);
    }

    @Override
    public void setNewSumOfMoney(String name, long money) {
        repository.setNewSumOfMoney(name, money);
    }
}
