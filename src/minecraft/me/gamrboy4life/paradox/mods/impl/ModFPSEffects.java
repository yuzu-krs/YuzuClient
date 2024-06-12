package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;

public class ModFPSEffects extends ModDraggable{
	
	private ScreenPosition pos;

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

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos=pos;		
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}

}
