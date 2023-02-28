/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.main;

import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.StopEvent;
import labb6.supermarketSimulator.ShopSimulator;
import labb6.supermarketSimulator.ShopState;
import labb6.supermarketSimulator.ShopView;
import labb6.supermarketSimulator.events.ShopCloseEvent;
import labb6.supermarketSimulator.events.ShopOpenEvent;

@SuppressWarnings("deprecation")
public class RunSim {
    private EventQueue eventQueue;
    private ShopState state;

    public RunSim(int maxRegisters, int maxInStore, double lambda, double pMin, double pMax, double kMin, double kMax, int seed, double closeTime, double stopTime) {

        eventQueue = new EventQueue();
        eventQueue.addEvent(new ShopOpenEvent(eventQueue, 0));
        eventQueue.addEvent(new ShopCloseEvent(eventQueue, closeTime));
        eventQueue.addEvent(new StopEvent(eventQueue, stopTime));


        state = new ShopState(
                maxRegisters,
                maxInStore,
                lambda,
                pMin,
                pMax,
                kMin,
                kMax,
                seed
        );

    }
    public void startSimulator(boolean withView) {
        if (withView) {
            ShopView view = new ShopView();
            state.addObserver(view);
        }

        ShopSimulator sim = new ShopSimulator(eventQueue, state);
        sim.run();
    }
    public static void main(String[] args){
        // testkör simulatorexempel 1 ur labbspecen

        RunSim runsim = new RunSim(2, 5, 1.0, 0.5, 1.0, 2.0, 3.0, 1234, 10.0, 999.0);
        runsim.startSimulator(true);
    }
}