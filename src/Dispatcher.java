import java.awt.*;
import java.awt.event.KeyEvent;

public class Dispatcher implements KeyEventDispatcher {
    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
            System.out.println("tester");
        }
        else {
            if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
                System.out.println("2test2");
            }
            else {
                if (keyEvent.getID() == KeyEvent.KEY_TYPED) {
                    System.out.println("3test3");
                }
            }
        }
        return false;
    }
}