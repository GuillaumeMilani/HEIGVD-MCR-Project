package game;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Luthier
 */
public class Clock extends Observable implements Runnable {

    private static Clock instance = null;
    private final Thread thread;
    private volatile boolean running = false;
    
    private Clock() {
        thread = new Thread(this);
        running = true;
        thread.start();
    }
    
    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    @Override
    public void run() {
        while (running) {
            try {
                setChanged();
                notifyObservers();
                Thread.sleep(Constantes.CLOCK_CYCLE);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop() {
        running = false;
    }
}
