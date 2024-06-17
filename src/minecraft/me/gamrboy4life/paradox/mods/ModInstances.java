package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.gui.hud.HUDManager;
import me.gamrboy4life.paradox.mods.impl.ModArmorStatus;
import me.gamrboy4life.paradox.mods.impl.ModCPS;
import me.gamrboy4life.paradox.mods.impl.ModFPSEffects;
import me.gamrboy4life.paradox.mods.impl.ModGlintColor;
import me.gamrboy4life.paradox.mods.impl.ModKeystrokes;
import me.gamrboy4life.paradox.mods.impl.ModPerspective;
import me.gamrboy4life.paradox.mods.impl.ModXYZ;
import me.gamrboy4life.paradox.mods.impl.TargetHUD;

public class ModInstances {

    
    private static ModArmorStatus modArmorStatus;
    
    private static ModFPSEffects modFPS;
    
    private static ModXYZ modXYZ;
    
    private static ModKeystrokes modKeystrokes;
    
    private static ModCPS modCPS;
    
    private static ModPerspective modPerspective;
    
    private static ModGlintColor glintColor;
    
    private static TargetHUD targetHUD;

    public static void register(HUDManager api) {

        
        modArmorStatus=new ModArmorStatus();
        api.register(modArmorStatus);
        
        modFPS=new ModFPSEffects();
        api.register(modFPS);
        
        modXYZ=new ModXYZ();
        api.register(modXYZ);
        
        modKeystrokes=new ModKeystrokes();
        api.register(modKeystrokes);
        
        modCPS=new ModCPS();
        api.register(modCPS);
        
        modPerspective=new ModPerspective();
        api.register(modPerspective);
        
        glintColor = new ModGlintColor();
        
        targetHUD=new TargetHUD();
        api.register(targetHUD);
    }


    
    public static ModPerspective getModPerspective() {
    	return modPerspective;
    }
    
    public static ModGlintColor getModGlintColor() {
    	return glintColor;
    }
    
    public static TargetHUD gettargetHUD() {
    	return targetHUD;
    }
    
    
}