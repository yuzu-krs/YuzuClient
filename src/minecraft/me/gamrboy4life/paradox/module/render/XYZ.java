package me.gamrboy4life.paradox.module.render;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.Wrapper;
import net.minecraft.client.gui.Gui;

public class XYZ extends Module{

	public XYZ() {
		super("XYZ",0,Category.RENDER);
	}
	
	@Override
	public void onRender() {
		if(this.isToggled()) {
			
			Gui.drawRect(118,2,250,14,0x80000000);
			Wrapper.fr.drawString(String.format("XYZ: %.2f %.2f %.2f", mc.getRenderViewEntity().posX, (mc.getRenderViewEntity().getEntityBoundingBox().maxY-1.8), mc.getRenderViewEntity().posZ), 120, 4, -1);
		}
	}
	

	

}
