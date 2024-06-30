package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.gui.hud.HUDManager;
import me.gamrboy4life.paradox.mods.impl.ModArmorStatus;
import me.gamrboy4life.paradox.mods.impl.ModBossbar;
import me.gamrboy4life.paradox.mods.impl.ModCPS;
import me.gamrboy4life.paradox.mods.impl.ModDate;
import me.gamrboy4life.paradox.mods.impl.ModFPSEffects;
import me.gamrboy4life.paradox.mods.impl.ModGlintColor;
import me.gamrboy4life.paradox.mods.impl.ModKeystrokes;
import me.gamrboy4life.paradox.mods.impl.ModPerspective;
import me.gamrboy4life.paradox.mods.impl.ModPing;
import me.gamrboy4life.paradox.mods.impl.ModPlayer;
import me.gamrboy4life.paradox.mods.impl.ModPotionStatus;
import me.gamrboy4life.paradox.mods.impl.ModTime;
import me.gamrboy4life.paradox.mods.impl.ModXYZ;
import me.gamrboy4life.paradox.mods.impl.ModYear;
import me.gamrboy4life.paradox.mods.impl.SCModuleMemUsage;
import me.gamrboy4life.paradox.mods.impl.TargetHUD;
import me.gamrboy4life.paradox.mods.impl.DirectionMod.DirectionMod;

public class ModInstances {

    
    private static ModArmorStatus modArmorStatus;
    
    private static ModFPSEffects modFPS;
    
    private static ModXYZ modXYZ;
    
    private static ModKeystrokes modKeystrokes;
    
    private static ModCPS modCPS;
    
    private static ModPerspective modPerspective;
    
    private static ModGlintColor glintColor;
    
    private static TargetHUD targetHUD;
    
    private static ModPing modPing;
    
    private static ModPlayer modPlayer;
    
    private static ModPotionStatus modPotionStatus;
    
    private static ModBossbar modBossbar;
    
    private static SCModuleMemUsage modMem;
    
    private static ModYear modYear;
    
    private static ModDate modDate;
    
    private static ModTime modTime;
    
    private static DirectionMod direction;
    


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
        
        modPing=new ModPing();
        api.register(modPing);
        
        modPlayer=new ModPlayer();
        api.register(modPlayer);
        
        modPotionStatus=new ModPotionStatus();
        api.register(modPotionStatus);
        
        modBossbar=new ModBossbar();
        api.register(modBossbar);
        
        modMem=new SCModuleMemUsage();
        api.register(modMem);
        
        modYear=new ModYear();
        api.register(modYear);
        
    	modDate=new ModDate();
		api.register(modDate);
        
		modTime=new ModTime();
		api.register(modTime);
		
		direction=new DirectionMod();
		api.register(direction);
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