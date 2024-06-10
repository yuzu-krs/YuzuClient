package me.gamrboy4life.paradox.module.misc;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class DeadPanic extends Module{

	public DeadPanic() {
		super("DeadPanic",0,Category.MISC);
	}
	
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.isDead) {
				for(Module m:Paradox.instance.moduleManager.getModules()) {
					m.setToggled(false);
				}
				
				Paradox.instance.moduleManager.addChatMessage("死んだのですべてのMODが無効になりました!");
			}
		}
	}
}
