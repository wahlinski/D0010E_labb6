package labb6.generalSimulator;

/**
 * This class inherits Event class. Only responsible for starting an Event
 *
 * @Author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class StartEvent extends Event {

    /**
     * Instantiates a new Start event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     * @throws Error "xxx"
     */
    public StartEvent(EventQueue eventQueue, int time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        state.begin();
    }
}
