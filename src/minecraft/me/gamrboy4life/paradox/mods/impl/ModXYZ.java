package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;

public class ModXYZ extends ModDraggable {

    private static boolean enabled = true; // 静的フィールド

    @Override
    public int getWidth() {
        return font.getStringWidth(getXYZString());
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (enabled) { // 静的フィールドを使用
            font.drawString(getXYZString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        }
    }

    private String getXYZString() {
        return String.format(
            "XYZ: %.3f / %.3f / %.3f",
            mc.getRenderViewEntity().posX,
            mc.getRenderViewEntity().getEntityBoundingBox().minY,
            mc.getRenderViewEntity().posZ
        );
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (enabled) { // 静的フィールドを使用
            font.drawString(getXYZString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModXYZ.enabled = isEnabled;
    }
}