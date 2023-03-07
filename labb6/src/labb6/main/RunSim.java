package labb6.main;

import labb6.general.EventQueue;
import labb6.general.StopEvent;
import labb6.supermarket.ShopSimulator;
import labb6.supermarket.ShopState;
import labb6.supermarket.ShopView;
import labb6.supermarket.events.ShopCloseEvent;
import labb6.supermarket.events.ShopOpenEvent;

/**
 * Runs the specific simulator.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
@SuppressWarnings("deprecation")
public class RunSim {
    private EventQueue eventQueue;
    private ShopState state;

    /**
     *
     * @param maxRegisters max amount of open registers
     * @param maxInStore max number of customers allowed in the store
     * @param lambda    arrival speed of customers
     * @param pMin      the p Min specifies minimum pickTime
     * @param pMax      the p max specifies the maximum pickTime
     * @param kMin      the k min specifies minimum PayTime
     * @param kMax      the k max specifies maximum PayTime
     * @param seed      the seed is an array of numbers used by RandomStream
     * @param closeTime the close time specifies when to close the store
     * @param stopTime  the stop time specifies when to stop
     */
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


    public static void main(String[] args) {
        final boolean firstTest = true;
        if (firstTest) {
            RunSim runsim = new RunSim(2, 5, 1.0, 0.5, 1.0, 2.0, 3.0, 1234, 10.0, 999.0);
            runsim.startSimulator(true);
        } else {
            RunSim runsim = new RunSim(2, 7, 3.0, 0.6, 0.9, 0.35, 0.6, 13, 8.0, 999.0);
            runsim.startSimulator(true);
        }
    }

    /**
     * Starts the simulator.
     *
     * @param withView {@code true} if a {@link ShopView} should observe the state.
     * @return the {@link ShopState} after the simulation is over.
     */
    public ShopState startSimulator(boolean withView) {
        if (withView) {
            ShopView view = new ShopView();
            state.addObserver(view);
        }

        ShopSimulator sim = new ShopSimulator(eventQueue, state);
        sim.run();
        return state;
    }
}