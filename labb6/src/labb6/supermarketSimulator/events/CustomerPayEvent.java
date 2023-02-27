/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator.events;

import labb6.generalSimulator.EventQueue;
import labb6.supermarketSimulator.ShopState;

public class CustomerPayEvent extends CustomerEvent {


    public CustomerPayEvent(EventQueue eventQueue, double time, int customerID) {
        super(eventQueue, time, customerID);
    }

    @Override
    public void execute(ShopState state) {

    }
}
