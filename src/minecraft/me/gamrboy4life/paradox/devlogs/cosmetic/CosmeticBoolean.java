package me.gamrboy4life.paradox.devlogs.cosmetic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticBoolean {
    public static boolean snowCape;

    static {
        CosmeticBoolean.snowCape = true;
    }

    public static boolean shouldRenderTopHat(final AbstractClientPlayer player) {
        return player.getName().contains(Minecraft.getMinecraft().getSession().getUsername());
    }

    public static float[] getHaloColor(final AbstractClientPlayer player) {
        return new float[] { 0.0f, 51.0f, 153.0f, 255.0f };
    }

    public static boolean SnowCape() {
        return CosmeticBoolean.snowCape;
    }

    // Add this setter method
    public static void setSnowCape(boolean value) {
        CosmeticBoolean.snowCape = value;
    }
}