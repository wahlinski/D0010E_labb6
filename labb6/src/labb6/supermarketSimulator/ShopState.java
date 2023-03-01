package labb6.supermarketSimulator;
import labb6.generalSimulator.Event;
import labb6.generalSimulator.State;
import labb6.generalSimulator.StopEvent;
import labb6.supermarketSimulator.events.CustomerPayEvent;

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
     * @param maxInStore   the max in store
     * @param lambda       the lambda
     * @param pMin         the p min
     * @param pMax         the p max
     * @param kMin         the k min
     * @param kMax         the k max
     * @param seed         the seed
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
     *
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
     *
     * @return the boolean
     */
    public boolean canCustomerGoIn() {
        return getMaxPeopleInStore() > getPeopleInStore();
    }

    /**
     * Add people in store.
     */
    public void addPeopleInStore() {
        if (peopleInStore >= maxPeopleInStore) {
            throw new RuntimeException("för många i butiken");
        }
        peopleInStore += 1;
    }

    /**
     * Person left store.
     */
    public void personLeftStore() {
        if (peopleInStore <= 0) {
            throw new RuntimeException("ingen som kan gå ut");
        }

        peopleInStore -= 1;
    }

    /**
     * Add person missed.
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
    public void addPeopleHaveQueued() {
        this.peopleQueued++;
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
     * Gets people have queued.
     *
     * @return the people have queued
     */
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

    /**
     * Create customer customer.
     *
     * @return the customer
     */
    public Customer createCustomer() {
        return new Customer(idGenerator.getNewID());
    }

    /**
     * Gets arrival time.
     *
     * @return the arrival time
     */
    public double getArrivalTime() {
        return arrivalTime.calculate(getTime());
    }

    /**
     * Gets pick time.
     *
     * @return the pick time
     */
    public double getPickTime() {
        return pickTime.calculate(getTime());
    }

    /**
     * Gets pay time.
     *
     * @return the pay time
     */
    public double getPayTime() {
        return payTime.calculate(getTime());
    }

    /**
     * Get customer last payed time double.
     *
     * @return the double
     */
    public double getCustomerLastPayedTime(){
        return this.lastCustomerPayedTime;
    }

    /**
     * Add time registers unused.
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
     * Gets time registers not used.
     *
     * @return the time registers not used
     */
    public double getTimeRegistersNotUsed() {
        return timeRegistersNotUsed;
    }

    /**
     * Add time in queue.
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
     * Gets time in queue.
     *
     * @return the time in queue
     */
    public double getTimeInQueue() {
        return timeInQueue;
    }


    /**
     * Lambda double.
     *
     * @return the double
     */
    public double lambda() {
        return arrivalTime.getLambda();
    }

    /**
     * Seed long.
     *
     * @return the long
     */
    public long seed() {
        return arrivalTime.getSeed();
    }

    /**
     * P min double.
     *
     * @return the double
     */
    public double pMin() {
        return pickTime.getPMin();
    }

    /**
     * P max double.
     *
     * @return the double
     */
    public double pMax() {
        return pickTime.getPMax();
    }

    /**
     * K min double.
     *
     * @return the double
     */
    public double kMin() {
        return payTime.getKMin();
    }

    /**
     * K max double.
     *
     * @return the double
     */
    public double kMax() {
        return pickTime.getPMax();
    }

}
