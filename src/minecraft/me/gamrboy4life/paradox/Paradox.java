package me.gamrboy4life.paradox;

import org.lwjgl.opengl.Display;

import me.gamrboy4life.paradox.module.ModuleManager;

public class Paradox {
	
	public static Paradox instance=new Paradox();
	
	public static String name="YuzuClient",version="1.0",creator="Yuzu";
	
	public static ModuleManager moduleManager;
	
	public static void startClient() {
		moduleManager=new ModuleManager();
		
		
		
		
		Display.setTitle(name+" "+version+" by "+creator);
	}

}
