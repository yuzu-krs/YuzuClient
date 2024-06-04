package de.Hero.example;

import me.deinclient.modules.Module;
import me.deinclient.modules.ModuleMgr;
import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;

/**  	   \/  \/  \/  \/  \/  \/  \/  \/  \/  \/ 
 *  ---->   This is just how your client main class <-----
 *  ---->   should look like 						<-----
 *  	   /\  /\  /\  /\  /\  /\  /\  /\  /\  /\   
 *  
 *  @author HeroCode
 */
public class ClientMain {
	public static SettingsManager setmgr;
	public static ModuleMgr modulemgr;
	public static ClickGUI clickgui;
	
	public static void setupClient(){
		//SettingsMgr zuerst 
		setmgr = new SettingsManager();
		//Dann modulemgr
		modulemgr = new ModuleMgr();
		modulemgr.rM(new GUI()); //Das würde natürlich in der Klasse des ModuleMgr passieren ;)
		//modulemgr.rM(new ...); und so weiter
		//Zu letzt das ClickGUI setzen
		clickgui = new ClickGUI();
	}
}
