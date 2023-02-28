/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.generalSimulator;

/**
 * This class inherits Event class. Only responsible for stopping an Event
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
import labb6.util.EventName;

public class StopEvent extends Event{
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

    @Override
    public void execute(State state) {
        super.execute(state);
        state.stop();
    }
}
