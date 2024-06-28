package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModTime;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class W_Time extends Module {

    public W_Time() {
        super("Time", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModTime modTime=new ModTime();
		modTime.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}