/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.State;
import labb6.supermarketSimulator.Customer;
import labb6.supermarketSimulator.ShopState;

public abstract class CustomerEvent extends Event {
    protected Customer customer;
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

    public abstract void execute(ShopState state);
}
