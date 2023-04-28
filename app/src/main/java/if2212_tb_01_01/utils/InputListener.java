package if2212_tb_01_01.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import if2212_tb_01_01.GamePanel;

public class InputListener extends KeyAdapter {
    private boolean upPressed, downPressed, leftPressed, rightPressed, EPressed, enterPressed, escapePressed;
    private GamePanel gp;

    public InputListener(GamePanel gp) {
        this.gp = gp;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_E:
                EPressed = true;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = true;
                break;
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isEPressed() {
        return EPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public boolean isEscapePressed() {
        return escapePressed;
    }
}
