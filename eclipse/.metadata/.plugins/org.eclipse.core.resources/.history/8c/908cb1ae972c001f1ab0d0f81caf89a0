package me.gamrboy4life.paradox.module.misc;

import me.gamrboy4life.paradox.cosmetics.CosmeticController;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Hat extends Module {

    public Hat() {
        super("Hat", 0, Category.MISC);
    }

    @Override
    public void onRender() {
        // モジュールがトグルされている場合にのみ帽子を表示する
        CosmeticController.setShouldRenderTopHat(this.isToggled());
    }
}