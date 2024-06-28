package me.gamrboy4life.paradox.module.player;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FastPlace extends Module {

    public FastPlace() {
        super("FastPlace", 0, Category.PLAYER);
    }

    @Override
    public void onUpdate() {
        if (this.isToggled()) {
           ItemStack heldItem = mc.thePlayer.getHeldItem();
            if (heldItem == null||(heldItem.getItem()!=Items.ender_pearl)
            		&&(heldItem.getItem()!=Items.water_bucket&&heldItem.getItem()!=Items.bucket)
            		&&(heldItem.getItem()!=Items.lava_bucket&&heldItem.getItem()!=Items.bucket)) {
                mc.rightClickDelayTimer = 1;
            }
        }
    }
}