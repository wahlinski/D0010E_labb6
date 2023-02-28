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

public class CustomerPayEvent extends CustomerEvent {


    public CustomerPayEvent(EventQueue eventQueue, double time, Customer customer) {
        super(eventQueue, time, customer);
        setName(EventNames.BETALNING + "");
    }

    @Override
    public void execute(ShopState state) {

        state.addPersonPaid();
        state.personLeftStore();

        CustomerQueue queue = state.getCustomerQueue();
        if(queue.size() > 0) {
            Customer customer = queue.first();
            queue.removeFirst();
            CustomerPayEvent payEvent = new CustomerPayEvent(eventQueue, state.getPayTime(), customer);
            eventQueue.addEvent(payEvent);
        }


    }
}
