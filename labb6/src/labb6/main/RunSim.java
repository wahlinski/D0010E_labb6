/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.main;

import labb6.generalSimulator.EventQueue;
import labb6.supermarketSimulator.ShopState;
import labb6.supermarketSimulator.events.ShopCloseEvent;
import labb6.supermarketSimulator.events.ShopOpenEvent;

public class RunSim {
    private EventQueue eventQueue;
    private ShopState state;

    public RunSim(int m, double l, double pMin, double pMax, double kMin, double kMax, int seed, double closeTime, double stopTime) {

        eventQueue = new EventQueue();
        eventQueue.addEvent(new ShopOpenEvent(eventQueue, 0));
        eventQueue.addEvent(new ShopCloseEvent(eventQueue, closeTime));



    }
    public void startSimulator(boolean withView){

    }
    public static void main(String[] args){

    }
}