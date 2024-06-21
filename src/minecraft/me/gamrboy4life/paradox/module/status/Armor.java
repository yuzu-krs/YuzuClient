package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModArmorStatus;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Armor extends Module {

    public Armor() {
        super("Armor", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModArmorStatus modArmorStatus=new ModArmorStatus();
    	
    	
    	modArmorStatus.setEnabled(isToggled());
        
    }
}