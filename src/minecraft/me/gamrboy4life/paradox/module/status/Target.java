package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.TargetHUD;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Target extends Module {

    public Target() {
        super("Target", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	TargetHUD targetHUD=new TargetHUD();
		targetHUD.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}