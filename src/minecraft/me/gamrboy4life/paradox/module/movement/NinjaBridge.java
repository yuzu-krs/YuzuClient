package me.gamrboy4life.paradox.module.movement;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class NinjaBridge extends Module{

    private long lastShiftToggleTime;
    private boolean isShiftPressed;

    public NinjaBridge() {
        super("Ninja", 0, Category.MOVEMENT);
        this.lastShiftToggleTime = System.currentTimeMillis();
        this.isShiftPressed = false;
    }
    
    @Override
    public void onUpdate() {
        if(this.isToggled()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastShiftToggleTime > 30) { // 0.2秒ごとにトグル
                isShiftPressed = !isShiftPressed;
                mc.gameSettings.keyBindSneak.pressed = isShiftPressed;
                lastShiftToggleTime = currentTime;
            }
            mc.gameSettings.keyBindLeft.pressed = true;
            mc.gameSettings.keyBindBack.pressed = true;
        }
    }
    
    @Override
    public void onDisable() {
        mc.gameSettings.keyBindLeft.pressed = false;
        mc.gameSettings.keyBindBack.pressed = false;
        mc.gameSettings.keyBindSneak.pressed = false;
        super.onDisable();
    }
}