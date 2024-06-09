package me.gamrboy4life.paradox.module;

import me.gamrboy4life.paradox.event.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.Packet;

public class Module {
	
	protected Minecraft mc=Minecraft.getMinecraft();
	
	public String name;
	private int key;
	public boolean toggled;
	Category category;
	
	public Module(String nm,int k,Category c) {
		name=nm;
		key=k;
		category=c;
		toggled=false;
		setup();
	}
	
	public void toggle() {
		toggled=!toggled;
		if(toggled) {
			onEnable();
		}else {
			onDisable();
		}
	}
	
	public void onEnable() {}
	public void onDisable() {}
	public void onUpdate() {}
	public void onRender() {}
	public void setup() {}
	
	public void onEvent(Event e) {
		
	}

	public Minecraft getMc() {
		return mc;
	}

	public void setMc(Minecraft mc) {
		this.mc = mc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category=category;
	}
	
	protected EntityPlayerSP player() {
		return mc.thePlayer;
	}
	
	protected PlayerControllerMP playerController() {
		return mc.playerController;
	}
	
	protected WorldClient world() {
		return mc.theWorld;
	}
	
	@SuppressWarnings("rawtypes")
	protected void sendPacket(Packet p){
		player().sendQueue.addToSendQueue(p);
	}
	
	public void enableOnStartUp() {
		toggled=true;
		try {
			toggle();
			onEnable();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	


}
