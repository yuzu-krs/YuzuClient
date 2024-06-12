package me.gamrboy4life.paradox.gui.hud;

public interface IRenderer extends IRenderConfig{
	
	int getWidth();
	int getHeight();
	
	void render(ScreenPosition pos);
	
	// デフォルトメソッドを削除し、抽象メソッドに変更
	 void renderDummy(ScreenPosition pos);
	
	boolean isEnabled();
}