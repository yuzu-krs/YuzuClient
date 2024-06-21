package me.gamrboy4life.paradox.mods.impl;



import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;

public class ModPlayer extends ModDraggable{
	
	public String modeCrouch = "up";
	/*
	 * Modes are 'down', 'up' and none.
	 *If down, you will go a few pixels down when crouching (and back when you stop) to keep your Y position.
	 *If up, same as down but you go up.
	 *If none, you stay the same.
	 */

	public String modeAppear = "crouchSprint";
	/*Modes are 'always', 'crouch', 'sprint' and 'crouchSprint'.
	*These are when it appears, simple enough.
	*/
	public int stabilisation;
	public String visible;
	
	@Override
	public int getWidth() {
		return 9;
	}

	@Override
	public int getHeight() {
		return 20;
	}

	@Override
	public void render(ScreenPosition pos) {
			
		if (modeCrouch == "none") {
			stabilisation = 0;
		}
		
		if (modeCrouch == "down") {
			stabilisation = 0;
		}
		
		if (modeCrouch == "up") {
			stabilisation = -2;
		}
		
		if (modeAppear == "always") {
			visible = "true";
		}
		
		if (modeAppear == "crouch") {
			if (mc.thePlayer.isSneaking()) {
				visible = "true";
			} else {
				visible = "false";
			}
		}
		
		if (modeAppear == "sprint") {
			if (mc.thePlayer.isSprinting()) {
				visible = "true";
			} else {
				visible = "false";
			}
		}
		
		if (modeAppear == "crouchSprint") {
			if (mc.thePlayer.isSprinting() || mc.thePlayer.isSneaking()) {
				visible = "true";
			} else {
				visible = "false";
			}
		}
			
		if(visible == "true") {
			if (!mc.thePlayer.isSneaking()) {
				GuiInventory.drawEntityOnScreen(pos.getAbsoluteX() + 5, (pos.getAbsoluteY() + 20), 10, 2f, 1.8f, Minecraft.getMinecraft().thePlayer);
			} else {
				GuiInventory.drawEntityOnScreen(pos.getAbsoluteX() + 5, (pos.getAbsoluteY() + 20) + stabilisation, 10, 2f, 1.8f, Minecraft.getMinecraft().thePlayer);
			}
		}
			
		//@Override
		//public void save(ScreenPosition pos) {
		//	this.pos = pos;
		//}

		//@Override
		//public ScreenPosition load() {
		//	return pos;
		//}

	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (modeCrouch == "none") {
			stabilisation = 0;
		}
		
		if (modeCrouch == "down") {
			stabilisation = 0;
		}
		
		if (modeCrouch == "up") {
			stabilisation = -2;
		}
		
		if (modeAppear == "always") {
			visible = "true";
		}
		
		if (modeAppear == "crouch") {
			if (mc.thePlayer.isSneaking()) {
				visible = "true";
			} else {
				visible = "false";
			}
		}
		
		if (modeAppear == "sprint") {
			if (mc.thePlayer.isSprinting()) {
				visible = "true";
			} else {
				visible = "false";
			}
		}
		
		if (modeAppear == "crouchSprint") {
			if (mc.thePlayer.isSprinting() || mc.thePlayer.isSneaking()) {
				visible = "true";
			} else {
				visible = "false";
			}
		}
			
		if(visible == "true") {
			if (!mc.thePlayer.isSneaking()) {
				GuiInventory.drawEntityOnScreen(pos.getAbsoluteX() + 5, (pos.getAbsoluteY() + 20), 10, 2f, 1.8f, Minecraft.getMinecraft().thePlayer);
			} else {
				GuiInventory.drawEntityOnScreen(pos.getAbsoluteX() + 5, (pos.getAbsoluteY() + 20) + stabilisation, 10, 2f, 1.8f, Minecraft.getMinecraft().thePlayer);
			}
		}
		
	}

    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        ModPlayer.enabled = isEnabled;
    }


}