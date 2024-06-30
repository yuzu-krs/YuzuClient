package me.gamrboy4life.paradox.module;

import java.util.ArrayList;
import java.util.List;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.mods.impl.EnchantEffect;
import me.gamrboy4life.paradox.module.combat.AntiFire;
import me.gamrboy4life.paradox.module.combat.AntiPotion;
import me.gamrboy4life.paradox.module.combat.AutoClicker;
import me.gamrboy4life.paradox.module.combat.AutoSword;
import me.gamrboy4life.paradox.module.combat.Criticals;
import me.gamrboy4life.paradox.module.combat.FastBow;
import me.gamrboy4life.paradox.module.combat.KillAura;
import me.gamrboy4life.paradox.module.combat.MineClicker;
import me.gamrboy4life.paradox.module.combat.Reach;
import me.gamrboy4life.paradox.module.combat.Regen;
import me.gamrboy4life.paradox.module.misc.Cape;
import me.gamrboy4life.paradox.module.misc.ChannelIcon;
import me.gamrboy4life.paradox.module.misc.DeadPanic;
import me.gamrboy4life.paradox.module.misc.Eyes;
import me.gamrboy4life.paradox.module.misc.GifCape;
import me.gamrboy4life.paradox.module.misc.Hat;
import me.gamrboy4life.paradox.module.misc.Panic;
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
import me.gamrboy4life.paradox.module.player.InventoryHelper;
import me.gamrboy4life.paradox.module.render.BedWarsXray;
import me.gamrboy4life.paradox.module.render.BlockOverlay;
import me.gamrboy4life.paradox.module.render.ClickGui;
import me.gamrboy4life.paradox.module.render.EspChest;
import me.gamrboy4life.paradox.module.render.EspFriendly;
import me.gamrboy4life.paradox.module.render.EspHostile;
import me.gamrboy4life.paradox.module.render.EspPlayer;
import me.gamrboy4life.paradox.module.render.FullBright;
import me.gamrboy4life.paradox.module.render.TabGui;
import me.gamrboy4life.paradox.module.render.Xray;
import me.gamrboy4life.paradox.module.status.Armor;
import me.gamrboy4life.paradox.module.status.Bossbar;
import me.gamrboy4life.paradox.module.status.CPS;
import me.gamrboy4life.paradox.module.status.Direction;
import me.gamrboy4life.paradox.module.status.FPS;
import me.gamrboy4life.paradox.module.status.Keystrokes;
import me.gamrboy4life.paradox.module.status.Mem;
import me.gamrboy4life.paradox.module.status.Ping;
import me.gamrboy4life.paradox.module.status.Player;
import me.gamrboy4life.paradox.module.status.Potion;
import me.gamrboy4life.paradox.module.status.Target;
import me.gamrboy4life.paradox.module.status.W_Date;
import me.gamrboy4life.paradox.module.status.W_Time;
import me.gamrboy4life.paradox.module.status.W_Year;
import me.gamrboy4life.paradox.module.status.XYZ;
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
		newMod(new Reach());
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
		newMod(new InventoryHelper());
		
		//PVP系
		newMod(new Armor());
		newMod(new Bossbar());
		newMod(new CPS());
		newMod(new Direction());
		newMod(new FPS());
		newMod(new Keystrokes());
		newMod(new Mem());
		newMod(new Ping());
		newMod(new Player());
		newMod(new Potion());
		newMod(new Target());
		newMod(new XYZ());
		newMod(new W_Year());
		newMod(new W_Date());
		newMod(new W_Time());
		
		//RENDER
		newMod(new BedWarsXray());
		newMod(new BlockOverlay());
		newMod(new ClickGui());
		newMod(new EspChest());
		newMod(new EspFriendly());
		newMod(new EspHostile());
		newMod(new EspPlayer());
		newMod(new FullBright());
		newMod(new Xray());
		
		//MISC
		newMod(new Cape());
		newMod(new ChannelIcon());
		newMod(new DeadPanic());
		newMod(new EnchantEffect());
		newMod(new Eyes());
		newMod(new GifCape());
		newMod(new Hat());
		newMod(new Panic());
		
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
