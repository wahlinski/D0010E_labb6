/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.EventQueue;
import labb6.supermarketSimulator.Customer;
import labb6.supermarketSimulator.CustomerQueue;
import labb6.supermarketSimulator.ShopState;
import labb6.util.EventName;

/**
 * This class keeps track of when customer has finished picking items.
 */
public class CustomerPickItemsEvent extends CustomerEvent {

    /**
     * Instantiates a new Customer pick items event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @param customer   the customer
     */
    public CustomerPickItemsEvent(EventQueue eventQueue, double time, Customer customer) {
        super(eventQueue, time, customer);
        setName(EventName.PLOCK);
    }

    @Override
    public void execute(ShopState state) {
        // kolla ifall det finns lediga kassor
        if(state.openRegisters() <= 0) {
            CustomerQueue queue = state.getCustomerQueue();
            queue.addCustomer(customer);
            return;
        }

        // skapa nytt PayEvent
        CustomerPayEvent payEvent = new CustomerPayEvent(eventQueue, state.getPayTime(), customer);
        eventQueue.addEvent(payEvent);
        state.occupyRegister();
    }
}
