package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class FullBright extends Module{

	public FullBright() {
		super("FullBright",0,Category.RENDER);
	}
	
	private float oldGamma;
	
	
	@Override
	public void onEnable() {
		super.onEnable();
		oldGamma=mc.gameSettings.gammaSetting;
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		mc.gameSettings.gammaSetting=oldGamma;
	}
	
	
	@Override
	public void onRender() {
		if(this.isToggled()) {
			mc.gameSettings.gammaSetting=100;
		}
	}
}
