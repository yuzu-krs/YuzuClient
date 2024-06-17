package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;

public class TargetHUD extends ModDraggable{

	EntityLivingBase target;
	
	@Override
	public int getWidth() {
		return 100;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT*2+1;
		
	}

	@Override
	public void render(ScreenPosition pos) {
		
		if(!(mc.pointedEntity instanceof EntityItemFrame)) {
			target=(EntityLivingBase)mc.pointedEntity;
		
			if(target!=null) {
				font.drawStringWithShadow(target.getName(), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, -1);
				GuiInventory.drawEntityOnScreen(pos.getAbsoluteX()+font.getStringWidth(target.getName())+30, pos.getAbsoluteY()+30, 20, 50, 0, target);
				//自分の体力とターゲットの体力の比較
				if(target.getHealth()>mc.thePlayer.getHealth()) {
					font.drawStringWithShadow(String.format("§c"+"%.1f", target.getHealth()) + " \u2764", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2 + font.FONT_HEIGHT, -1);
					
				}else {
					
					font.drawStringWithShadow(String.format("§a"+"%.1f", target.getHealth()) + " \u2764", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2 + font.FONT_HEIGHT, -1);				
				}
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawStringWithShadow(mc.thePlayer.getName(), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, -1);
		font.drawStringWithShadow((int)mc.thePlayer.getHealth()+" \u2764", pos.getAbsoluteX()+2, pos.getAbsoluteY()+2+font.FONT_HEIGHT, -1);
		GuiInventory.drawEntityOnScreen(pos.getAbsoluteX()+font.getStringWidth(mc.thePlayer.getName())+30, pos.getAbsoluteY()+30, 20, 50, 0, mc.thePlayer);
	}
	
	
		
	
	@Override
	public boolean isEnabled() {
		return true;
	}


}



