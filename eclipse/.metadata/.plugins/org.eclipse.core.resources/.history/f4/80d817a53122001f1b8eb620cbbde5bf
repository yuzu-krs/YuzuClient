package de.Hero.example;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;

//Deine Imports
import me.deinclient.Client;
import me.deinclient.modules.Category;
import me.deinclient.modules.Module;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class GUI extends Module{

    public GUI()
    {
        super("ClickGUI", Keyboard.KEY_T, Category.HIDDEN, 0xff0B21D6);
    }

    //Setup is called in the Module con
    @Override
    public void setup(){
    	ArrayList<String> options = new ArrayList<>();
    	options.add("JellyLike");
    	options.add("New");
    	Client.setmgr.rSetting(new Setting("Design", this, "New", options));
    	Client.setmgr.rSetting(new Setting("Sound", this, false));
    	Client.setmgr.rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
    	Client.setmgr.rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
    	Client.setmgr.rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
    }
    
    @Override
    public void onEnable()
    {
    	/**
    	 * Einfach in der StartMethode
    	 * clickgui = new ClickGUI(); ;)
    	 */
    	mc.displayGuiScreen(Client.clickgui);
    	toggle();
    	super.onEnable();
    }
}
