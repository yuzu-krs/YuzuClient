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
import me.gamrboy4life.paradox.gui.SplashProgress;
import me.gamrboy4life.paradox.gui.hud.HUDConfigScreen;
import me.gamrboy4life.paradox.gui.hud.HUDManager;
import me.gamrboy4life.paradox.gvent.GventManager;
import me.gamrboy4life.paradox.gvent.GventTarget;
import me.gamrboy4life.paradox.gvent.impl.ClientTickEvent;
import me.gamrboy4life.paradox.mods.ModInstances;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.module.ModuleManager;
import me.gamrboy4life.paradox.module.render.TabGui;
import me.gamrboy4life.paradox.utils.font.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class Paradox {
    
    public static Paradox instance = new Paradox();
    
    public static String name = "YuzuClient", version = "3.0", creator = "Yuzu";
    
    public static SettingsManager settingsManager;
    public static ModuleManager moduleManager;
    public static ClickGUI clickGUI;
    public static AltManager altManager;
    public static TabGui hud;
    public static CommandManager cmdManager;
    public static FileManager fileManager;
    
    public boolean hasSent=false;
    
    public static DiscordRP discordRP = new DiscordRP();
    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
    public static HUDManager hudManager;
    
    public static final Paradox INSTANCE = new Paradox();
    
    public static final Paradox getInstance() {
        return INSTANCE;
    }
    
    //public static KeyBinding CLIENT_GUI_MOD_POS = new KeyBinding("Mod Positioning", Keyboard.KEY_INSERT, "ClientName");
    
    public static void startClient() {
        
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        clickGUI = new ClickGUI();
        altManager = new AltManager();
        hud = new TabGui();
        cmdManager = new CommandManager();
        fileManager = new FileManager();
        
        hudManager = HUDManager.getInstance();
        ModInstances.register(hudManager);
        GventManager.register(instance); // GventManager に Paradox インスタンスを登録

        fileManager.init();
        GileManager.init();
        
        FontUtil.bootstrap();
        
        
  
        SplashProgress.setProgress(8, "YuzuClient - Discord RP");        
        discordRP.start();

        Display.setTitle(name + " " + version + " by " + creator);
        
       
    }
    
    public static void stopClient() {
        Paradox.instance.fileManager.saveMods();
    }
    
    public static void onEvent(Event e) {
        if (e instanceof EventChat) {
            cmdManager.handleChat((EventChat)e);
        }
        for (Module m : modules) {
            if (!m.toggled)
                continue;
            m.onEvent(e);
        }
    }
    
    public static DiscordRP getDiscordRP() {
        return discordRP;
    }
    
    @GventTarget
    public void onTick(ClientTickEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        KeyBinding CLIENT_GUI_MOD_POS = mc.gameSettings.CLIENT_GUI_MOD_POS;
        // GUI が開かれておらず、かつ T キーが押されたときに HUDConfigScreen を開く
        if (mc.currentScreen == null && CLIENT_GUI_MOD_POS.isPressed()) {
            mc.displayGuiScreen(new HUDConfigScreen(hudManager));
        }
    }


}