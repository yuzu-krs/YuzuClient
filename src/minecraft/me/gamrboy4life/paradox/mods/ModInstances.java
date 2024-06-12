package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.gui.hud.HUDManager;
import me.gamrboy4life.paradox.mods.impl.ModArmorStatus;
import me.gamrboy4life.paradox.mods.impl.ModFPS;
import me.gamrboy4life.paradox.mods.impl.ModHelloWorld;

public class ModInstances {
    private static ModHelloWorld modHelloWorld;
    
    private static ModArmorStatus modArmorStatus;
    
    private static ModFPS modFPS;

    public static void register(HUDManager api) {
        modHelloWorld = new ModHelloWorld();
        api.register(modHelloWorld);
        
        modArmorStatus=new ModArmorStatus();
        api.register(modArmorStatus);
        
        modFPS=new ModFPS();
        api.register(modFPS);
    }

    public static ModHelloWorld getModHelloWorld() {
        return modHelloWorld;
    }
    
    
}