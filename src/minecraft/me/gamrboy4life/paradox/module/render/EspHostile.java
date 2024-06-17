package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.MobESPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;

public class EspHostile extends Module{

    public EspHostile() {
        super("ESP Hostile", 0, Category.RENDER);
    }
    
    public void onRender() {
        if (this.isToggled()) {
            for (Object h : mc.theWorld.loadedEntityList) {
                if (h instanceof EntityMob) {
                    MobESPUtils.entityESPBox((Entity) h, 0);
                }
            }
        }
    }
}
