package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModFPSEffects;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class FPS extends Module {

    public FPS() {
        super("FPS", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModFPSEffects fps=new ModFPSEffects();
    	
    	
        fps.setEnabled(isToggled());
        
    }
}