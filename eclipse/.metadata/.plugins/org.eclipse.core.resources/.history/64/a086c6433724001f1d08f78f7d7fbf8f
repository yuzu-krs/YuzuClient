package me.gamrboy4life.paradox.module.render;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BedWarsXray extends Module {

    public static boolean enabled;
    private float oldGamma;
    public ArrayList<Block> xrayBlocks = new ArrayList<Block>();

    public BedWarsXray() {
        super("BedWarsXray", 0, Category.RENDER);
    }

    @Override
    public void setup() {
        Paradox.instance.settingsManager.rSetting(new Setting("Bed", this, false));
        Paradox.instance.settingsManager.rSetting(new Setting("Wool", this, false));
        Paradox.instance.settingsManager.rSetting(new Setting("Glass", this, false));
        Paradox.instance.settingsManager.rSetting(new Setting("Clay", this, false));
        Paradox.instance.settingsManager.rSetting(new Setting("Ladder", this, false));
        Paradox.instance.settingsManager.rSetting(new Setting("Oak", this, false));
        Paradox.instance.settingsManager.rSetting(new Setting("Obsidian", this, false));
    }

    @Override
    public void onEnable() {
        BedWarsXray.enabled = true;
        this.oldGamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 10.0f;
        mc.gameSettings.ambientOcclusion = 0;
        mc.renderGlobal.loadRenderers();
        updateXrayBlocks();
    }

    @Override
    public void onDisable() {
        BedWarsXray.enabled = false;
        mc.gameSettings.gammaSetting = this.oldGamma;
        mc.gameSettings.ambientOcclusion = 1;
        mc.renderGlobal.loadRenderers();
    }

    public boolean shouldXrayBlock(Block block) {
        return this.xrayBlocks.contains(block);
    }

    private void updateXrayBlocks() {
        xrayBlocks.clear();
        if (Paradox.instance.settingsManager.getSettingByName("Bed").getValBoolean()) {
            xrayBlocks.add(Blocks.bed);
        }
        if (Paradox.instance.settingsManager.getSettingByName("Wool").getValBoolean()) {
            xrayBlocks.add(Blocks.wool);
        }
        if (Paradox.instance.settingsManager.getSettingByName("Glass").getValBoolean()) {
            xrayBlocks.add(Blocks.glass);
        }
        if (Paradox.instance.settingsManager.getSettingByName("Clay").getValBoolean()) {
            xrayBlocks.add(Blocks.stained_glass_pane);
        }
        if (Paradox.instance.settingsManager.getSettingByName("Ladder").getValBoolean()) {
            xrayBlocks.add(Blocks.ladder);
        }
        if (Paradox.instance.settingsManager.getSettingByName("Oak").getValBoolean()) {
            xrayBlocks.add(Blocks.planks);
        }
        if (Paradox.instance.settingsManager.getSettingByName("Obsidian").getValBoolean()) {
            xrayBlocks.add(Blocks.obsidian);
        }
    }

    @Override
    public void onRender() {
        if (this.isToggled()) {
            updateXrayBlocks();
        }
    }
}
