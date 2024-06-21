package me.gamrboy4life.paradox.module.player;

import java.util.Arrays;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class InventoryHelper extends Module {
    private int[] bestArmorDamageReducement;
    private int[] bestArmorSlots;
    private float bestSwordDamage;
    private int bestSwordSlot;

    public InventoryHelper() {
        super("InvHelper", 0, Category.PLAYER);
    }

    @Override
    public void onUpdate() {
        if (this.isToggled()) {
            searchForItems();

            for (int i = 0; i < 4; i++) {
                if (bestArmorSlots[i] != -1) {
                    int bestSlot = bestArmorSlots[i];
                    ItemStack oldArmor = mc.thePlayer.inventory.armorItemInSlot(i);

                    if (oldArmor != null && oldArmor.getItem() != null) {
                        mc.playerController.windowClick(
                            mc.thePlayer.inventoryContainer.windowId,
                            8 - i,
                            0,
                            1,
                            mc.thePlayer
                        );
                    }

                    mc.playerController.windowClick(
                        mc.thePlayer.inventoryContainer.windowId,
                        bestSlot < 9 ? bestSlot + 36 : bestSlot,
                        0,
                        1,
                        mc.thePlayer
                    );
                }
            }

            if (bestSwordSlot != -1 && bestSwordDamage != -1) {
                mc.playerController.windowClick(
                    mc.thePlayer.inventoryContainer.windowId,
                    bestSwordSlot < 9 ? bestSwordSlot + 36 : bestSwordSlot,
                    0,
                    2,
                    mc.thePlayer
                );
            }
        }
    }

    private void searchForItems() {
        bestArmorDamageReducement = new int[4];
        bestArmorSlots = new int[4];
        bestSwordDamage = -1;
        bestSwordSlot = -1;
        Arrays.fill(bestArmorDamageReducement, -1);
        Arrays.fill(bestArmorSlots, -1);

        for (int i = 0; i < 4; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.armorItemInSlot(i);
            if (itemStack != null && itemStack.getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) itemStack.getItem();
                bestArmorDamageReducement[3 - armor.armorType] = armor.damageReduceAmount;
            }
        }

        for (int i = 0; i < 9 * 4; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
            if (itemStack == null || itemStack.getItem() == null) continue;

            if (itemStack.getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) itemStack.getItem();
                int armorType = 3 - armor.armorType;
                if (bestArmorDamageReducement[armorType] < armor.damageReduceAmount) {
                    bestArmorDamageReducement[armorType] = armor.damageReduceAmount;
                    bestArmorSlots[armorType] = i;
                }
            }

            if (itemStack.getItem() instanceof ItemSword) {
                ItemSword sword = (ItemSword) itemStack.getItem();
                if (bestSwordDamage < sword.getDamageVsEntity()) {
                    bestSwordDamage = sword.getDamageVsEntity();
                    bestSwordSlot = i;
                }
            }

            if (itemStack.getItem() instanceof ItemTool) {
                ItemTool sword = (ItemTool) itemStack.getItem();
                float damage = sword.getToolMaterial().getDamageVsEntity();
                if (bestSwordDamage < damage) {
                    bestSwordDamage = damage;
                    bestSwordSlot = i;
                }
            }
        }
    }
}