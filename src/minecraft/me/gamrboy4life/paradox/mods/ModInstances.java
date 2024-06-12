package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.gui.hud.HUDManager;
import me.gamrboy4life.paradox.mods.impl.ModHelloWorld;

public class ModInstances {
    private static ModHelloWorld modHelloWorld;

    public static void register(HUDManager api) {
        modHelloWorld = new ModHelloWorld();
        api.register(modHelloWorld);
    }

    public static ModHelloWorld getModHelloWorld() {
        return modHelloWorld;
    }
}