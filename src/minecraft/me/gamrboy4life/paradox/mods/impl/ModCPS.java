package me.gamrboy4life.paradox.mods.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.lwjgl.input.Mouse;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ModCPS extends ModDraggable {
    private List<Long> clicks = new ArrayList<Long>(); 
    private boolean wasPressed;
    private long lastPressed;
    private FontRenderer font;

    public ModCPS() {
        this.font = Minecraft.getMinecraft().fontRendererObj;
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("CPS: 00");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        final boolean pressed = Mouse.isButtonDown(0);
        if (pressed != this.wasPressed) {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = pressed;
            if (pressed) {
                this.clicks.add(this.lastPressed);
            }
        }
        font.drawString("CPS: " + getCPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }
    
    private int getCPS() {
        final long time = System.currentTimeMillis();
        this.clicks.removeIf(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) {
                return aLong + 1000 < time;
            }
        });
        return this.clicks.size();
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawString("CPS: 0", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }


    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModCPS.enabled = isEnabled;
    }
}