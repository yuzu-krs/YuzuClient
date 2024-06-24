package me.gamrboy4life.paradox.module.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class InventoryHelper extends Module {
    private int[] bestArmorDamageReduction; // Renamed for clarity
    private int[] bestArmorDamageReducement;
    private int[] bestArmorSlots;
    private float bestSwordDamage;
    private int bestSwordSlot;
    
    private List<Integer> trash = new ArrayList<Integer>();

    public InventoryHelper() {
        super("InvHelper", 0, Category.PLAYER);
        bestArmorDamageReduction = new int[4]; // Initialize arrays in constructor
        bestArmorSlots = new int[4];
        Arrays.fill(bestArmorDamageReduction, -1);
        Arrays.fill(bestArmorSlots, -1);
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
            searchForTrash();
        }
        

        for (Integer slot : trash) {
            mc.playerController.windowClick(
                mc.thePlayer.inventoryContainer.windowId,
                slot < 9 ? slot + 36 : slot,
                0,
                4, // Changed mode to 4 (drop item)
                mc.thePlayer
            );
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

    private void searchForTrash() {
        trash.clear();

        List<List<Integer>> allItems = new ArrayList<>(4); // List of lists for armor types

        List<Integer> allSwords = new ArrayList<>();

        int[] bestArmorDamageReduction = new int[4];
        final int[] bestArmorSlots = new int[4];
        float bestSwordDamage = 0;
        int bestSwordSlot = -1; // Initialize bestSwordSlot

        for (int i = 0; i < 4; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.armorItemInSlot(i);

            allItems.add(new ArrayList<Integer>()); // Initialize armor type lists

            if (itemStack != null && itemStack.getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) itemStack.getItem();
                bestArmorDamageReduction[3 - armor.armorType] = armor.damageReduceAmount;
                bestArmorSlots[i] = 8 + i;
            }
        }

        for (int i = 0; i < 9 * 4; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
            if (itemStack == null || itemStack.getItem() == null) continue;

            if (itemStack.getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) itemStack.getItem();
                int armorType = 3 - armor.armorType;

                allItems.get(armorType).add(i);

                if (bestArmorDamageReduction[armorType] < armor.damageReduceAmount) {
                    bestArmorDamageReduction[armorType] = armor.damageReduceAmount;
                    bestArmorSlots[armorType] = i;
                }
            }

            if (itemStack.getItem() instanceof ItemSword) {
                ItemSword sword = (ItemSword) itemStack.getItem();

                allSwords.add(i);

                if (bestSwordSlot == -1 || bestSwordDamage < sword.getDamageVsEntity()) {
                    bestSwordDamage = sword.getDamageVsEntity();
                    bestSwordSlot = i;
                }
            }

            if (itemStack.getItem() instanceof ItemTool) {
                ItemTool tool = (ItemTool) itemStack.getItem();
                float damage = tool.getToolMaterial().getDamageVsEntity();
                if (bestSwordSlot == -1 || bestSwordDamage < damage) {
                    bestSwordDamage = damage;
                    bestSwordSlot = i;
                }
            }
        }

        for (int i = 0; i < allItems.size(); i++) {
            final int finalI = i;
            allItems.get(i).stream()
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer slot) {
                        return slot != bestArmorSlots[finalI];
                    }
                })
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer slot) {
                        trash.add(slot);
                    }
                });
        }

        for (Integer swordSlot : allSwords) {
            if (swordSlot != bestSwordSlot) {
                trash.add(swordSlot);
            }
        }
    }
}