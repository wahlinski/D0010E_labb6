package labb6.supermarketSimulator.events;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.State;
import labb6.supermarketSimulator.ShopState;

public abstract class CustomerEvent extends Event {
    public CustomerEvent(EventQueue eventQueue, int time) {
        super(eventQueue, time);
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
