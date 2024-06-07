package me.gamrboy4life.paradox.module.combat;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class MineClicker extends Module {

    public MineClicker() {
        super("MineClicker", Keyboard.KEY_R, Category.COMBAT);
    }

    @Override
    public void onUpdate() {

        if (!this.isToggled()) 
            return;

        //攻撃
        // 左クリックの遅延
        mc.rightClickDelayTimer = 1;

        // カーソルに合わせたエンティティが攻撃範囲内にあり、かつ生存している場合にのみ攻撃を実行
        MovingObjectPosition target = mc.objectMouseOver;
        if (target != null && target.entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) target.entityHit;

            // 自分自身を攻撃しないようにする
            if (entity instanceof EntityPlayerSP)
                return;

            // 攻撃範囲を調整する（通常のリーチを使用）
            if (mc.thePlayer.getDistanceToEntity(entity) <= mc.playerController.getBlockReachDistance()) {
                if (entity.isEntityAlive()) {
                    mc.playerController.attackEntity(mc.thePlayer, entity);
                    mc.thePlayer.swingItem();
                }
            }
        }

        super.onUpdate();
        
        //攻撃

        // ブロック置き
        mc.rightClickDelayTimer = 1; //20CPS
        // ブロック置き
    }
}