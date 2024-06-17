package me.gamrboy4life.paradox.module.movement;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Speed extends Module{

	public Speed() {
		super("Speed",0,Category.MOVEMENT);
	}
	
	
	private double speed=Paradox.instance.settingsManager.getSettingByName("Run Speed").getValDouble();
	
	@Override
	public void setup() {
		ArrayList<String> options=new ArrayList<String>();
		options.add("Run Speed");
		Paradox.instance.settingsManager.rSetting(new Setting("Run Speed",this,speed,1,20,false));
	}
	
	
	public void onUpdate() {
		if(this.isToggled()) {
			for(int i=0;i<Paradox.instance.settingsManager.getSettingByName("Run Speed").getValDouble();i++) {
				if(mc.thePlayer.onGround) {
					mc.thePlayer.motionX *=1.1f;
					mc.thePlayer.motionZ *=1.1f;
				}
			}
		}
	}
	

}
