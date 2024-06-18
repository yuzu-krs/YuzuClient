package me.gamrboy4life.paradox.module.misc;

import me.gamrboy4life.paradox.devlogs.cosmetic.CosmeticBoolean; // Import the CosmeticBoolean class
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class GifCape extends Module {

    public GifCape() {
        super("GifCape", 0, Category.MISC);
    }

    @Override
    public void onRender() {
    	CosmeticBoolean.setSnowCape(isToggled());

    }
}