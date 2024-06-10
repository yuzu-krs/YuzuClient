package me.gamrboy4life.paradox.module.combat;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Regen extends Module{

	public Regen() {
		super("Regen",0,Category.COMBAT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()){
			EntityPlayerSP p=player();
			if(!mc.playerController.isInCreativeMode()&&p.getFoodStats().getFoodLevel()>17&&p.getHealth()<20&&p.getHealth()!=0&&p.onGround) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
			}
		}
	}
	
}
