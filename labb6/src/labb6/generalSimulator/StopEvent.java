package labb6.generalSimulator;

/**
 * This class inherits Event class. Only responsible for stopping an Event
 * @Author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class StopEvent extends Event{
    /**
     * Instantiates a new Stop event.
     *
     * @param eventQueue the event queue
     * @param time       the time
     */
    public StopEvent(EventQueue eventQueue, int time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        state.stop();
    }
}
