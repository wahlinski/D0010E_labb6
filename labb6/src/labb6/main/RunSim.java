package labb6.main;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.StopEvent;
import labb6.supermarketSimulator.ShopSimulator;
import labb6.supermarketSimulator.ShopState;
import labb6.supermarketSimulator.ShopView;
import labb6.supermarketSimulator.events.ShopCloseEvent;
import labb6.supermarketSimulator.events.ShopOpenEvent;

/**
 * Runs the specific simulator.
 *  @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
@SuppressWarnings("deprecation")
public class RunSim {
    private EventQueue eventQueue;
    private ShopState state;
    
    /**
     * Runs the specific simulator.
     *
     * @param m         the m specifies max amount of customers in the store
     * @param l         the l specifies the ArrivalTime speed lambda
     * @param pMin      the p Min specifies minimum pickTime
     * @param pMax      the p max specifies the maximum pickTime
     * @param kMin      the k min specifies minimum PayTime
     * @param kMax      the k max specifies maximum PayTime
     * @param seed      the seed is an array of numbers used by RandomStream
     * @param closeTime the close time specifies when to close the store
     * @param stopTime  the stop time specifies when to stop the time ???
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
    /**
     * Start simulator.
     *
     * @param withView the with view???
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

    /**
     * Main.
     *
     * @param args the args
     *  Prints the whole simulator in different stages???
     */
    public static void main(String[] args){
        // testkör simulatorexempel 1 ur labbspecen

        //Värdena är från Håkans, det är de variabler för första körningen av labb-specen.
        RunSim runsim = new RunSim(2, 5, 1.0, 0.5, 1.0, 2.0, 3.0, 1234, 10.0, 999.0);
        runsim.startSimulator(true);
    }
}