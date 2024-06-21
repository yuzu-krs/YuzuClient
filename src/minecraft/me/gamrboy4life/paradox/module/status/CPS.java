package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModCPS;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class CPS extends Module {

    public CPS() {
        super("CPS", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModCPS modCPS=new ModCPS();
    	
    	
        modCPS.setEnabled(isToggled());
        
    }
}