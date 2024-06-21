package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModPing;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Ping extends Module {

    public Ping() {
        super("Ping", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModPing modPing=new ModPing();
    	
    	
        modPing.setEnabled(isToggled());
        
    }
}