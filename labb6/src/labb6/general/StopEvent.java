package labb6.general;

import labb6.supermarket.EventName;

/**
 * Responsible for stopping a simulation.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class StopEvent extends Event {
    /**
     * Instantiates a new Stop event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     */
    public StopEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
        setName(EventName.STOP);
    }

    /**
     * Stops the simulation
     * @param state the state
     */
    @Override
    public void execute(State state) {
        super.execute(state);
        state.stop();
    }
}
