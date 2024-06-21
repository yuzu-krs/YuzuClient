package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModXYZ;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class XYZ extends Module {

    public XYZ() {
        super("XYZ", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
        ModXYZ modXYZ = new ModXYZ();
		modXYZ.setEnabled(isToggled()); // 静的メソッドを呼び出す
    }
}