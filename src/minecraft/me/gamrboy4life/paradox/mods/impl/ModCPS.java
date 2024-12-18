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
    private List<Long> leftClicks = new ArrayList<>();
    private List<Long> rightClicks = new ArrayList<>();
    private boolean leftPressed;
    private boolean rightPressed;
    private long lastLeftPressed;
    private long lastRightPressed;
    private FontRenderer font;

    public ModCPS() {
        this.font = Minecraft.getMinecraft().fontRendererObj;
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("CPS: 0 | 0");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        final boolean leftPressed = Mouse.isButtonDown(0);
        final boolean rightPressed = Mouse.isButtonDown(1);

        if (leftPressed != this.leftPressed) {
            this.lastLeftPressed = System.currentTimeMillis();
            this.leftPressed = leftPressed;
            if (leftPressed) {
                this.leftClicks.add(this.lastLeftPressed);
            }
        }

        if (rightPressed != this.rightPressed) {
            this.lastRightPressed = System.currentTimeMillis();
            this.rightPressed = rightPressed;
            if (rightPressed) {
                this.rightClicks.add(this.lastRightPressed);
            }
        }

        font.drawString("CPS: " + getCPS(leftClicks) + " | " + getCPS(rightClicks), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();
        clicks.removeIf(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) {
                return aLong + 1000 < time;
            }
        });
        return clicks.size();
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawString("CPS: 0 | 0", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    private static boolean enabled = true;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean isEnabled) {
        ModCPS.enabled = isEnabled;
    }
}