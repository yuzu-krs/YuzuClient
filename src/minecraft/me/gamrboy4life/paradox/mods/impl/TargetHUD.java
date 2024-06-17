package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;

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
		target=(EntityLivingBase)mc.pointedEntity;
		if(target!=null) {
			font.drawStringWithShadow(target.getName(), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, -1);
			font.drawStringWithShadow(String.format("%.1f", target.getHealth()) + " \u2764", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2 + font.FONT_HEIGHT, -1);
			GuiInventory.drawEntityOnScreen(pos.getAbsoluteX()+font.getStringWidth(target.getName())+30, pos.getAbsoluteY()+30, 20, 50, 0, target);
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



