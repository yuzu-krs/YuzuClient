package me.gamrboy4life.paradox.command.commands;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.command.Command;
import me.gamrboy4life.paradox.module.Module;

public class Toggle extends Command{

	public Toggle() {
		super("Toggle","モデルを有効/無効","toggle <name>","t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length>0) {
			String moduleName=args[0];
			boolean foundModule=false;
			
			for(Module module:Paradox.instance.moduleManager.getModules()) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					
					Paradox.instance.moduleManager.addChatMessage(module.name+" "+(module.isToggled()? "有効":"無効"));
					
					foundModule=true;
					break;
				}
			}
			if(!foundModule) {
				Paradox.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
			}
		}
		
	}


}
