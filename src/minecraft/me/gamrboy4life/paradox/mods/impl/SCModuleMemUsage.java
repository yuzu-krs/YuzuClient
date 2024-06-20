package me.gamrboy4life.paradox.mods.impl;


import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class SCModuleMemUsage extends ModDraggable {

    private Minecraft mc = Minecraft.getMinecraft();
    private FontRenderer font = mc.fontRendererObj;

    @Override
    public int getWidth() {
        Runtime runtime = Runtime.getRuntime();
        String str = "Mem: " + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "% ";
        return font.getStringWidth(str) + 8;
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT + 9;
    }

    @Override
    public void render(ScreenPosition pos) {
        Runtime runtime = Runtime.getRuntime();
        String str = "Mem: " + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "% ";
        
        font.drawString(str, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + 5, -1);
    }
    
    @Override
    public void renderDummy(ScreenPosition pos) {
        Runtime runtime = Runtime.getRuntime();
        String str = "Mem: " + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "% ";
        
        font.drawString(str, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + 5, -1);
    }

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}