package me.gamrboy4life.paradox.module.misc;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ChannelIcon extends Module{

	public ChannelIcon() {
		super("ChannelIcon",0,Category.MISC);
	}
	
	public void onRender() {
		if(this.isToggled()) {
			mc.getTextureManager().bindTexture(new ResourceLocation("yuzuclient/yuzu.jpg"));
			GuiScreen.drawModalRectWithCustomSizedTexture(300, 150, 0, 0, 50, 50, 50, 50);
		}
	}
	

}
