package me.gamrboy4life.paradox.module.misc;

import me.gamrboy4life.paradox.cosmetics.CosmeticController;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Eyes extends Module {

    public Eyes() {
        super("Eyes", 0, Category.MISC);
    }

    @Override
    public void onRender() {
        CosmeticController.setShouldRenderTopEyes(this.isToggled());
    }
}
