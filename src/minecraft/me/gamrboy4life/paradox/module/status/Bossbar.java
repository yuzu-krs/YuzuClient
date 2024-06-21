package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModBossbar;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Bossbar extends Module {

    public Bossbar() {
        super("Bossbar", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModBossbar modbossbar=new ModBossbar();
    	
    	
        modbossbar.setEnabled(isToggled());
        
    }
}