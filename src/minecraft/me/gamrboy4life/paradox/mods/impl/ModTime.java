package me.gamrboy4life.paradox.mods.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ModTime extends ModDraggable {
    private FontRenderer font;

    public ModTime() {
        this.font = Minecraft.getMinecraft().fontRendererObj;
    }

    @Override
    public int getWidth() {
        // 時刻の文字列の幅を取得
        return font.getStringWidth("00:00:00");
    }

    @Override
    public int getHeight() {
        // フォントの高さを取得
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        // 現在の時刻を取得
        LocalTime now = LocalTime.now();

        // 時刻を描画する
        font.drawString(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        // ダミーデータとして時刻を描画する
        font.drawString("HH:mm:ss", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModTime.enabled = isEnabled;
    }

}