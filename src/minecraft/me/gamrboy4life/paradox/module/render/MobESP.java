package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.MobESPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;

public class MobESP extends Module{

	public MobESP() {
		super("MobESP",0,Category.RENDER);
	}
	
	@Override
	public void onRender() {
		if(this.isToggled()) {
			for(Object m:mc.theWorld.loadedEntityList) {
				if(m instanceof EntityMob) {
					MobESPUtils.entityESPBox((Entity)m,0);
				}
			}
		}
	}
	

}
