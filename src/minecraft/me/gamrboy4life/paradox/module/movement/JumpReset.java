package me.gamrboy4life.paradox.module.movement;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class JumpReset extends Module{
	
	

    public JumpReset() {
        super("JumpReset", 0, Category.MOVEMENT);
    }
    
    @Override
    public void onUpdate() {
        if (this.isToggled() && mc.thePlayer.hurtTime>8) {
        	System.out.println(mc.thePlayer.hurtTime);
            //mc.gameSettings.keyBindJump.pressed = true;
        	mc.thePlayer.jump();
            
        }
    }
}