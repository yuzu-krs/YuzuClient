package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModYear;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class W_Year extends Module {

    public W_Year() {
        super("Year", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModYear modYear=new ModYear();
		modYear.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}