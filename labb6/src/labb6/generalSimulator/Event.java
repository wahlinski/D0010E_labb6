
package labb6.generalSimulator;

/**
 * This general Event class can be inherited and used to specific events.
 *  @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class Event {
    protected int time;
    protected EventQueue eventQueue;

    /**
     * Instantiates a new Event.
     *
     * @param eventQueue the event queue stored in an Arraylist.
     * @param time the time for an event.
     * @throws Error "xxxx"
     */
    public Event(EventQueue eventQueue, int time){
        this.eventQueue = eventQueue;
        this.time = time;
    }

    /**
     * Execute.
     *
     * @param state the state
     */
    public void execute(State state) {
        state.update();
    }

    /**
     * Gets time for the event.
     *
     * @return the time as an int
     */
    public int getTime(){
        return this.time;
    }

    /**
     * Appends the specified event to the eventQueue.
     *
     * @param e  specific event to be added in the queue
     */
    public void addToQueue(Event e) {
        eventQueue.addEvent(e);
    }
}
