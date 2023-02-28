/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.State;
import labb6.generalSimulator.StopEvent;
import labb6.supermarketSimulator.events.CustomerPayEvent;

/**
 * This class defines the state of the shop.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
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
    private double lastCustomerPayedTime;
    private boolean storeOpened = false;
    private double timeRegistersNotUsed = 0;
    private double timeInQueue = 0;

    /**
     * Instantiates a new Shop state.
     *
     * @param maxPeople    the max people in the store.
     * @param maxRegisters the max registers in the store.
     * @throws IllegalArgumentException if incorrect types are passed through.
     */
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

        //
        if (event instanceof CustomerPayEvent){
            this.lastCustomerPayedTime = event.getTime();
        }

        super.update(event);
    }

    public void begin() {
        storeOpened = true;
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
    public void addPeopleHaveQueued() {
        this.peopleQueued++;
    }

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
    
    public int getPeopleHaveQueued() {
        return this.peopleQueued;
    }

    /**
     * Gets customer queue.
     *
     * @return the customer queue
     */
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
    public double getCustomerLastPayedTime(){
        return this.lastCustomerPayedTime;
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
