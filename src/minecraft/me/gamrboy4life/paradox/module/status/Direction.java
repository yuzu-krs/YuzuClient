package me.gamrboy4life.paradox.module.status;


import me.gamrboy4life.paradox.mods.impl.DirectionMod.DirectionMod;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Direction extends Module {

    public Direction() {
        super("Direction", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	DirectionMod direction=new DirectionMod();
		direction.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}