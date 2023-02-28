/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.State;
import labb6.supermarketSimulator.ShopState;
import labb6.util.EventName;

public class ShopCloseEvent extends Event {
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
