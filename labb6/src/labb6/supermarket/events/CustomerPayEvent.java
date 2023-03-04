/**
 * @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
 */
package labb6.supermarket.events;

import labb6.general.EventQueue;
import labb6.supermarket.Customer;
import labb6.supermarket.CustomerQueue;
import labb6.supermarket.ShopState;
import labb6.util.EventName;

/**
 * This class keeps track of when customers have finished paying for their goods.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class CustomerPayEvent extends CustomerEvent {


    /**
     * Instantiates a new Customer pay event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @param customer   the customer
     */
    public CustomerPayEvent(EventQueue eventQueue, double time, Customer customer) {
        super(eventQueue, time, customer);
        setName(EventName.BETALNING);
    }

    @Override
    public void execute(ShopState state) {

        state.addPersonPaid();
        state.personLeftStore();
        state.freeUpRegister();

        CustomerQueue queue = state.getCustomerQueue();
        if (queue.size() > 0) {
            Customer customer = queue.first();
            queue.removeFirst();
            CustomerPayEvent payEvent = new CustomerPayEvent(eventQueue, state.getPayTime(), customer);
            eventQueue.addEvent(payEvent);
            state.occupyRegister();
        }


    }
}
