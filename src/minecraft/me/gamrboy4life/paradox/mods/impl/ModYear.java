package me.gamrboy4life.paradox.mods.impl;

import java.time.Year;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ModYear extends ModDraggable {
    private FontRenderer font;

    public ModYear() {
        this.font = Minecraft.getMinecraft().fontRendererObj;
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("yyyy"); // 年の文字列の幅を取得
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT; // フォントの高さを取得
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawString(Year.now().getValue()+"", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        // 現在の年を描画する
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawString("yyyy", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        // ダミーデータとして現在の年を描画する
    }

  
    
    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModYear.enabled = isEnabled;
    }

}