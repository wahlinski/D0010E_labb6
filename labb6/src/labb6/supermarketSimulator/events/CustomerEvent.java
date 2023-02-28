package labb6.supermarketSimulator.events;
import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.State;
import labb6.supermarketSimulator.ShopState;


/**
 * This inherits the general Event class and creates a specific event for customers.
 *
 *  @Author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class CustomerEvent extends Event {
    protected int customerID;

    /**
     * Instantiates a new Customer event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @param customerID the customer id to keep track of the customers
     */
    public CustomerEvent(EventQueue eventQueue, int time, int customerID) {
        super(eventQueue, time);
        this.customerID = customerID;
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        if(state instanceof ShopState s) {
            execute(s);
        }
    }

    /**
     * Execute based on the state.
     *
     * @param state the state
     */
    public abstract void execute(ShopState state);
}
