/**
 * @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
 */
package labb6.supermarket.events;

import labb6.general.EventQueue;
import labb6.supermarket.Customer;
import labb6.supermarket.ShopState;
import labb6.util.EventName;


/**
 * This keeps track of when customer arrives to the store.
 *@author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 *
 */
public class CustomerArrivesEvent extends CustomerEvent {
    /**
     * Instantiates a new Customer arrives event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @param customer   the customer
     */
    public CustomerArrivesEvent(EventQueue eventQueue, double time, Customer customer) {
        super(eventQueue, time, customer);
        setName(EventName.ANKOMST);
    }

    @Override
    public void execute(ShopState state) {
        // kolla ifall det finns plats i butiken
        if (!state.isOpen()) {
            // det är ju inte lönt att skapa nya ankomstevent om butiken är stängd
            return;
        }

        // Nytt ankomstevent
        CustomerArrivesEvent arrivesEvent = new CustomerArrivesEvent(eventQueue, state.getArrivalTime(), state.createCustomer());
        eventQueue.addEvent(arrivesEvent);

        if (!state.canCustomerGoIn()) {
            // fullt i butiken
            state.addPersonMissed();
            return;
        }

        state.addPeopleInStore();

        // Nytt plockevent
        CustomerPickItemsEvent pickEvent = new CustomerPickItemsEvent(eventQueue, state.getPickTime(), customer);
        eventQueue.addEvent(pickEvent);
    }
}
