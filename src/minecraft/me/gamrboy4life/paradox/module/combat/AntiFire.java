package me.gamrboy4life.paradox.module.combat;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AntiFire extends Module{

	public AntiFire() {
		super("AntiFire",0,Category.COMBAT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.isBurning()&&!mc.thePlayer.capabilities.isCreativeMode&&mc.thePlayer.onGround) {
				for(int i=0;i<100;i++) {
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
				}
			}
		}
	}
	
	

	

}
