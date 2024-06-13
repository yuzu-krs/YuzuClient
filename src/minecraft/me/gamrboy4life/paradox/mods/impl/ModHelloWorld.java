package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;

public class ModHelloWorld extends ModDraggable{
	
	private ScreenPosition pos;

	@Override
	public int getWidth() {
		// TODO 自動生成されたメソッド・スタブ
		return font.getStringWidth("Hello World (Dummy)");
	}

	@Override
	public int getHeight() {
		// TODO 自動生成されたメソッド・スタブ
		return font.FONT_HEIGHT;
		
	}

	@Override
	public void render(ScreenPosition pos) {
		// TODO 自動生成されたメソッド・スタブ
		font.drawString("Hello World", pos.getAbsoluteX()+1, pos.getAbsoluteY()+1, -1);
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		// TODO 自動生成されたメソッド・スタブ
		font.drawString("Hello World (Dummy)", pos.getAbsoluteX()+1, pos.getAbsoluteY()+1, 0xFF00FF00);
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public void save(ScreenPosition pos) {
		// TODO 自動生成されたメソッド・スタブ
		this.pos=pos;
	}

	@Override
	public ScreenPosition load() {
		// TODO 自動生成されたメソッド・スタブ
		return pos;
	}

}