package com.roman.FootballManager.controller;

import com.roman.FootballManager.FootballManagerApplication;
import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.entity.Player;
import com.roman.FootballManager.exception.IncorrectCommandException;
import com.roman.FootballManager.exception.IncorrectDataException;
import com.roman.FootballManager.exception.IncorrectPlayerException;
import com.roman.FootballManager.exception.NotEnoughMoneyException;
import com.roman.FootballManager.model.CommandManager;
import com.roman.FootballManager.model.PlayerManager;
import com.roman.FootballManager.model.TransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FootballController {

    @Autowired
    private PlayerManager playerManager;
    @Autowired
    private CommandManager commandManager;
    @Autowired
    private TransferManager transferManager;

    @GetMapping("/")
    public String start(){
        playerManager.createInitialPlayers();
        commandManager.createInitialCommands();
        return "main";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/addPlayer")
    public String addPlayer(){
        return "player/addPlayer";
    }

    @RequestMapping(value = "/addPlayerDone", method = RequestMethod.POST)
    public String addPlayerDone(Model model, String firstName, String lastName, String age, String experience, String commandName){
        try {
            playerManager.createPlayer(firstName,lastName,age,experience,commandName);
        } catch (IncorrectDataException e) {
            return "exception/player/incorrectDataAdd";
        } catch (IncorrectCommandException e){
            return "exception/player/incorrectCommandAdd";
        }
        return "main";
    }

    @GetMapping("/findPlayer")
    public String findPlayer(){
        return "player/findPlayer";
    }

    @RequestMapping(value = "/findPlayerDone", method = RequestMethod.POST)
    public String findPlayerDone(Model model, String id){
        Player player;
        try {
            player = playerManager.findPlayer(id);
        } catch (Exception e) {
            return "exception/player/incorrectPlayerFind";
        }
        model.addAttribute("firstName",player.getFirstName());
        model.addAttribute("lastName",player.getLastName());
        model.addAttribute("age",player.getAge());
        model.addAttribute("experience",player.getExperience());
        model.addAttribute("commandName",player.getCommand());
        return "player/findPlayerDone";
    }

    @GetMapping("/updatePlayer")
    public String updatePlayer(){
        return "player/updatePlayer";
    }

    @RequestMapping(value = "/updatePlayerDone", method = RequestMethod.POST)
    public String updatePlayerDone(Model model, String id,String firstName, String lastName, String age, String experience, String commandName){
        try {
            playerManager.updatePlayer(id, firstName, lastName, age, experience, commandName);
        } catch (IncorrectCommandException e) {
            return "exception/player/incorrectCommandUpdate";
        } catch (IncorrectDataException e) {
            return "exception/player/incorrectDataUpdate";
        } catch (IncorrectPlayerException e) {
            return "exception/player/incorrectPlayerUpdate";
        }
        return "main";
    }

    @GetMapping("/deletePlayer")
    public String deletePlayer(){
        return "player/deletePlayer";
    }

    @RequestMapping(value = "/deletePlayerDone", method = RequestMethod.POST)
    public String deletePlayerDone(Model model, String id){
        try {
            playerManager.deletePlayer(id);
        } catch (IncorrectPlayerException e){
            return "exception/player/incorrectPlayerDelete";
        }
        return "main";
    }

    @GetMapping("/addCommand")
    public String addCommand(){
        return "command/addCommand";
    }

    @RequestMapping(value = "/addCommandDone", method = RequestMethod.POST)
    public String addCommandDone(Model model, String name, String fee, String money){
        try {
            commandManager.createCommand(name,fee,money);
        } catch (IncorrectDataException e) {
            return "exception/command/incorrectDataAdd";
        }
        return "main";
    }

    @GetMapping("/findCommand")
    public String findCommand(){
        return "command/findCommand";
    }

    @RequestMapping(value = "/findCommandDone", method = RequestMethod.POST)
    public String findCommandDone(Model model, String name){
        Command command;
        command = commandManager.findCommand(name);
        if (command == null) return "exception/command/incorrectCommandFind";
        model.addAttribute("name", command.getName());
        model.addAttribute("fee",command.getFee());
        model.addAttribute("money",command.getMoney());
        return "command/findCommandDone";
    }

    @GetMapping("/updateCommand")
    public String updateCommand(){
        return "command/updateCommand";
    }

    @RequestMapping(value = "/updateCommandDone", method = RequestMethod.POST)
    public String updateCommandDone(Model model, String oldName, String newName, String fee){
        try {
            commandManager.updateCommand(oldName,newName,fee);
        } catch (IncorrectDataException e) {
            return "exception/command/incorrectDataUpdate";
        } catch (IncorrectCommandException e) {
            return "exception/command/incorrectCommandUpdate";
        }
        return "main";
    }

    @GetMapping("/deleteCommand")
    public String deleteCommand(){
        return "command/deleteCommand";
    }

    @RequestMapping(value = "/deleteCommandDone", method = RequestMethod.POST)
    public String deleteCommandDone(Model model, String name){
        try {
            commandManager.deleteCommand(name);
        } catch (IncorrectCommandException e) {
            return "exception/command/incorrectCommandDelete";
        }
        return "main";
    }

    @GetMapping("/transfer")
    public String transfer(){
        return "transfer";
    }

    @RequestMapping(value = "/transferDone", method = RequestMethod.POST)
    public String transferDone(Model model, String idPlayer, String nameCommand){
        try {
            transferManager.makeTransfer(idPlayer, nameCommand);
        } catch (IncorrectCommandException e) {
            return "exception/transfer/incorrectCommand";
        } catch (IncorrectPlayerException e) {
            return "exception/transfer/incorrectPlayer";
        } catch (NotEnoughMoneyException e) {
            return "exception/transfer/notEnoughMoney";
        }
        return "main";
    }
    @GetMapping("/exit")
    public void exit(){
        FootballManagerApplication.finish();
    }
}
