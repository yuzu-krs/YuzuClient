package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModPotionStatus;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Potion extends Module {

    public Potion() {
        super("Potion", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModPotionStatus modPotionStatus=new ModPotionStatus();
        
		modPotionStatus.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}