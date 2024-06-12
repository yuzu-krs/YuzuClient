package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.gui.hud.HUDManager;
import me.gamrboy4life.paradox.mods.impl.ModArmorStatus;
import me.gamrboy4life.paradox.mods.impl.ModFPSEffects;
import me.gamrboy4life.paradox.mods.impl.ModHelloWorld;
import me.gamrboy4life.paradox.mods.impl.ModKeystrokes;
import me.gamrboy4life.paradox.mods.impl.ModXYZ;

public class ModInstances {
    private static ModHelloWorld modHelloWorld;
    
    private static ModArmorStatus modArmorStatus;
    
    private static ModFPSEffects modFPS;
    
    private static ModXYZ modXYZ;
    
    private static ModKeystrokes modKeystrokes;

    public static void register(HUDManager api) {
        modHelloWorld = new ModHelloWorld();
        api.register(modHelloWorld);
        
        modArmorStatus=new ModArmorStatus();
        api.register(modArmorStatus);
        
        modFPS=new ModFPSEffects();
        api.register(modFPS);
        
        modXYZ=new ModXYZ();
        api.register(modXYZ);
        
        modKeystrokes=new ModKeystrokes();
        api.register(modKeystrokes);
    }

    public static ModHelloWorld getModHelloWorld() {
        return modHelloWorld;
    }
    
    
}