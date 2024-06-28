package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModDate;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class W_Date extends Module {

    public W_Date() {
        super("Date", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModDate modDate=new ModDate();
		modDate.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}