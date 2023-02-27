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

public class ShopCloseEvent extends Event {
    public ShopCloseEvent(EventQueue eventQueue, int time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
    }
}
