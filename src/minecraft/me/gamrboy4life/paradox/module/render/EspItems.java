package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.ItemESPUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;

public class EspItems extends Module {

    public EspItems() {
        super("ESP Items", 0, Category.RENDER);
    }

    public void onRender() {
        if (this.isToggled()) {
            for (Object o : mc.theWorld.loadedEntityList) {
                if (o instanceof EntityItem) {
                    EntityItem entityItem = (EntityItem) o;
                    if (entityItem.getEntityItem().getItem() == Items.iron_ingot) {
                        ItemESPUtils.itemESPBox(entityItem, 0.9, 0.9, 0.9); // 白っぽい色
                    } else if (entityItem.getEntityItem().getItem() == Items.diamond) {
                        ItemESPUtils.itemESPBox(entityItem, 0.0, 1.0, 1.0); // シアン
                    } else if (entityItem.getEntityItem().getItem() == Items.gold_ingot) {
                        ItemESPUtils.itemESPBox(entityItem, 1.0, 0.84, 0.0); // 金色
                    } else if (entityItem.getEntityItem().getItem() == Items.emerald) {
                        ItemESPUtils.itemESPBox(entityItem,  0.0, 0.5, 0.0); // 緑色
                    }
                }
            }
        }
    }
}