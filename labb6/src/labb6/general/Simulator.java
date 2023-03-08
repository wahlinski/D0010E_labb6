/**
 * @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
 */
package labb6.general;

/**
 * The simulator is responsible for making sure every {@link Event} happens.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class Simulator {
    protected State state;
    protected EventQueue eventQueue;

    /**
     * Instantiates a new simulator.
     *
     * @param eventQueue the event queue stored in an Arraylist.
     * @param state      the state describes the current state.
     */
    public Simulator(EventQueue eventQueue, State state) {
        this.eventQueue = eventQueue;
        this.state = state;
    }

    /**
     * Current returns the current state of the simulator.
     *
     * @return the state
     */
    public State currentState() {
        return this.state;
    }

    /**
     * Runs the simulator until a stop event has occurred.
     * @throws IndexOutOfBoundsException if there are no events to execute.
     */
    public void run() {
        while (!state.isStopped()) {
            Event event = eventQueue.getEvent(0);
            eventQueue.removeEvent(0);
            event.execute(state);
        }
    }
}