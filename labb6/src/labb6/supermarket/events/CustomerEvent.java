package labb6.supermarket.events;

import labb6.general.Event;
import labb6.general.EventQueue;
import labb6.general.State;
import labb6.supermarket.Customer;
import labb6.supermarket.ShopState;


/**
 * This inherits the general Event class and creates a specific event for customers.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class CustomerEvent extends Event {
    protected Customer customer;

    /**
     * Instantiates a new Customer event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @param customer   the customer object to keep track of customers.
     */
    public CustomerEvent(EventQueue eventQueue, double time, Customer customer) {
        super(eventQueue, time);
        this.customer = customer;
    }

    /**
     * Returns customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        if (state instanceof ShopState s) {
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
