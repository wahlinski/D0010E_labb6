/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.StartEvent;
import labb6.generalSimulator.State;
import labb6.supermarketSimulator.ShopState;
import labb6.util.EventName;

/**
 * This class keeps track of when the store is open.
 */
public class ShopOpenEvent extends StartEvent {
    /**
     * Instantiates a new Shop open event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     */
    public ShopOpenEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
        setName(EventName.START);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        if (state instanceof ShopState shopState) {
            CustomerArrivesEvent e = new CustomerArrivesEvent(eventQueue, shopState.getArrivalTime(), shopState.createCustomer());
            eventQueue.addEvent(e);
        }
    }
}
