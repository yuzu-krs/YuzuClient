package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.ChestESPUtils;
import me.gamrboy4life.paradox.utils.esp.EnderChestESPUtils;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;

public class EspChest extends Module {

    public EspChest() {
        super("ESP Chest", 0, Category.RENDER);
    }

    public void onRender() {
        if (this.isToggled()) {
            for (Object o : mc.theWorld.loadedTileEntityList) {
                if (o instanceof TileEntityChest) {
                    ChestESPUtils.blockESPBox(((TileEntityChest) o).getPos());
                } else if (o instanceof TileEntityEnderChest) {
                    EnderChestESPUtils.blockESPBox(((TileEntityEnderChest) o).getPos());
                }
            }
        }
    }
}