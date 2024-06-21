package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModPlayer;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Player extends Module {

    public Player() {
        super("Player", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModPlayer modPlayer=new ModPlayer();
    	
        modPlayer.setEnabled(isToggled());
        
    }
}