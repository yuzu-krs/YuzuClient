package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.MobESPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EspPlayer extends Module{

    public EspPlayer() {
        super("ESP Player", 0, Category.RENDER);
    }
    
    public void onRender() {
        if (this.isToggled()) {
            for (Object e : mc.theWorld.loadedEntityList) {
                if (e instanceof EntityPlayer && e != mc.thePlayer) {
                    MobESPUtils.entityESPBox((Entity) e, 0);
                }
            }
        }
    }
}
