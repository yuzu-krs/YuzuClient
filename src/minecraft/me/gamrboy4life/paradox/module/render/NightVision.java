package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class NightVision extends Module{

	public NightVision() {
		super("NightVision",0,Category.RENDER);
	}
	
	@Override
    public void onEnable() {
        mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 0));
    }

    @Override
    public void onDisable() {
        mc.thePlayer.removePotionEffect(Potion.nightVision.id);
    }
	
	@Override
	public void onRender() {
		if(this.isToggled()) {
			mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id,Integer.MAX_VALUE,0));
		}
	}
}
