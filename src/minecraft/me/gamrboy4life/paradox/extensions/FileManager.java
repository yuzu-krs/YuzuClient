package me.gamrboy4life.paradox.extensions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.module.misc.Panic;
import me.gamrboy4life.paradox.module.render.ClickGui;
import me.gamrboy4life.paradox.utils.JsonUtils;

public class FileManager {
	public static File ROOT_DIR=new File("yuzuclient");
	public static File modules=new File(ROOT_DIR,"modules.json");
	
	public void init() {
		//Make Root Directory
		if(!ROOT_DIR.exists()) {ROOT_DIR.mkdirs();}
	
		//Handles Module
		if(!modules.exists())
			saveMods();
		else
			loadMods();

	}
	
	//保存したいハック
	public void saveMods() {
		try {
			JsonObject json=new JsonObject();
			for(Module mod:Paradox.instance.moduleManager.getModules()) {
				JsonObject jsonMod=new JsonObject();
				jsonMod.addProperty("enabled",mod.isToggled());
				json.add(mod.getName(), jsonMod);
			}
			PrintWriter save=new PrintWriter(new FileWriter(modules));
			save.println(JsonUtils.prettyGson.toJson(json));
			save.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//保存したくないハック
	//ClickGuiは標準で表示する
	private HashSet<String> modBlackList=Sets.newHashSet(ClickGui.class.getName(),Panic.class.getName());
	
	public boolean isModBlackListed(Module m) {
		return modBlackList.contains(m.getClass().getName());
	}
	
	//読み取り
	public void loadMods() {
		try {
			BufferedReader load=new BufferedReader(new FileReader(modules));
			JsonObject json=(JsonObject)JsonUtils.jsonParser.parse(load);
			load.close();
			Iterator<Entry<String,JsonElement>> itr=json.entrySet().iterator();
			while(itr.hasNext()) {
				Entry<String,JsonElement> entry=itr.next();
				Module mod=Paradox.instance.moduleManager.getModuleByName(entry.getKey());
				if(mod!=null&&!modBlackList.contains(mod.getClass().getName())) {
					JsonObject jsonModule=(JsonObject)entry.getValue();
					boolean enabled=jsonModule.get("enabled").getAsBoolean();
					if(enabled) {
						mod.toggle();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
