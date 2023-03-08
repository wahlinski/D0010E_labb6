package labb6.supermarket.events;

import labb6.general.Event;
import labb6.general.EventQueue;
import labb6.general.State;
import labb6.supermarket.ShopState;
import labb6.supermarket.EventName;

/**
 * This class keeps track of when the store is closed.
 *
 * @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
 */
public class ShopCloseEvent extends Event {
    /**
     * Instantiates a new Shop close event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     */
    public ShopCloseEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
        setName(EventName.STÄNGER);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        if (state instanceof ShopState state2) {
            state2.closeShop();
        }
    }
}
