package me.gamrboy4life.paradox.module.render;

import java.util.List;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.Wrapper;
import net.minecraft.client.gui.Gui;

public class TabGui extends Module{
	
	public int currentTab;
	public boolean expanded;

	public TabGui() {
		super("TabGui",0,Category.RENDER);
		toggled=true;
	}
	
	public void draw() {
		if(this.isToggled()) {
			Gui.drawRect(5, 20, 60, 101, 0x90000000);
			Gui.drawRect(8, 23+currentTab*13, 7+50, 34+currentTab*13, 0xFF006400);
			
			int count=0;
			
			for(Category c:Category.values()) {
				Wrapper.fr.drawString(c.name,10,25+count*13,-1);
				count++;
			}
			
			if(expanded) {
				Category category=Category.values()[currentTab];
				List<Module> modules=Paradox.instance.moduleManager.getModulesbyCategory(category);
				
				if(modules.size()==0) {
					return;
				}
				
				int maxLenModule=0;
				for(Module module:modules) {
					if(Wrapper.fr.getStringWidth(module.name)>maxLenModule)maxLenModule=Wrapper.fr.getStringWidth(module.name);
				}
				
				Gui.drawRect(60, 20, 40+maxLenModule+30, 25+modules.size()*13, 0x90000000);
				Gui.drawRect(60, 23+category.moduleIndex*13, 55+maxLenModule+12, 34+category.moduleIndex*13,0xFF006400);
				
				count=0;
				
				for(Module m:modules) {
					Wrapper.fr.drawStringWithShadow(m.name,63, 25+count*13, -1 );
					count++;
				}
				
				
			}
			
			
		}
	}
	
	public void keyPressed(int k) {
		Category category=Category.values()[currentTab];
		List<Module> modules=Paradox.instance.moduleManager.getModulesbyCategory(category);
		switch(k) {
		
		case Keyboard.KEY_UP:
			if(expanded) {
				if(category.moduleIndex<=0) {
					category.moduleIndex=modules.size()-1;
				}else {
					category.moduleIndex--;
				}
			}else {
				if(currentTab<=0) {
					currentTab=Category.values().length-1;
				}else {
					currentTab--;
				}
				
			}
			break;
			
		case Keyboard.KEY_DOWN:
			if(expanded) {
				if(category.moduleIndex>=modules.size()-1) {
					category.moduleIndex=0;
				}else {
					category.moduleIndex++;
				}
			}else {
				if(currentTab>=Category.values().length-1) {
					currentTab=0;
				}else {
					currentTab++;
				}
			}
			break;
			
		case Keyboard.KEY_RIGHT:
			if(expanded&&modules.size()!=0) {
				Module module=modules.get(category.moduleIndex);
				if(!module.name.equals("TabGui"))
					module.toggle();
			}else {
				if(modules.size()!=0) {
					expanded=true;
					category.moduleIndex=0;
				}
			}
			break;
			
		case Keyboard.KEY_LEFT:
			expanded=false;

			
			
			
			
			
		
		}
		
	}

}