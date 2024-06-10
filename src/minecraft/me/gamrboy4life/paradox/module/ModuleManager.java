package me.gamrboy4life.paradox.module;

import java.util.ArrayList;
import java.util.List;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.combat.AntiFire;
import me.gamrboy4life.paradox.module.combat.AntiPotion;
import me.gamrboy4life.paradox.module.combat.AutoClicker;
import me.gamrboy4life.paradox.module.combat.AutoSword;
import me.gamrboy4life.paradox.module.combat.Criticals;
import me.gamrboy4life.paradox.module.combat.FastBow;
import me.gamrboy4life.paradox.module.combat.KillAura;
import me.gamrboy4life.paradox.module.combat.MineClicker;
import me.gamrboy4life.paradox.module.combat.Regen;
import me.gamrboy4life.paradox.module.misc.ChannelIcon;
import me.gamrboy4life.paradox.module.misc.SmartDisabler;
import me.gamrboy4life.paradox.module.movement.AutoSprint;
import me.gamrboy4life.paradox.module.movement.AutoWalk;
import me.gamrboy4life.paradox.module.movement.Dolphin;
import me.gamrboy4life.paradox.module.movement.Flight;
import me.gamrboy4life.paradox.module.movement.Glide;
import me.gamrboy4life.paradox.module.movement.Jetpack;
import me.gamrboy4life.paradox.module.movement.JumpReset;
import me.gamrboy4life.paradox.module.movement.NoFall;
import me.gamrboy4life.paradox.module.movement.Parkour;
import me.gamrboy4life.paradox.module.movement.Scaffold;
import me.gamrboy4life.paradox.module.movement.Sneak;
import me.gamrboy4life.paradox.module.movement.Speed;
import me.gamrboy4life.paradox.module.movement.Spider;
import me.gamrboy4life.paradox.module.movement.Step;
import me.gamrboy4life.paradox.module.player.AntiAFK;
import me.gamrboy4life.paradox.module.player.AntiCobweb;
import me.gamrboy4life.paradox.module.player.AutoMine;
import me.gamrboy4life.paradox.module.player.AutoRespawn;
import me.gamrboy4life.paradox.module.player.Bhop;
import me.gamrboy4life.paradox.module.player.ChestStealer;
import me.gamrboy4life.paradox.module.player.FastLadder;
import me.gamrboy4life.paradox.module.player.FastPlace;
import me.gamrboy4life.paradox.module.render.BedWarsXray;
import me.gamrboy4life.paradox.module.render.ClickGui;
import me.gamrboy4life.paradox.module.render.ESP;
import me.gamrboy4life.paradox.module.render.FullBright;
import me.gamrboy4life.paradox.module.render.TabGui;
import me.gamrboy4life.paradox.module.render.XYZ;
import me.gamrboy4life.paradox.module.render.Xray;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ModuleManager {
	
	private static ArrayList<Module> mods;
	
	public ModuleManager() {
		mods=new ArrayList<Module>();
		newMod(new TabGui());

		
		//COMBAT
		newMod(new AntiFire());
		newMod(new AntiPotion());
		newMod(new AutoClicker());
		newMod(new AutoSword());
		newMod(new Criticals());
		newMod(new FastBow());
		newMod(new KillAura());
		newMod(new MineClicker());
		newMod(new Regen());
		
		//MOVEMENT
		newMod(new AutoSprint());
		newMod(new AutoWalk());
		newMod(new Dolphin());
		newMod(new Flight());
		newMod(new Glide());
		newMod(new Jetpack());
		newMod(new JumpReset());
		newMod(new NoFall());
		newMod(new Parkour());
		newMod(new Scaffold());
		newMod(new Sneak());
		newMod(new Speed());
		newMod(new Spider());
		newMod(new Step());
		
		//PLAYER
		newMod(new AntiAFK());
		newMod(new AntiCobweb());
		newMod(new AutoMine());
		newMod(new AutoRespawn());
		newMod(new Bhop());
		newMod(new ChestStealer());
		newMod(new FastLadder());
		newMod(new FastPlace());
		
		//RENDER
		newMod(new BedWarsXray());
		newMod(new ClickGui());
		newMod(new ESP());
		newMod(new FullBright());
		newMod(new Xray());
		newMod(new XYZ());
		
		
		
		//MISC
		newMod(new ChannelIcon());
		newMod(new SmartDisabler());
	}
	
	
	public static List<Module> getModulesbyCategory(Category c){
		List<Module> modules=new ArrayList<Module>();
		
		for(Module m:Paradox.instance.moduleManager.getModules()) {
			if(m.category==c) {
				modules.add(m);
			}
		}
		return modules;
		
	}
	
	
	
	public static void newMod(Module m) {
		mods.add(m);
	}
	
	public static ArrayList<Module> getModules(){
		return mods;
	}
	
	public static void onUpdate() {
		for(Module m:mods) {
			m.onUpdate();
		}
	}
	
	public static void onRender() {
		for(Module m:mods) {
			m.onRender();
		}
	}
	
	public static void onKey(int k) {
		for(Module m:mods) {
			if(m.getKey()==k) {
				m.toggle();
			}
		}
	}


	public static void addChatMessage(String message) {
		message="\u00A7e"+Paradox.name+"\2477: "+message;
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
	public static Module getModuleByName(String moduleName) {
		for(Module m:Paradox.instance.moduleManager.getModules()) {
			if(!m.getName().trim().equalsIgnoreCase(moduleName)&&!m.toString().equalsIgnoreCase(moduleName.trim())) continue;
			return m;
		}
		return null;
	}
	
	
	
	
	
	
	
	
}
