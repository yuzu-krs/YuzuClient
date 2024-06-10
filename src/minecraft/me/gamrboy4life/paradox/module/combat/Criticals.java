package me.gamrboy4life.paradox.module.combat;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Criticals extends Module{

	public Criticals() {
		super("Criticals",0,Category.COMBAT);
	}
	
	@Override
	public void setup() {
		ArrayList<String> options=new ArrayList<String>();
		options.add("Jump");
		options.add("Packet");
		Paradox.instance.settingsManager.rSetting(new Setting("Jump",this,true));
		Paradox.instance.settingsManager.rSetting(new Setting("Packet",this,false));
	}
	
	@Override
	public void onUpdate() {
		
		if(this.isToggled()&&Paradox.instance.settingsManager.getSettingByName("Jump").getValBoolean()) {
			if(mc.objectMouseOver!=null && mc.objectMouseOver.entityHit instanceof EntityLivingBase)
				doJumpCriticals();
		}
		if(this.isToggled()&&Paradox.instance.settingsManager.getSettingByName("Packet").getValBoolean()) {
			if(mc.objectMouseOver!=null&&mc.objectMouseOver.entityHit instanceof EntityLivingBase)
				doPacketCriticals();
		}
	}
	
	public void doJumpCriticals() {
		if(!mc.thePlayer.isInWater()&&!mc.thePlayer.isInsideOfMaterial(Material.lava)&&mc.thePlayer.onGround) {
			mc.thePlayer.motionY=0.1f;
			mc.thePlayer.fallDistance=0.1f;
			mc.thePlayer.onGround=false;
		}
	}
	
	public void doPacketCriticals() {
		if(!mc.thePlayer.isInWater()&&!mc.thePlayer.isInsideOfMaterial(Material.lava)&&mc.thePlayer.onGround) {
			double posX=mc.thePlayer.posX;
			double posY=mc.thePlayer.posY;
			double posZ=mc.thePlayer.posZ;
			
			NetHandlerPlayClient sendQueue=mc.thePlayer.sendQueue;
			
			sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX,posY+0.0625D,posZ,true));
			sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX,posY,posZ,false));
			sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX,posY+1.1E-5D,posZ,false));
			sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX,posY,posZ,false));
			
		}
	}
	
	

}















