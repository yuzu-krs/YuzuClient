package me.gamrboy4life.paradox.module.misc;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class SmartDisabler extends Module{

	public SmartDisabler() {
		super("SmartDisabler",0,Category.MISC);
	}
	
	@Override
	public void setup() {
		ArrayList<String> options=new ArrayList<String>();
		options.add("Normal");
		options.add("OnDeath");
		Paradox.instance.settingsManager.rSetting(new Setting("Normal",this,true));
		Paradox.instance.settingsManager.rSetting(new Setting("OnDeath",this,false));
		
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()&& Paradox.instance.settingsManager.getSettingByName("Normal").getValBoolean()) {
			for(Module m:Paradox.instance.moduleManager.getModules()) {
				m.setToggled(false);
			}
		} 
		if(this.isToggled()&&mc.thePlayer.isDead
				&& Paradox.instance.settingsManager.getSettingByName("OnDeath").getValBoolean()) {
			for(Module m:Paradox.instance.moduleManager.getModules()) {
				m.setToggled(false);
			}
		}
	}

}
