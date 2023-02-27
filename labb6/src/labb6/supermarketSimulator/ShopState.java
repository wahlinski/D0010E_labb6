/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.generalSimulator.State;

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

    public ShopState(int maxPeople, int maxRegisters) {

    }
    public void begin() {

    }

    public boolean isOpen() {
        return storeOpened;
    }

    public void closeShop() {
        storeOpened = false;
    }

    /**
     *
     * @return mängden tillgängliga kassor
     */
    public int openRegisters() {
        return unusedRegisters;
    }

    /**
     * <p><b>viktigt!</b> kolla så det finns tillgängliga kassor</p>
     */
    public void occupyRegister() {
        if (unusedRegisters < 1) {
            throw new RuntimeException("finns inga fria kassor, använd openRegisters() före");
        }

        unusedRegisters -= 1;
    }

    /**
     * <p><b>viktigt!</b> kolla så alla kassor är oanvända redan</p>
     */
    public void freeUpRegister() {
        if (unusedRegisters >= maxRegisters) {
            throw new RuntimeException("kan inte fria en ny kassa eftersom alla är oanvända");
        }

        unusedRegisters += 1;
    }

    public int getMaxRegisters() {
        return maxRegisters;
    }

    public int getMaxPeopleInStore() {
        return maxPeopleInStore;
    }

    public int getPeopleInStore() {
        return peopleInStore;
    }

    public void addPersonMissed() {
        peopleMissed += 1;
    }

    public void addPersonPaid() {
        peoplePaid += 1;
    }

    public int getPeoplePaid() {
        return this.peoplePaid;
    }

    public int getPeopleMissed() {
        return this.peopleMissed;
    }

    public CustomerQueue getCustomerQueue() {
        return customerQueue;
    }

    public int addCustomer() {
        return idGenerator.getNewID();
    }

    public int getArrivalTime() {
        return 0;
    }

    public int getPickTime() {
        return 0;
    }

    public int getPayTime() {
        return 0;
    }

    public void addTimeRegistersUnused(int time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som kassor varit oanvända");
        }

        timeRegistersNotUsed += time;
    }

    public void addTimeInQueue(int time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som folk har stått i kassakön");
        }

        timeInQueue += time;
    }
}
