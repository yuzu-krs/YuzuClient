package me.gamrboy4life.paradox.module.movement;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class BunnyHop extends Module{

	public BunnyHop() {
		super("Bhop",Keyboard.KEY_Z,Category.MOVEMENT);
	}
	
	@Override
	public void onDisable() {
		mc.gameSettings.keyBindJump.pressed=false;
		super.onDisable();
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.gameSettings.keyBindJump.pressed=true;
		}
	}
	
	
	
}
