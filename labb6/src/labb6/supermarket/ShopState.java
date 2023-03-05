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

    /**
     * Calculates the time customers has been in queue and
     * the time registers has been unused.
     * @param event the event that occurred
     */
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

    /**
     * Opens the store
     */
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
     * Closes the shop.
     */
    public void closeShop() {
        storeOpened = false;
    }

    /**
     * Gets the amount of open registers
     *
     * @return the amount of open registers
     */
    public int openRegisters() {
        return unusedRegisters;
    }

    /**
     * Occupies a register.
     *
     * @throws RuntimeException if {@link #openRegisters()} < 1
     */
    public void occupyRegister() throws RuntimeException {
        if (unusedRegisters < 1) {
            throw new RuntimeException("There are no available registers.");
        }

        unusedRegisters -= 1;
    }

    /**
     * Opens up a used register.
     *
     * @throws RuntimeException if {@link #openRegisters()} >= {@link #getMaxRegisters()}
     */
    public void freeUpRegister() throws RuntimeException {
        if (unusedRegisters >= maxRegisters) {
            throw new RuntimeException("None of the registers are being used.");
        }

        unusedRegisters += 1;
    }

    /**
     * Returns maximum amount of registers.
     *
     * @return the max amount of registers.
     */
    public int getMaxRegisters() {
        return maxRegisters;
    }

    /**
     * Returns maximum people in store.
     *
     * @return the max people in store.
     */
    public int getMaxPeopleInStore() {
        return maxPeopleInStore;
    }

    /**
     * Returns number of people in the store.
     *
     * @return the people in store.
     */
    public int getPeopleInStore() {
        return peopleInStore;
    }

    /**
     * Checks if more people are allowed to enter the store.
     *
     * @return {@code true} if another customer can enter.
     */
    public boolean canCustomerGoIn() {
        return getMaxPeopleInStore() > getPeopleInStore();
    }

    /**
     * Allows another customer to enter the store.
     *
     * @throws RuntimeException if there already are max amount of people in the store.
     */
    public void addPeopleInStore() throws RuntimeException {
        if (peopleInStore >= maxPeopleInStore) {
            throw new RuntimeException("Maximum number of customers already.");
        }
        peopleInStore += 1;
    }

    /**
     * Decreases the number of people in the store.
     *
     * @throws RuntimeException if there are no customers in the store.
     */
    public void personLeftStore() {
        if (peopleInStore <= 0) {
            throw new RuntimeException("Store is empty.");
        }

        peopleInStore -= 1;
    }

    /**
     * Adds another person missed.
     */
    public void addPersonMissed() {
        peopleMissed += 1;
    }

    /**
     * Adds another person paid.
     */
    public void addPersonPaid() {
        peoplePaid += 1;
    }

    /**
     * Adds number of people queued in total.
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
     * Returns the customer queue.
     *
     * @return the customer queue.
     */
    public CustomerQueue getCustomerQueue() {
        return customerQueue;
    }

    /**
     * Creates a new customer.
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
     * Returns the time the last customer paid.
     *
     * @return the time the last customer paid.
     */
    public double getCustomerLastPayedTime() {
        return this.lastCustomerPayedTime;
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
     * @return the arrival time lambda
     */
    public double lambda() {
        return arrivalTime.getLambda();
    }

    /**
     * Returns the random integer used to randomize the simulation for ArrivalTime.
     *
     * @return the seed
     */
    public long seed() {
        return arrivalTime.getSeed();
    }

    /**
     * Returns minimum pickTime.
     *
     * @return minimum pickTime.
     */
    public double pMin() {
        return pickTime.getPMin();
    }

    /**
     * Returns maximum pickTime.
     *
     * @return maximum pickTime.
     */
    public double pMax() {
        return pickTime.getPMax();
    }

    /**
     * Returns minimum payTime.
     *
     * @return minimum payTime.
     */
    public double kMin() {
        return payTime.getKMin();
    }

    /**
     * Returns maximum payTime.
     *
     * @return maximum payTime.
     */
    public double kMax() {
        return payTime.getKMax();
    }

}
