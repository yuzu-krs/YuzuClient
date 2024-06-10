package me.gamrboy4life.paradox.module.combat;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class AutoSword extends Module{

	public AutoSword() {
		super("AutoSword",0,Category.COMBAT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			getBestWeapon(null);
		}
	}
	
	public static void getBestWeapon(Entity e) {
		float damageModifer=0;
		int newItem=-1;
		for(int slot=0;slot<9;slot++) {
			ItemStack stack=Minecraft.getMinecraft().thePlayer.inventory.mainInventory[slot];
			if(stack==null) {
				continue;
			}
			if(stack.getItem() instanceof ItemSword) {
				ItemSword sword=(ItemSword)stack.getItem();
				float damage=sword.getMaxDamage()+(sword.hasEffect(stack)?getEnchantDamageVsEntity(stack,e):0);
				if(damage>=damageModifer) {
					newItem=slot;
					damageModifer=damage;
				}
			}
		}
		if(newItem>-1) {
			Minecraft.getMinecraft().thePlayer.inventory.currentItem=newItem;
		}
	}
	
	
	
	
	
	
	
	public static int getEnchantDamageVsEntity(ItemStack i,Entity e) {
		if(e instanceof EntityZombie || e instanceof EntityPigZombie|| e instanceof EntitySkeleton) {
			return EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, i)
					+EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId,i);
		}else if(e instanceof EntitySpider) {
			return EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, i)
					+EnchantmentHelper.getEnchantmentLevel(Enchantment.baneOfArthropods.effectId, i);
		}else
			return EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, i);
	}

}
