
/*
import java.awt.*;
import java.awt.event.KeyEvent;

public class Dispatcher {
    private static volatile boolean enterPressed = false;
    public static boolean isEnterPressed() { //TODO call this at some point
        synchronized (Dispatcher.class) {
            return enterPressed;
        }
    }
    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEvent -> {
            synchronized (Dispatcher.class) {
                switch (keyEvent.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                            enterPressed = true;
                            System.out.println("Enter Pressed");
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                            enterPressed = false;
                            System.out.println("Enter Released");
                        }
                        break;
                }
            }
            return false;
        });
    }
}

 */
