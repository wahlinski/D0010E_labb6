/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator.events;
import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.State;
import labb6.supermarketSimulator.Customer;
import labb6.supermarketSimulator.ShopState;


/**
 * This inherits the general Event class and creates a specific event for customers.
 *
 *  @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class CustomerEvent extends Event {
    protected Customer customer;
    /**
     * Instantiates a new Customer event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @param customerID the customer id to keep track of the customers
     */
    public CustomerEvent(EventQueue eventQueue, double time, Customer customer) {
        super(eventQueue, time);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
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
