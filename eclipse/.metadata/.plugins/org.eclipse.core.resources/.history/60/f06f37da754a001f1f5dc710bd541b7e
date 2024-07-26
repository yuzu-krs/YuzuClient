package me.gamrboy4life.paradox.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.command.commands.Bind;
import me.gamrboy4life.paradox.command.commands.ChatSpam;
import me.gamrboy4life.paradox.command.commands.Help;
import me.gamrboy4life.paradox.command.commands.Say;
import me.gamrboy4life.paradox.command.commands.Toggle;
import me.gamrboy4life.paradox.event.events.EventChat;

public class CommandManager {
	
	public static List<Command> commands=new ArrayList<Command>();
	public String prefix=".";
	
	public CommandManager() {
		setup();
	}
	
	public void setup() {
		commands.add(new Bind());
		commands.add(new Say());
		commands.add(new Toggle());
		commands.add(new ChatSpam());
		commands.add(new Help());
	}
	
	public static List<Command> getCommands(){
		return commands;
	}
	
	public void handleChat(EventChat event) {
		String message=event.getMessage();
		
		if(!message.startsWith(prefix))
			return;
		
		event.setCancelled(true);
		
		message=message.substring(prefix.length());
		
		boolean foundCommand=false;
		
		if(message.split(" ").length>0) {
			String commandName=message.split(" ")[0];
			
			for(Command c:commands) {
				if(c.aliases.contains(commandName)||c.name.equalsIgnoreCase(commandName)) {
					c.onCommand(Arrays.copyOfRange(message.split(" "),1,message.split(" ").length),message);
					foundCommand=true;
					break;
				}
			}
		}
		if(!foundCommand) {
			Paradox.instance.moduleManager.addChatMessage("コマンドが見つかりません! [.help]");
		}
		
		
		
		
		
	}
	
	
}
