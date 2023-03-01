package labb6.supermarketSimulator;
import labb6.generalSimulator.EventQueue;
import labb6.generalSimulator.Simulator;

/**
 * This simulator is responsible for calling each event's execute method.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class ShopSimulator extends Simulator{
    /**
     * Instantiates a new Shop simulator.
     *
     * @param eventQueue the event queue
     * @param state      the state
     */
    public ShopSimulator(EventQueue eventQueue, ShopState state) {
        super(eventQueue, state);
    }

    @Override
    public void run() {
        super.run();
    }
}
