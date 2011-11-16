package util;

import graphique.Canon;
import graphique.InterfaceGraphique;
import java.util.ArrayList;
import main.Main;

/**
 *
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class KeyBoardListener extends Thread {

    private ArrayList<Integer> enabledKeys = new ArrayList<Integer>();
    private InterfaceGraphique interfaceGraphique;
    private Canon canon1, canon2;

    /**
     * 
     * @param canon1
     * @param canon2 
     */
    public KeyBoardListener(Canon canon1, Canon canon2) {
        super("Thread pour le multitouch du clavier");
       this.canon1 = canon1;
       this.canon2 = canon2;
    }

    /**
     * 
     * @param i 
     */
    public void add(Integer i) {
        enabledKeys.add(i);
    }

    /**
     * 
     * @param i 
     */
    public void remove(Integer i) {
        
        enabledKeys.remove(i);
        
        
    }

    /**
     * 
     * @param i
     * @return 
     */
    public boolean contains(Integer i) {
        return enabledKeys.contains(i);
    }

    @Override
    public void run() {
        while (Main.isRunning) {
            long currentTime = System.currentTimeMillis();
            for (int i = 0; i < enabledKeys.size(); i++) {
                if (!Main.isPaused) {
                    
                    try {
                    canon1.gererEvenementDuClavier(enabledKeys.get(i));
                    canon2.gererEvenementDuClavier(enabledKeys.get(i));
                    } catch(IndexOutOfBoundsException iaobe) {
        iaobe.printStackTrace();
        }
                }
            }
            try {
                long tempsDuRendu = System.currentTimeMillis() - currentTime;
                if (tempsDuRendu > 2*(int)Main.latency) {
                    Thread.sleep(0);
                } else {
                    Thread.sleep(2*(int)Main.latency - tempsDuRendu);
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            while (Main.isPaused) {
                try {
                    Thread.sleep((int)Main.latency);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
;
}