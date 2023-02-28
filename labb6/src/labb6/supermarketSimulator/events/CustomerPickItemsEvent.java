/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.EventQueue;
import labb6.supermarketSimulator.CustomerQueue;
import labb6.supermarketSimulator.ShopState;
import labb6.util.EventNames;

public class CustomerPickItemsEvent extends CustomerEvent {

    public CustomerPickItemsEvent(EventQueue eventQueue, double time, Customer customer) {
        super(eventQueue, time, customer);
        setName(EventNames.PLOCK + "");
    }

    @Override
    public void execute(ShopState state) {
        // kolla ifall CustomerQueue är tom
        CustomerQueue queue = state.getCustomerQueue();
        if(state.getAvailableRegisters() <= 0) {
            queue.addCustomer(customer);
            return;
        }

        // skapa nytt PayEvent
        CustomerPayEvent payEvent = new CustomerPayEvent(eventQueue, state.getPayTime(), customer);
        eventQueue.addEvent(payEvent);
    }
}
