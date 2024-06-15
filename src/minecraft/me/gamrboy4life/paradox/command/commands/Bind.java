package me.gamrboy4life.paradox.command.commands;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.command.Command;
import me.gamrboy4life.paradox.module.Module;

public class Bind extends Command{

	public Bind() {
		super("Bind","キーバインド設定します","bind <ハック名> <任意のキー> | clear","b");
	}
	
	@Override
	public void onCommand(String[] args,String command) {
		if(args.length==2) {
			String moduleName=args[0];
			String keyName=args[1];
			
			boolean foundModule=false;
			
			for(Module module:Paradox.instance.moduleManager.getModules()) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.setKey(Keyboard.getKeyIndex(keyName.toUpperCase()));
					
					Paradox.instance.moduleManager.addChatMessage(String.format("%s を %sキー に設定しました",module.name,Keyboard.getKeyName(module.getKey())));
					foundModule=true;
					break;
				}
			}
			
			if(!foundModule) {
				Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help] ");
			}
			
		}
		
		
		if(args.length==1) {
			if(args[0].equalsIgnoreCase("Clear")) {
				for(Module module : Paradox.instance.moduleManager.getModules()) {
					module.setKey(Keyboard.KEY_NONE);
				}
				Paradox.instance.moduleManager.addChatMessage("すべてのキーバインドがクリアされました!");
			}else {
				Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help] ");
			}
		}
		
		
		
		
	}


}
