package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;

public class ModFPSEffects extends ModDraggable{


	@Override
	public int getWidth() {
		return 50;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("FPS: "+mc.getDebugFPS(),pos.getAbsoluteX(),pos.getAbsoluteY(),-1);
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("FPS: "+mc.getDebugFPS(),pos.getAbsoluteX(),pos.getAbsoluteY(),-1);

	}


    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModFPSEffects.enabled = isEnabled;
    }


}
