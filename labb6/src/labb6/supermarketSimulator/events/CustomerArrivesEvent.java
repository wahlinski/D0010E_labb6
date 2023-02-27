/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.EventQueue;
import labb6.supermarketSimulator.ShopState;

public class CustomerArrivesEvent extends CustomerEvent {
    public CustomerArrivesEvent(EventQueue eventQueue, int time, int customerID) {
        super(eventQueue, time, customerID);
    }

    @Override
    public void execute(ShopState state) {

    }
    private CustomerArrivesEvent
}
