package me.gamrboy4life.paradox.module.movement;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class AutoSprint extends Module {

    private final Minecraft mc = Minecraft.getMinecraft();

    public AutoSprint() {
        super("AutoSprint", 0, Category.MOVEMENT);
    }

    public void onEnable() {
        super.onEnable();
    }

    public void onDisable() {
        mc.thePlayer.setSprinting(false);
        super.onDisable();
    }

    public void onUpdate() {
        if (this.isToggled()) {
            if (mc.thePlayer.isCollidedHorizontally) return;
            if (mc.thePlayer.moveForward < 0) return;
            if (mc.thePlayer.isUsingItem()) return;
            if (mc.thePlayer.isSneaking()) return;

            mc.thePlayer.setSprinting(true);
        }
    }
}