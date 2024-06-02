package me.gamrboy4life.paradox.module;

import java.util.ArrayList;

import me.gamrboy4life.paradox.module.combat.FastBow;
import me.gamrboy4life.paradox.module.combat.KillAura;
import me.gamrboy4life.paradox.module.movement.AutoSprint;

public class ModuleManager {
	
	private static ArrayList<Module> mods;
	
	public ModuleManager() {
		mods=new ArrayList<Module>();
		
		//COMBAT
		newMod(new FastBow());
		newMod(new KillAura());
		
		//MOVEMENT
		newMod(new AutoSprint());
		
		//RENDER
		
		//MISC
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
}
