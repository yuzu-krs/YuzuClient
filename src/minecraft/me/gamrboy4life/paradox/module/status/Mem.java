package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.SCModuleMemUsage;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Mem extends Module {

    public Mem() {
        super("MEM", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	SCModuleMemUsage mem=new SCModuleMemUsage();
        
		mem.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}