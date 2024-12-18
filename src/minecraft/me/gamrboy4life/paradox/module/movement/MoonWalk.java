package me.gamrboy4life.paradox.module.movement;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class MoonWalk extends Module {

    private long lastToggleTime;
    private boolean isLeftPressed;

    public MoonWalk() {
        super("MoonWalk", 0, Category.MOVEMENT);
        this.lastToggleTime = System.currentTimeMillis();
        this.isLeftPressed = true; // 最初は左キーから始める
    }

    @Override
    public void onUpdate() {
        if (this.isToggled()) {
            long currentTime = System.currentTimeMillis();

            // 200ミリ秒ごとにトグル
            if (currentTime - lastToggleTime > 120) { 
                if (isLeftPressed) {
                    mc.gameSettings.keyBindLeft.pressed = true;
                } else {
                    mc.gameSettings.keyBindLeft.pressed = false;
                }
                isLeftPressed = !isLeftPressed;
                lastToggleTime = currentTime;
            }

            // 常に後ろキーを押す
            mc.gameSettings.keyBindBack.pressed = true;
        }
    }

    @Override
    public void onDisable() {
        mc.gameSettings.keyBindLeft.pressed = false;
        mc.gameSettings.keyBindBack.pressed = false;
        super.onDisable();
    }
}