package labb6.supermarketSimulator.events;
import labb6.generalSimulator.EventQueue;
import labb6.supermarketSimulator.ShopState;


/**
 * This class creates a new Customer event .
 */
public class CustomerArrivesEvent extends CustomerEvent {
    /**
     * Instantiates a new Customer arrives event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @param customerID the customer id
     */
    public CustomerArrivesEvent(EventQueue eventQueue, int time, int customerID) {
        super(eventQueue, time, customerID);
    }

    @Override
    public void execute(ShopState state) {

    }
    private CustomerArrivesEvent
}
