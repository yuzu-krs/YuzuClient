package me.gamrboy4life.paradox;

import org.lwjgl.opengl.Display;

import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import me.gamrboy4life.paradox.extensions.DiscordRP;
import me.gamrboy4life.paradox.module.ModuleManager;

public class Paradox {
	
	public static Paradox instance=new Paradox();
	
	public static String name="YuzuClient",version="1.0",creator="Yuzu";
	
	public static SettingsManager settingsManager;
	public static ModuleManager moduleManager;
	public static ClickGUI clickGUI;
	
	public static DiscordRP discordRP=new DiscordRP();
	
	public static void startClient() {
		settingsManager=new SettingsManager();
		moduleManager=new ModuleManager();
		clickGUI=new ClickGUI();
		discordRP.start();
		
		
		
		
		
		Display.setTitle(name+" "+version+" by "+creator);
	}
	
	public static DiscordRP getDiscordRP() {
		return discordRP;
	}

}
