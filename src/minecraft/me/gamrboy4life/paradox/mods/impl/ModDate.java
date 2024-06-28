package me.gamrboy4life.paradox.mods.impl;

import java.util.Calendar;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ModDate extends ModDraggable {
    private FontRenderer font;

    public ModDate() {
        this.font = Minecraft.getMinecraft().fontRendererObj;
    }

    @Override
    public int getWidth() {
        // 日付の文字列の幅を取得
        return font.getStringWidth("MM/dd");
    }

    @Override
    public int getHeight() {
        // フォントの高さを取得
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        // 現在の月と日を取得
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1; // 月は0-indexedで取得されるため、+1する
        int day = now.get(Calendar.DAY_OF_MONTH);

        // 日付を描画する
        font.drawString(String.format("%02d/%02d", month, day), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        // ダミーデータとして現在の月日を描画する
        font.drawString("MM/dd", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

 
    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModDate.enabled = isEnabled;
    }

    
}