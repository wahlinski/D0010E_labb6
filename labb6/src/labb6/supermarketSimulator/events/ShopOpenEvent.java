/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.StartEvent;
import labb6.generalSimulator.State;
import labb6.supermarketSimulator.ShopState;

public class ShopOpenEvent extends StartEvent {
    public ShopOpenEvent(EventQueue eventQueue, int time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        EventQueue queue = getEventQueue();
//        CustomerArrivesEvent e = new CustomerArrivesEvent(queue, ); fixa klart då vi har tidsklasserna
    }
}
