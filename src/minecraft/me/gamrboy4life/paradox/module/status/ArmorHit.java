package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;

public class ArmorHit extends Module {

    public ArmorHit() {
        super("ArmorHit", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
        
        LayerArmorBase.setCombineTextures(isToggled()); // 現在の状態を反転
        
    }
}