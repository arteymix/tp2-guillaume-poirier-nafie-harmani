package org.rsbot.script.util;

import java.awt.Point;
import java.util.ArrayList;
import org.rsbot.script.wrappers.RSInterface;
import org.rsbot.event.listners.RunePouchListener;
import org.rsbot.script.methods.Bank;
import org.rsbot.script.methods.Game;
import org.rsbot.script.methods.Interfaces;
import org.rsbot.script.wrappers.RSItem;
import org.rsbot.script.methods.Mouse;
import org.rsbot.script.methods.Inventory;
import org.rsbot.script.methods.Magic;
import org.rsbot.script.wrappers.RSComponent;

/**
 *
 * @author Ubuntu4Life and Arteymis
 * @version 0.04
 */
public class RunePouchHandler {
    /* RunePouchHandler variables
     */

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    public final int SMALL = 3, MEDIUM = 6, LARGE = 9, GIANT = 12;
    public static final int SMALL_POUCH_ID = 5509;
    public static final int MEDUIM_POUCH_ID = 5510;
    public static final int LARGE_POUCH_ID = 5512;
    public static final int GIANT_POUCH_ID = 5514;
    public static final int COSMIC_RUNE_ID = 564,
            ASTRAL_RUNE_ID = 9075,
            AIR_RUNE_ID = 556,
            PURE_ESSENCE_ID = 7936,
            RUNE_ESSENCE_ID = 1436;
    public static final int[] ESSENCES = {RUNE_ESSENCE_ID, PURE_ESSENCE_ID};
    RunePouchHandler instance = null;
    private int essence = RunePouchHandler.PURE_ESSENCE_ID;
    private Timer bankTimer = new Timer(2500);
    ArrayList<Pouch> pouchesUsed = new ArrayList<Pouch>();
    /* Access variables
     */
    private Inventory inventory;
    private Mouse mouse;
    private RunePouchListener superClass;
    private Bank bank;
    private Game game;
    private Magic magic;
    private Interfaces interfaces;
    ArrayList<Pouch> tempPouchesUsed = new ArrayList<Pouch>();
    /* Items' ids
     */

    /**
     * Constructor
     * @param aSuperClass
     */
    public RunePouchHandler(RunePouchListener aSuperClass) {
        this.inventory = aSuperClass.getInventory();
        this.mouse = aSuperClass.getMouse();
        this.superClass = aSuperClass;
        this.bank = aSuperClass.getBank();
        this.interfaces = aSuperClass.getInterfaces();
        this.magic = aSuperClass.getMagic();
    }

    /**
     * Determine if we will be using Rune or Pure essences.
     * @param use true if Rune essences are used, false for Pure essences.
     */
    public void useRuneEssence(boolean use) {
        essence = use ? RunePouchHandler.RUNE_ESSENCE_ID : RunePouchHandler.PURE_ESSENCE_ID;
    }

    /**
     * Return an instance of the RunePouchHandler object.
     * @return a new instance if it's null or the instance itself.
     */
    public RunePouchHandler getInstance() {
        return instance == null ? instance = new RunePouchHandler(this.superClass) : instance;
    }

    /**
     * Initialize the RunePouchHandler object.
     * @return what reset() returns.
     */
    public boolean initialize() {
        return reset();
    }

    /**
     * Reset the RunePouchHandler object.
     * @return always true;
     */
    public boolean reset() {
        pouchesUsed.clear();
        int id;
        RSItem[] items = inventory.getItems();
        for (RSItem item : items) {
            id = item.getID();
            if (id > 5508 && id < 5516) {
                pouchesUsed.add(new Pouch(id));
            }
        }
        return true;
    }

    private boolean withdrawFromBank(int itemID, int amount) {
        if (!bank.isOpen()) {
            return false;
        }
        bankTimer.reset();
        try {
            bank.getItem(itemID).getComponent().doHover();
            int oldInventoryCount = inventory.getCount(true);
            if (!bank.withdraw(itemID, amount)) {
                this.superClass.sleep(456, 654);
                if (!bank.withdraw(itemID, amount)) {
                    return false;
                }
            }
            while (bankTimer.isRunning() && oldInventoryCount == inventory.getCount(true)) {
                this.superClass.sleep(25);
            }
            return bankTimer.isRunning();
        } catch (Throwable th) {
            this.superClass.log("Throwable in PouchHandler.withdrawFromBank:" + th);
            th.printStackTrace();
            return false;
        }
    }

    private boolean depositIntoBank(int itemID, int amount) {
        if (!bank.isOpen() || !inventory.contains(itemID)) {
            return false;
        }
        bankTimer.reset();
        try {
            bank.getItem(itemID).getComponent().doHover();
            int oldInventoryCount = inventory.getCount(true);
            if (!bank.deposit(itemID, amount)) {
                this.superClass.sleep(456, 654);
                if (inventory.contains(itemID) && !bank.deposit(itemID, amount)) {
                    return false;
                }
            }
            while (bankTimer.isRunning() && oldInventoryCount == inventory.getCount(true)) {
                this.superClass.sleep(25);
            }
            return bankTimer.isRunning();
        } catch (Throwable th) {
            this.superClass.log("Throwable in PouchHandler.withdrawFromBank:" + th);
            th.printStackTrace();
            return false;
        }
    }

    /**
     * Fill pouches.
     * @return a boolean I'm just too tired to figure out.
     */
    public boolean fillPouches() {
        boolean returner = true;
        for (Pouch pouch : pouchesUsed) {
            if (bank.isOpen() && inventory.getCount(RunePouchHandler.ESSENCES) < (pouch.getCapacity() - pouch.contains())) {
                withdrawFromBank(essence, 0);
                this.superClass.sleep(50, 150);
            }
            returner = pouch.fill() && returner;
        }
        this.superClass.sleep(50, 150);
        if (bank.isOpen() && !inventory.isFull()) {
            withdrawFromBank(essence, 0);
            this.superClass.sleep(50, 150);
        }
        bankTimer.reset();
        while (bankTimer.isRunning() && !inventory.isFull()) {
            this.superClass.sleep(25);
        }
        if (!bankTimer.isRunning()) {
            this.superClass.log("Stupid ass inventory says it isn't full:" + inventory.getCount());
        }
        if (bank.isOpen() && !inventory.isFull()) {
            withdrawFromBank(essence, 0);
            this.superClass.sleep(50, 150);
        }
        return returner;
    }

    /**
     * Empty pouches.
     * @return a boolean I'm just too tired to figure out.
     */
    public boolean emptyPouches() {
        //tempPouchesUsed = (ArrayList<Pouch>) pouchesUsed.clone();
        boolean returner = true;
        for (Pouch pouch : pouchesUsed) {
            if (bank.isOpen() && (28 - inventory.getCount()) < pouch.contains()) {
                depositIntoBank(essence, 0);
                mouse.moveRandomly(10);
                this.superClass.sleep(100, 200);
            }
            returner = pouch.empty() && returner;
        }
        if (bank.isOpen() && inventory.contains(essence) && depositIntoBank(essence, 0)) {
            mouse.moveRandomly(10);
            this.superClass.sleep(100, 200);
        }
        return returner;
    }

    /**
     * Really not sure..
     * @return something..
     */
    public boolean hasMoreEssence() {
        boolean negatedReturner = true;
        for (Pouch pouch : pouchesUsed) {
            negatedReturner = pouch.isEmpty() && negatedReturner;
        }
        return !negatedReturner;
    }

    /**
     * Check if pouches need repairing.
     * @return true if pouches need repairing, false otherwise.
     */
    public boolean needsRepairing() {
        boolean returner = false;
        for (Pouch pouch : pouchesUsed) {
            returner = returner || pouch.needsRepair();
        }
        return returner;
    }

    /**
     * Really not sure..
     * @param s something..
     * @return something..
     */
    public RSComponent getComponentByString(String s) {
        return getComponentByString(s, this.interfaces.getAll());
    }

    /**
     * Really not sure..
     * @param s something..
     * @param valid something..
     * @return something..
     */
    public RSComponent getComponentByString(String s, RSInterface[] valid) {
        for (RSInterface iface : valid) {
            if (iface.getIndex() != 137) {
                int len = iface.getChildCount();
                for (int i = 0; i < len; i++) {
                    RSComponent child = iface.getComponent(i);
                    if (child.containsText(s) && child.isValid()
                            && child.getAbsoluteY() > 100) {
                        return child;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Repair pouches in a single call.
     * @return something I don't have the time to figure out..
     */
    public boolean repairPouchesWithSingleCall() {

        this.bank.close();
        this.game.openTab(Game.TAB_MAGIC);
        Timer repairTimer = new Timer(5000);
        if (this.inventory.getCount(true, RunePouchHandler.COSMIC_RUNE_ID) > 0 && this.inventory.getCount(true, RunePouchHandler.ASTRAL_RUNE_ID) > 0 && this.inventory.getCount(true, RunePouchHandler.AIR_RUNE_ID) > 1) {
            if (magic.castSpell(Magic.SPELL_NPC_CONTACT)) {
                this.superClass.sleep(2000, 3000);
                RSComponent scrollbar = interfaces.getComponent(88, 20).getComponent(1);
                Point p = new Point(this.superClass.random(scrollbar.getCenter().x, scrollbar.getCenter().x + 3), this.superClass.random(scrollbar.getCenter().y, scrollbar.getCenter().y + 3));
                this.mouse.move(p);
                this.mouse.drag(this.superClass.random(460, 465), this.superClass.random(210, 220));
                Point p2 = new Point(this.superClass.random(385, 425), this.superClass.random(175, 200));
                this.mouse.click(p2, true);
                this.superClass.sleep(150, 200);
                repairTimer.reset();
                while (repairTimer.isRunning() && this.superClass.getMyPlayer().getAnimation() == 4413) {
                    this.superClass.sleep(150, 200);
                }
                if (!repairTimer.isRunning()) { //Failed
                    return false;
                }
                this.superClass.sleep(5000);
                for (int count = 0; count < 6; count++) {
                    repairTimer.reset();
                    while (repairTimer.isRunning() && !this.interfaces.clickContinue()) {
                        this.superClass.sleep(25, 200);
                    }
                    if (!repairTimer.isRunning()) { //Failed
                        return true;//??
                    }
                    this.superClass.sleep(678, 1234);
                }

                //Repair them
                repairTimer.reset();
                while (repairTimer.isRunning() && getComponentByString("Can you repair my pouches") == null) {
                    this.superClass.sleep(25, 200);
                }
                if (!repairTimer.isRunning()) { //Failed
                    return true;//??
                } else {
                    if (!getComponentByString("Can you repair my pouches").doClick(true)) {
                        return false; //?
                    }
                }

                //click continue
                repairTimer.reset();
                while (repairTimer.isRunning() && !this.interfaces.clickContinue()) {
                    this.superClass.sleep(25, 200);
                }
                if (!repairTimer.isRunning()) { //Failed
                    return false;
                }
            }
        }
        return true;
    }

    class Pouch {
        //internal "settings" variables, could be tuned

        private final int pouchTimerTimeoutInMilliSeconds = 2000;
        private final int maxPixelsFromCenterPouchMenuClicking = 3;
        //internal variables, don't touch unless fixing bug
        private Timer pouchTimer = new Timer(pouchTimerTimeoutInMilliSeconds);
        private Timer statusTimer = new Timer(0);
        private int statusTimerPeriod = 180000;
        private int id;
        private int contains = 0;

        public Pouch(int id) {
            if (id < 5509 || id > 5515) {
                throw new RuntimeException("Item with id:" + id + " is not a supported runecrafting pouch.");
            }
            if (id == 5511 || id == 5513 || id == 5515) {
                id--;//The pouch is broken
            }
            this.id = id;
        }

        public Pouch(int id, int contains) {
            this(id);
            this.setContains(contains);
        }

        public boolean fill() {
            try {
                superClass.sleep(25);
                if (isStatusKnown() && this.isFull()) {
                    return true;
                }
                int old = inventory.getCount(ESSENCES);
                if (inventory.getCount(ESSENCES) > getCapacity() - contains()) {
                    superClass.sleep(25);
                }
                mouse.move(inventory.getItem(getID()).getComponent().getCenter(), maxPixelsFromCenterPouchMenuClicking, maxPixelsFromCenterPouchMenuClicking);
                superClass.sleep(25);
                if (!inventory.getItem(getID()).interact("Fill")) {
                    superClass.sleep(25, 75);
                    mouse.moveRandomly(20);
                    superClass.sleep(25, 75);
                    if (!inventory.getItem(getID()).interact("Fill")) {
                        superClass.log("Failed doing action Fill on pouch:" + getID());
                        return false; //This should basically not happen ever
                    }
                }

                pouchTimer.setEndIn((long) superClass.random(0.9 * pouchTimerTimeoutInMilliSeconds, 1.1 * pouchTimerTimeoutInMilliSeconds));
                //pouchTimer.setEndIn(pouchTimerTimeoutInMilliSeconds);
                while (pouchTimer.isRunning() && inventory.getCount(ESSENCES) >= old) {
                    superClass.sleep(25);
                }
                setContains(getContains() + old - inventory.getCount(ESSENCES));
                if (pouchTimer.isRunning()) {
                    superClass.sleep(75, 125);//??
                    //log("Action done, and filled is pouch:" + getID() + " now contains:" + getContains());
                    return true;
                } else {
                    setContains(getCapacity());
                    superClass.log("Fill done, and failed on pouch:" + getID() + " now contains:" + getContains());
                    return false;
                }
            } catch (Throwable th) {
                setContains(getCapacity());
                superClass.log("Throwable in Pouch.Fill():" + th);
                th.printStackTrace();
                return false;
            }
        }

        public boolean empty() {
            try {
                superClass.sleep(25);
                if (isStatusKnown() && this.isEmpty()) {
                    return true;
                }
                if (this.contains() > (28 - inventory.getCount())) {
                    return false;
                }
                superClass.sleep(25);
                int old = inventory.getCount(ESSENCES);
                mouse.move(inventory.getItem(getID()).getComponent().getCenter(), maxPixelsFromCenterPouchMenuClicking, maxPixelsFromCenterPouchMenuClicking);
                superClass.sleep(25);
                if (!inventory.getItem(getID()).interact("mpty")) {
                    superClass.sleep(25, 75);
                    mouse.moveRandomly(20);
                    superClass.sleep(25, 75);
                    if (!inventory.getItem(getID()).interact("mpty")) {
                        superClass.log("Failed doing action Empty on pouch:" + getID());
                        return false; //This should basically not happen ever
                    }
                }

                pouchTimer.setEndIn((long) superClass.random(0.9 * pouchTimerTimeoutInMilliSeconds, 1.1 * pouchTimerTimeoutInMilliSeconds));
                //pouchTimer.setEndIn(pouchTimerTimeoutInMilliSeconds);
                while (pouchTimer.isRunning() && inventory.getCount(ESSENCES) <= old) {
                    superClass.sleep(25);
                }
                setContains(getContains() + old - inventory.getCount(ESSENCES));
                if (pouchTimer.isRunning()) {
                    superClass.sleep(75, 125);//??
                    //log("Action done, and emptied is pouch:" + getID() + " now contains:" + getContains());
                    return true;
                } else {
                    setContains(0);
                    superClass.log("Empty done, and failed for pouch:" + getID() + " now contains:" + getContains());
                    return false;
                }
            } catch (Throwable th) {
                superClass.log("Throwable in Pouch.Empty():" + th);
                th.printStackTrace();
                setContains(0);
                return false;
            }

        }

        public boolean isEmpty() {
            return isStatusKnown() ? contains() < 1 : false;
        }

        public boolean isFull() {
            return isStatusKnown() ? contains() >= getCapacity() : false;
        }

        public boolean isStatusKnown() {
            return statusTimer.isRunning();
        }

        public void setStatusTimerPeriod(int period) {
            this.statusTimerPeriod = period;
        }

        // Un-intuitivly getID() calls this method
        // So unless that is changed - we can't call
        // getID() here (for logging/etc)
        // also observe:this method might break misserably
        // if new pouches are added to RuneScape
        public boolean needsRepair() {
            if (id == 5509) {
                return false;
            }
            return inventory.contains(id + 1);
        }

        /*
         * Returns the max capacity of this pouch when it isn't broken
         */
        public int getCapacity() {
            switch (getID()) {
                case 5509:
                    return 3;
                case 5510:
                case 5511:
                    return 6;
                case 5512:
                case 5513:
                    return 9;
                case 5514:
                case 5515:
                default:
                    return 12;
            }
        }

        private void setContains(int i) {
            statusTimer.setEndIn(superClass.random((int) (0.9 * statusTimerPeriod), (int) (1.1 * statusTimerPeriod)));
            this.contains = i < 0 ? 0 : (i > getCapacity() ? getCapacity() : i);
        }

        private int getContains() {
            return contains;
        }

        public int contains() {
            return getContains();
        }

        private int getID() {
            return needsRepair() ? id + 1 : id;
        }
    }
}
