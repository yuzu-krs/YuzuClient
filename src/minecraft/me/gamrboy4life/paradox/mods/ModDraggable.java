package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.gui.hud.IRenderer;
import me.gamrboy4life.paradox.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {
    public final int getLineOffset(ScreenPosition pos, int lineNum) {
        return pos.getAbsoluteY() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum) {
        return (font.FONT_HEIGHT + 3) * lineNum;
    }
    

}