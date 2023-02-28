/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.generalSimulator.State;

/**
 * This class defines the state of the shop.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class ShopState extends State {
    private int peopleInStore;
    private int maxPeopleInStore;
    private int peopleMissed;
    private int peoplePaid;
    private int maxRegisters;
    private int unusedRegisters;
    private boolean storeOpened;
    private CustomerQueue customerQueue;
    private CustomerIDGenerator idGenerator;
    private ArrivalTime arrivalTime;
    private PickTime pickTime;
    private PayTime payTime;
    private int timeRegistersNotUsed;
    private int timeInQueue;

    /**
     * Instantiates a new Shop state.
     *
     * @param maxPeople    the max people in the store.
     * @param maxRegisters the max registers in the store.
     * @throws IllegalArgumentException if incorrect types are passed through.
     */
    public ShopState(int maxPeople, int maxRegisters) {

    }
    public void begin() {

    }

    /**
     * Checks if the store is open.
     *
     * @return the boolean
     */
    public boolean isOpen() {
        return storeOpened;
    }

    /**
     * Checks if the store is closed.
     */
    public void closeShop() {
        storeOpened = false;
    }

    /**
     * Opens registers int.
     *
     * @return mängden tillgängliga kassor
     * @throws Error store is not open??
     */
    public int openRegisters() {
        return unusedRegisters;
    }

    /**
     * Makes sure that Registers are occupied.
     *
     * <p><b>viktigt!</b> kolla så det finns tillgängliga kassor</p>
     *
     * @throws RuntimeException if the unusedRegisters < 1
     */
    public void occupyRegister() {
        if (unusedRegisters < 1) {
            throw new RuntimeException("finns inga fria kassor, använd openRegisters() före");
        }

        unusedRegisters -= 1;
    }

    /**
     * Opens up new Register
     * <p><b>viktigt!</b> kolla så alla kassor är oanvända redan</p>
     * @throws RuntimeException if unusedRegisters >= maxRegisters
     */
    public void freeUpRegister() {
        if (unusedRegisters >= maxRegisters) {
            throw new RuntimeException("kan inte fria en ny kassa eftersom alla är oanvända");
        }

        unusedRegisters += 1;
    }

    /**
     * Gets max amount of registers.
     *
     * @return the max registers
     */
    public int getMaxRegisters() {
        return maxRegisters;
    }

    /**
     * Gets maximum people in store.
     *
     * @return the max people in store of the type int
     */
    public int getMaxPeopleInStore() {
        return maxPeopleInStore;
    }

    /**
     * Gets people in store.
     *
     * @return the people in store of the type int
     */
    public int getPeopleInStore() {
        return peopleInStore;
    }

    /**
     * Adds person missed.
     */
    public void addPersonMissed() {
        peopleMissed += 1;
    }

    /**
     * Add person paid.
     */
    public void addPersonPaid() {
        peoplePaid += 1;
    }

    /**
     * Gets people paid.
     *
     * @return the people paid
     */
    public int getPeoplePaid() {
        return this.peoplePaid;
    }

    /**
     * Gets people missed.
     *
     * @return the people missed
     */
    public int getPeopleMissed() {
        return this.peopleMissed;
    }

    /**
     * Gets customer queue.
     *
     * @return the customer queue
     */
    public CustomerQueue getCustomerQueue() {
        return customerQueue;
    }

    /**
     * Add customer int.
     *
     * @return the int
     */
    public int addCustomer() {
        return idGenerator.getNewID();
    }

    /**
     * Gets arrival time.
     *
     * @return the arrival time
     */
    public int getArrivalTime() {
        return 0;
    }

    /**
     * Gets pick time.
     *
     * @return the pick time
     */
    public int getPickTime() {
        return 0;
    }

    /**
     * Gets pay time.
     *
     * @return the pay time
     */
    public int getPayTime() {
        return 0;
    }

    /**
     * Add time registers unused.
     *
     * @param time the time
     */
    public void addTimeRegistersUnused(int time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som kassor varit oanvända");
        }

        timeRegistersNotUsed += time;
    }

    /**
     * Add time in queue.
     *
     * @param time the time
     */
    public void addTimeInQueue(int time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som folk har stått i kassakön");
        }

        timeInQueue += time;
    }
}
