package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModKeystrokes;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Keystrokes extends Module {



    public Keystrokes() {
        super("Keystrokes", 0, Category.STATUS);

    }

    @Override
    public void onUpdate() {
    	ModKeystrokes modKeystrokes=new ModKeystrokes();
    	
    	
        modKeystrokes.setEnabled(isToggled());
    }
}