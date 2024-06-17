package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.MobESPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;

public class EspFriendly extends Module{

    public EspFriendly() {
        super("ESP Friendly", 0, Category.RENDER);
    }
    
    public void onRender() {
    	 if (this.isToggled()) {
             for (Object f : mc.theWorld.loadedEntityList) {
                 if (f instanceof EntityAnimal) {
                     MobESPUtils.entityESPBox((Entity) f, 0);
                 }
             }
         }
    }
}
