package me.gamrboy4life.paradox.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticController {

    private static boolean shouldRenderTopHat = false;
    private static boolean shouldRenderTopEyes = false;

    public static boolean shouldRenderTopHat(AbstractClientPlayer player) {
        return shouldRenderTopHat;
    }

    public static void setShouldRenderTopHat(boolean shouldRender) {
        shouldRenderTopHat = shouldRender;
    }

    public static float[] getTopHatColor(AbstractClientPlayer player) {
        // R G B -> Values 0 - 1 only
        return new float[] {1, 1, 0};
    }
    
    public static boolean shouldRenderEyes(AbstractClientPlayer player) {
    	return shouldRenderTopEyes;
    }
    
    public static void setShouldRenderTopEyes(boolean shouldRender) {
        shouldRenderTopEyes = shouldRender;
    }
    
}