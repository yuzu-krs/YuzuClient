package me.gamrboy4life.paradox.mods.impl.DirectionMod;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class DirectionMod extends ModDraggable {
    protected static ScaledResolution scaledResolution;
    public static String markerColor = "c";
    public static int compassIndex = 0;

	@Override
	public int getWidth() {
		return 102;
	}

	@Override
	public int getHeight() {
		return 15;
	}

	@Override
	public void render(ScreenPosition pos) {
		
        int direction = MathHelper.floor_double((double) (mc.thePlayer.rotationYaw * 256.0F / 360.0F) + 0.5D-16) & 255;
        int yBase = getY(1, 12);
        int xBase = getX(65);
        
        mc.getTextureManager().bindTexture(new ResourceLocation("yuzuclient/Pusula.png"));
        if (direction < 128) {
        	Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction, this.compassIndex * 24, 100, 13);
        } else {
            Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction - 128, this.compassIndex * 24 + 12, 100, 13);
        }

        mc.fontRendererObj.drawString("§" + this.markerColor.toLowerCase() + "|§r", pos.getAbsoluteX() + xBase - 16, pos.getAbsoluteY() + yBase - 7, 16777215);
        
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		
        int direction = MathHelper.floor_double((double) (mc.thePlayer.rotationYaw * 256.0F / 360.0F) + 0.5D-16) & 255;
        int yBase = getY(1, 12);
        int xBase = getX(65);
        
        mc.getTextureManager().bindTexture(new ResourceLocation("yuzuclient/Pusula.png"));
        if (direction < 128) {
        	Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction, this.compassIndex * 24, 100, 13);
        } else {
            Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction - 128, this.compassIndex * 24 + 12, 100, 13);
        }

        mc.fontRendererObj.drawString("§" + this.markerColor.toLowerCase() + "|§r", pos.getAbsoluteX() + xBase - 16, pos.getAbsoluteY() + yBase - 7, 16777215);
        
	}
	
    private static int getX(int width) {
		return width;
    }

    private static int getY(int rowCount, int height) {
        return height;
    }
    
    public static enum Direction {
        S,
        SW,
        W,
        NW,
        N,
        NE,
        E,
        SE;
     }

    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        DirectionMod.enabled = isEnabled;
    }
}
