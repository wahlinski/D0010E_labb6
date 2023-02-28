/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.State;
import labb6.generalSimulator.StopEvent;

public class ShopState extends State {
    private final int maxPeopleInStore;
    private final int maxRegisters;
    private final CustomerQueue customerQueue;
    private final ArrivalTime arrivalTime;
    private final PickTime pickTime;
    private final PayTime payTime;
    private final CustomerIDGenerator idGenerator;
    private int peopleInStore = 0;
    private int peopleMissed = 0;
    private int peoplePaid = 0;
    private int peopleQueued = 0;
    private int unusedRegisters;
    private boolean storeOpened = false;
    private double timeRegistersNotUsed = 0;
    private double timeInQueue = 0;

    public ShopState(int maxRegisters, int maxInStore, double lambda, double pMin, double pMax, double kMin, double kMax, int seed) {

        this.maxRegisters = maxRegisters;
        this.unusedRegisters = maxRegisters;
        this.maxPeopleInStore = maxInStore;

        arrivalTime = new ArrivalTime(lambda, seed);
        pickTime = new PickTime(pMin, pMax, seed);
        payTime = new PayTime(kMin, kMax, seed);

        customerQueue = new CustomerQueue(this);
        idGenerator = new CustomerIDGenerator();

    }

    @Override
    public void update(Event event) {
        if(event instanceof StopEvent) { // if (event.getName() == EventName.STOP) {
            super.update(event);
            return;
        }

        double diffTime = event.getTime() - getTime();

        // räkna ut oanvänd kassatid
        int open = openRegisters();
        timeRegistersNotUsed += diffTime * open;

        // räkna ut kötid
        int inQueue = customerQueue.size();
        timeInQueue += diffTime * inQueue;

        super.update(event);
    }

    public void begin() {
        storeOpened = true;
    }

    public boolean isOpen() {
        return storeOpened;
    }

    public void closeShop() {
        storeOpened = false;
    }

    /**
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
     * <p><b>viktigt!</b> kolla så alla kassor inte är öppna redan</p>
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

    public boolean canCustomerGoIn() {
        return getMaxPeopleInStore() > getPeopleInStore();
    }

    public void addPeopleInStore() {
        if (peopleInStore >= maxPeopleInStore) {
            throw new RuntimeException("för många i butiken");
        }
        peopleInStore += 1;
    }

    public void personLeftStore() {
        if (peopleInStore <= 0) {
            throw new RuntimeException("ingen som kan gå ut");
        }

        peopleInStore -= 1;
    }

    public void addPersonMissed() {
        peopleMissed += 1;
    }

    public void addPersonPaid() {
        peoplePaid += 1;
    }

    public void addPeopleHaveQueued() {
        this.peopleQueued++;
    }

    public int getPeoplePaid() {
        return this.peoplePaid;
    }

    public int getPeopleMissed() {
        return this.peopleMissed;
    }

    public int getPeopleHaveQueued() {
        return this.peopleQueued;
    }

    public CustomerQueue getCustomerQueue() {
        return customerQueue;
    }

    public Customer createCustomer() {
        return new Customer(idGenerator.getNewID());
    }

    public double getArrivalTime() {
        return arrivalTime.calculate(getTime());
    }

    public double getPickTime() {
        return pickTime.calculate(getTime());
    }

    public double getPayTime() {
        return payTime.calculate(getTime());
    }

    public void addTimeRegistersUnused(double time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som kassor varit oanvända");
        }

        timeRegistersNotUsed += time;
    }

    public double getTimeRegistersNotUsed() {
        return timeRegistersNotUsed;
    }

    public void addTimeInQueue(double time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som folk har stått i kassakön");
        }

        timeInQueue += time;
    }

    public double getTimeInQueue() {
        return timeInQueue;
    }


    public double lambda() {
        return arrivalTime.getLambda();
    }

    public long seed() {
        return arrivalTime.getSeed();
    }

    public double pMin() {
        return pickTime.getPMin();
    }

    public double pMax() {
        return pickTime.getPMax();
    }

    public double kMin() {
        return payTime.getKMin();
    }

    public double kMax() {
        return pickTime.getPMax();
    }

}
