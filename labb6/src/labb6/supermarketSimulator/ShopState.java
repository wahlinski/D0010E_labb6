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

    public void stop() {

    }

    public boolean isOpen() {
        return false;
    }

    public void closeShop() {

    }

    public void openRegister() {

    }

    public void occupyRegister() {

    }

    public void freeUpRegister() {

    }

    public void addPersonMissed() {

    }

    public void addPersonPaid() {

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

    public void addCustomer() {

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

    }

    public void addTimeInQueue(int time) {

    }
}
