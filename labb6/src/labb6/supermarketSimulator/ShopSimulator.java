/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.Simulator;
import labb6.generalSimulator.State;

public class ShopSimulator extends Simulator{
    public ShopSimulator(EventQueue eventQueue, ShopState state) {
        super(eventQueue, state);
    }

    @Override
    public void run() {
        super.run();

        if (state instanceof ShopState shop) {
            System.out.println("Betalade: " + shop.getPeoplePaid());
            System.out.println("Missade: " + shop.getPeopleMissed());
            System.out.println("Tid i kö: " + shop.getTimeInQueue());
            System.out.println("Tid oanvänd: " + shop.getTimeRegistersNotUsed());
        }
    }
}
