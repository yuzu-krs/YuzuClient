package me.gamrboy4life.paradox.module.combat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class MineClicker extends Module {

    private Robot robot;
    private long lastClickTime;

    public MineClicker() {
        super("MineClicker", Keyboard.KEY_R, Category.COMBAT);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        lastClickTime = 0;
    }

    @Override
    public void onUpdate() {
        if (!this.isToggled())
            return;

        long currentTime = System.currentTimeMillis();
        boolean isCtrlPressed = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);

        // マウスの戻るボタン
        if (Mouse.isButtonDown(3) && !isCtrlPressed) {
            if (currentTime - lastClickTime >= 50) { // 50ミリ秒ごとにクリックを許可
                // 左クリックをシミュレート
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                lastClickTime = currentTime; // 最後にクリックした時刻を更新
            }
        }

        super.onUpdate();
    }
}