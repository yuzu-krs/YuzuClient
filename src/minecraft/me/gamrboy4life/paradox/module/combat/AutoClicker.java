package me.gamrboy4life.paradox.module.combat;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class AutoClicker extends Module{

	public AutoClicker() {
		super("AutoClicker",0,Category.COMBAT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			try {
				mc.thePlayer.setSprinting(true);
				mc.clickMouse();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
