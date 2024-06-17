package me.gamrboy4life.paradox.module.combat;

import java.util.ArrayList;
import java.util.Iterator;

import de.Hero.settings.Setting;
import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Reach extends Module {

    public Reach() {
        super("Reach", 0, Category.COMBAT);
    }

    // 攻撃範囲の設定を追加
    private double attackRange = Paradox.instance.settingsManager.getSettingByName("Attack Range").getValDouble();

    @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Attack Range");
        Paradox.instance.settingsManager.rSetting(new Setting("Attack Range", this, attackRange, 1, 10, false));
    }

    @Override
    public void onUpdate() {
        if (!this.isToggled())
            return;

        // 設定から攻撃範囲を更新
        double range = Paradox.instance.settingsManager.getSettingByName("Attack Range").getValDouble();

        for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
            Object theObject = entities.next();
            if (theObject instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) theObject;

                if (entity instanceof EntityPlayerSP) continue;

                // 調整可能な攻撃範囲を使用
                if (mc.thePlayer.getDistanceToEntity(entity) <= range) {
                    if (entity.isEntityAlive()) {
                        mc.playerController.attackEntity(mc.thePlayer, entity);
                        mc.thePlayer.swingItem();
                        continue;
                    }
                }
            }
        }
        super.onUpdate();
    }
}