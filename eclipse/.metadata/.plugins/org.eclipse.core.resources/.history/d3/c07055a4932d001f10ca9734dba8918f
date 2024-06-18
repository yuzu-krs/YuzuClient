package base.cosmetic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticBoolean
{
    public static boolean cape1;
    public static boolean haloblue;
    public static boolean halogreen;
    public static boolean snowcape;
    public static boolean skycape;
    public static boolean rickcape;
    public static boolean dragonWings;
    public static boolean retrowaveCape;
    public static boolean lightningCape;
    
    static {
        CosmeticBoolean.cape1 = true;
        CosmeticBoolean.haloblue = true;
        CosmeticBoolean.halogreen = false;
        CosmeticBoolean.snowcape = false;
        CosmeticBoolean.skycape = false;
        CosmeticBoolean.rickcape = false;
        CosmeticBoolean.dragonWings = true;
        CosmeticBoolean.retrowaveCape = false;
        CosmeticBoolean.lightningCape = false;
    }
    
    
    public static boolean Cape1() {
        return CosmeticBoolean.cape1;
    }
    
    public static boolean LightningCape() {
        return CosmeticBoolean.lightningCape;
    }
    
    public static boolean isHaloblue() {
        return CosmeticBoolean.haloblue;
    }
    
    
    public static boolean DragonWings() {
        return CosmeticBoolean.dragonWings;
    }
    
    public static boolean shouldRenderTopHat(final AbstractClientPlayer player) {
        return player.getName().contains(Minecraft.getMinecraft().getSession().getUsername());
    }
    
    public static float[] getHaloColor(final AbstractClientPlayer player) {
        return new float[] { 0.0f, 51.0f, 153.0f, 255.0f };
    }
    
    public static boolean RickCape() {
        return CosmeticBoolean.rickcape;
    }
    
    public static boolean SnowCape() {
        return CosmeticBoolean.snowcape;
    }
    
    public static boolean SkyCape() {
        return CosmeticBoolean.skycape;
    }
    public static boolean RetrowaveCape() {
        return CosmeticBoolean.retrowaveCape;
    }
}
