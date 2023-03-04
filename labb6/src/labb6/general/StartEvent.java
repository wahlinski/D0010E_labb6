/**
 * @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
 */
package labb6.general;

/**
 * Responsible for starting a simulation.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class StartEvent extends Event {

    /**
     * Instantiates a new Start event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     */
    public StartEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
    }

    /**
     * Begins the simulation.
     * @param state the state
     */
    @Override
    public void execute(State state) {
        super.execute(state);
        state.begin();
    }
}
