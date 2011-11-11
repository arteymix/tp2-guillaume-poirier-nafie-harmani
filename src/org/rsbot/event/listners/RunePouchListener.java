package org.rsbot.event.listners;

import org.rsbot.script.methods.Mouse;
import org.rsbot.script.methods.Bank;
import org.rsbot.script.methods.Game;
import org.rsbot.script.methods.Interfaces;
import org.rsbot.script.methods.Inventory;
import org.rsbot.script.methods.Magic;
import org.rsbot.script.wrappers.RSPlayer;

/**
 *
 * @author Arteymis
 * @version 0.02
 */
public interface RunePouchListener {

    /**
     * Gives inventory access to RunePouchHandler object.
     * @return inventory access.
     */
    Inventory getInventory();

    /**
     * Gives magic access to RunePouchHandler object.
     * @return magic access.
     */
    Magic getMagic();

    /**
     * Gives mouse access to RunePouchHandler object.
     * @return mouse access.
     */
    Mouse getMouse();

    /**
     * Gives bank access to RunePouchHandler object.
     * @return bank access.
     */
    Bank getBank();

    /**
     * Gives game access to RunePouchHandler object.
     * @return game access.
     */
    Game getGame();

    /**
     * Gives getMyPlayer() access to RunePouchHandler object.
     * @return getMyPlayer access.
     */
    RSPlayer getMyPlayer();

    /**
     * Gives random() access to RunePouchHandler object.
     * @param i see random().
     * @param i2 see random().
     * @return random() access.
     */
    double random(double i, double i2);

    /**
     * Gives random() access to RunePouchHandler object.
     * @param i see random().
     * @param i2 see random().
     * @return random() access.
     */
    int random(int i, int i2);

    /**
     * Gives interfaces access to RunePouchHandler object.
     * @return interfaces access.
     */
    Interfaces getInterfaces();

    /**
     * Gives sleep() access to RunePouchHandler object.
     * @param i see sleep().
     */
    void sleep(int i);

    /**
     * Gives sleep() access to RunePouchHandler object.
     * @param i see sleep().
     * @param i2 see sleep().
     */
    void sleep(int i, int i2);

    /**
     * Gives log() access to RunePouchHandler object.
     * @param i see log().
     */
    void log(Object i);
}
