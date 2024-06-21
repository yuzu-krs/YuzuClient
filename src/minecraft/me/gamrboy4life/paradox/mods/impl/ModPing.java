package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;

public class ModPing extends ModDraggable{

	@Override
	public int getWidth() {
		// TODO 自動生成されたメソッド・スタブ
		return font.getStringWidth("ping: 100");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID())!=null) {
			font.drawString("ping: "+mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime(),pos.getAbsoluteX(),pos.getAbsoluteY(),-1);
		}else {
			font.drawString("ping: "+"ping is null",pos.getAbsoluteX(),pos.getAbsoluteY(),-1);
		}
		
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID())!=null) {
			font.drawString("ping: "+mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime(),pos.getAbsoluteX(),pos.getAbsoluteY(),-1);
		}else {
			font.drawString("ping: "+"ping is null",pos.getAbsoluteX(),pos.getAbsoluteY(),-1);
		}
	}

    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModPing.enabled = isEnabled;
    }

}
