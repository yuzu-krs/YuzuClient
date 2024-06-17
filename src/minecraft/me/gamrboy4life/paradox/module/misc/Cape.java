package me.gamrboy4life.paradox.module.misc;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.renderer.entity.layers.LayerCape;

public class Cape extends Module {

    public Cape() {
        super("Cape", 0, Category.MISC);
    }

    @Override
    public void onRender() {
        LayerCape.setOriginCape(isToggled());
    }
}