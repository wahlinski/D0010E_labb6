package labb6.supermarketSimulator.events;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.State;

public class ShopOpenEvent extends Event {
    public ShopOpenEvent(EventQueue eventQueue, int time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
    }
}
