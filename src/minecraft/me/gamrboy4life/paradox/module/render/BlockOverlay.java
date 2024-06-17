package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.renderer.RenderGlobal;

public class BlockOverlay extends Module{

	public BlockOverlay() {
		super("BlockOverlay",0,Category.RENDER);
	}
	
    @Override
    public void onRender() {
    	RenderGlobal.setBlockOverlay(isToggled());
    }
	
}
