package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.renderer.EntityRenderer;

public class OldSneaking extends Module {

    public OldSneaking() {
        super("OldSneaking", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
        EntityRenderer.setOldSneak(isToggled());
        
        
    }
}