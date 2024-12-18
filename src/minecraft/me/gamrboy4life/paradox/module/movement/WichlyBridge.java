package me.gamrboy4life.paradox.module.movement;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class WichlyBridge extends Module {

    private long lastToggleTime;
    private boolean isLeftPressed;

    public WichlyBridge() {
        super("Wichly", 0, Category.MOVEMENT);
        this.lastToggleTime = System.currentTimeMillis();
        this.isLeftPressed = true; // 最初は左キーから始める
    }

    @Override
    public void onUpdate() {
        if (this.isToggled()) {
            long currentTime = System.currentTimeMillis();

            // 200ミリ秒ごとにトグル
            if (currentTime - lastToggleTime > 20) { 
                if (isLeftPressed) {
                    mc.gameSettings.keyBindLeft.pressed = true;
                    mc.gameSettings.keyBindBack.pressed = false;
                } else {
                    mc.gameSettings.keyBindLeft.pressed = false;
                    mc.gameSettings.keyBindBack.pressed = true;
                }
                isLeftPressed = !isLeftPressed;
                lastToggleTime = currentTime;
            }
        }
    }

    @Override
    public void onDisable() {
        mc.gameSettings.keyBindLeft.pressed = false;
        mc.gameSettings.keyBindBack.pressed = false;
        super.onDisable();
    }
}