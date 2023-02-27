package labb6.supermarketSimulator.events;

import labb6.generalSimulator.EventQueue;
import labb6.supermarketSimulator.ShopState;

public class CustomerPickItemsEvent extends CustomerEvent {
    public CustomerPickItemsEvent(EventQueue eventQueue, int time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(ShopState state) {

    }
}
