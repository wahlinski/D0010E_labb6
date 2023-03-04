package labb6.supermarket;

import labb6.general.Event;
import labb6.general.State;
import labb6.general.StopEvent;
import labb6.supermarket.events.CustomerPayEvent;

/**
 * This class defines the state of the shop.
 *
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
     * @param maxRegisters the max registers in the store.
     * @param maxInStore   the max customers simultaneously in store.
     * @param lambda       the lambda parameter indicates the average number of customers arriving per unit of time.
     * @param pMin         the p min specifies minimum PickTime.
     * @param pMax         the p max specifies maximum PickTime.
     * @param kMin         the k min specifies minimum PayTime.
     * @param kMax         the k max specifies maximum PayTime.
     * @param seed         the seed is a random integer used to randomize the simulation.
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
        if (event instanceof StopEvent) {
            super.update(event);
            return;
        }

        double diffTime = event.getTime() - getTime();

        // räkna ut oanvänd kassatid
        int open = openRegisters();
        if (isOpen() || peopleInStore > 0) {
            timeRegistersNotUsed += diffTime * open;
        }

        // räkna ut kötid
        int inQueue = customerQueue.size();
        timeInQueue += diffTime * inQueue;

        //
        if (event instanceof CustomerPayEvent) {
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
     * Open registers int.
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
     * @throws RuntimeException if the unusedRegisters {@literal <} 1
     */
    public void occupyRegister() {
        if (unusedRegisters < 1) {
            throw new RuntimeException("finns inga fria kassor, använd openRegisters() före");
        }

        unusedRegisters -= 1;
    }

    /**
     * Opens up new Register
     *
     * @throws RuntimeException if unusedRegisters {@literal >=} maxRegisters
     */
    public void freeUpRegister() {
        if (unusedRegisters >= maxRegisters) {
            throw new RuntimeException("kan inte fria en ny kassa eftersom alla är oanvända");
        }

        unusedRegisters += 1;
    }

    /**
     * Returns maximum amount of registers.
     *
     * @return the max registers
     */
    public int getMaxRegisters() {
        return maxRegisters;
    }

    /**
     * Returns maximum people in store.
     *
     * @return the max people in store of the type int
     */
    public int getMaxPeopleInStore() {
        return maxPeopleInStore;
    }

    /**
     * Returns number of people in the store.
     *
     * @return the people in store of the type int.
     */
    public int getPeopleInStore() {
        return peopleInStore;
    }

    /**
     * Checks if more people are allowed to enter the store.
     *
     * @return the boolean
     */
    public boolean canCustomerGoIn() {
        return getMaxPeopleInStore() > getPeopleInStore();
    }

    /**
     * Allows more people to enter the store.
     *
     * @throws RuntimeException if peopleInStore >= maxPeopleInStore.
     */
    public void addPeopleInStore() {
        if (peopleInStore >= maxPeopleInStore) {
            throw new RuntimeException("för många i butiken");
        }
        peopleInStore += 1;
    }

    /**
     * Decreses the number of people in the store.
     *
     * @throws RuntimeException if peopleInStore {@literal <=} 0
     */
    public void personLeftStore() {
        if (peopleInStore <= 0) {
            throw new RuntimeException("ingen som kan gå ut");
        }

        peopleInStore -= 1;
    }

    /**
     * Counts the number of people missed.
     */
    public void addPersonMissed() {
        peopleMissed += 1;
    }

    /**
     * Counts number of people paid.
     */
    public void addPersonPaid() {
        peoplePaid += 1;
    }

    /**
     * Counts the number of people queued.
     *
     * @return the people paid
     */
    public void addPeopleHaveQueued() {
        this.peopleQueued++;
    }

    /**
     * Returns the number of people finished paying.
     *
     * @return the people paid
     */
    public int getPeoplePaid() {
        return this.peoplePaid;
    }

    /**
     * Returns the number of people missed.
     *
     * @return the people missed
     */
    public int getPeopleMissed() {
        return this.peopleMissed;
    }

    /**
     * Returns the number of people who have queued.
     *
     * @return the people have queued
     */
    public int getPeopleHaveQueued() {
        return this.peopleQueued;
    }

    /**
     * Returns the customer queue of type ArrayList
     *
     * @return the customer queue
     */
    public CustomerQueue getCustomerQueue() {
        return customerQueue;
    }

    /**
     * Creates a new Customer Object.
     *
     * @return the customer with a unique CustomerID
     */
    public Customer createCustomer() {
        return new Customer(idGenerator.getNewID());
    }

    /**
     * Returns the calculated ArrivalTime.
     *
     * @return the arrival time
     */
    public double getArrivalTime() {
        return arrivalTime.calculate(getTime());
    }

    /**
     * Returns the calculated PickTime.
     *
     * @return the pick time
     */
    public double getPickTime() {
        return pickTime.calculate(getTime());
    }

    /**
     * Returns the calculated PayTime.
     *
     * @return the pay time
     */
    public double getPayTime() {
        return payTime.calculate(getTime());
    }

    /**
     * Returns the last customers' pay time.
     *
     * @return the double
     */
    public double getCustomerLastPayedTime() {
        return this.lastCustomerPayedTime;
    }

    /**
     * Used to calculate duration of the unused registers.
     *
     * @param time the time
     */
    public void addTimeRegistersUnused(double time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som kassor varit oanvända");
        }

        timeRegistersNotUsed += time;
    }

    /**
     * Returns the total time of unused registers.
     *
     * @return the time registers not used
     */
    public double getTimeRegistersNotUsed() {
        return timeRegistersNotUsed;
    }

    /**
     * Used to calculate time spent in the queue.
     *
     * @param time the time
     */
    public void addTimeInQueue(double time) {
        if (time < 0) {
            throw new RuntimeException("kan inte ta bort tid som folk har stått i kassakön");
        }

        timeInQueue += time;
    }

    /**
     * Returns the total time spent in the queue
     *
     * @return the time in queue
     */
    public double getTimeInQueue() {
        return timeInQueue;
    }


    /**
     * Returns the average number of customers arriving per unit of time.
     *
     * @return the double lambda
     */
    public double lambda() {
        return arrivalTime.getLambda();
    }

    /**
     * Returns the random integer used to randomize the simulation for ArrivalTime.
     *
     * @return the long
     */
    public long seed() {
        return arrivalTime.getSeed();
    }

    /**
     * Returns minimum pickTime.
     *
     * @return the double
     */
    public double pMin() {
        return pickTime.getPMin();
    }

    /**
     * Returns maximum pickTime.
     *
     * @return the double
     */
    public double pMax() {
        return pickTime.getPMax();
    }

    /**
     * Returns minimum payTime.
     *
     * @return the double
     */
    public double kMin() {
        return payTime.getKMin();
    }

    /**
     * Returns maximum payTime.
     *
     * @return the double
     */
    public double kMax() {
        return pickTime.getPMax();
    }

}
