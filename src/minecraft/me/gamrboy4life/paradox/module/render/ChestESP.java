package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.esp.ChestESPUtils;
import net.minecraft.tileentity.TileEntityChest;

public class ChestESP extends Module{

	public ChestESP() {
		super("ChestESP",0,Category.RENDER);
	}
	
	public void onRender() {
		if(this.isToggled()) {
			for(Object o :mc.theWorld.loadedTileEntityList) {
				if(o instanceof TileEntityChest) {
					ChestESPUtils.blockESPBox(((TileEntityChest)o).getPos());
				}
			}
		}
	}
}
