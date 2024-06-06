package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.MobESPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerESP extends Module{

	public PlayerESP() {
		super("PlayerESP",0,Category.RENDER);
	}
	
	@Override
	public void onRender() {
		if(this.isToggled()) {
			for(Object p:mc.theWorld.loadedEntityList) {
				if(p instanceof EntityPlayer) {
					MobESPUtils.entityESPBox((Entity)p,0);
				}
			}
		}
	}
	

}