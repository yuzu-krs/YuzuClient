package me.gamrboy4life.paradox;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import me.gamrboy4life.paradox.alt.AltManager;
import me.gamrboy4life.paradox.command.CommandManager;
import me.gamrboy4life.paradox.event.Event;
import me.gamrboy4life.paradox.event.events.EventChat;
import me.gamrboy4life.paradox.extensions.DiscordRP;
import me.gamrboy4life.paradox.extensions.FileManager;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.module.ModuleManager;
import me.gamrboy4life.paradox.module.render.TabGui;

public class Paradox {
	
	public static Paradox instance=new Paradox();
	
	public static String name="YuzuClient",version="1.0",creator="Yuzu";
	
	public static SettingsManager settingsManager;
	public static ModuleManager moduleManager;
	public static ClickGUI clickGUI;
	public static AltManager altManager;
	public static TabGui hud;
	public static CommandManager cmdManager;
	public static FileManager fileManager;
	
	public static DiscordRP discordRP=new DiscordRP();
	public static CopyOnWriteArrayList<Module> modules=new CopyOnWriteArrayList<Module>();
	
	public static void startClient() {
		settingsManager=new SettingsManager();
		moduleManager=new ModuleManager();
		clickGUI=new ClickGUI();
		altManager=new AltManager();
		hud=new TabGui();
		cmdManager=new CommandManager();
		fileManager = new FileManager();
		
		
		fileManager.init();
		
		discordRP.start();
		
		
		
		
		
		Display.setTitle(name+" "+version+" by "+creator);
	}
	
	public static void stopClient() {
		Paradox.instance.fileManager.saveMods();
	}
	
	public static void onEvent(Event e) {
		if(e instanceof EventChat) {
			cmdManager.handleChat((EventChat)e);
		}
		for(Module m:modules) {
			if(!m.toggled)
				continue;
			m.onEvent(e);
		}
	}
	
	public static DiscordRP getDiscordRP() {
		return discordRP;
	}

}
