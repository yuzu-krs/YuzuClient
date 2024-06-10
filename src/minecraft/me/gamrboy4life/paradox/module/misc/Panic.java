package me.gamrboy4life.paradox.module.misc;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Panic extends Module{

	public Panic() {
		super("Panic",0,Category.MISC);
	}
	
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			for(Module m:Paradox.instance.moduleManager.getModules()) {
				m.setToggled(false);
			}
			Paradox.instance.moduleManager.addChatMessage("すべてのMODが無効になりました!");
		} 
		

	}

}
