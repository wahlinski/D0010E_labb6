package labb6.generalSimulator;
import labb6.util.EventName;

/**
 * An abstract class that can be expanded to specify which type of events occur in the simulation.
 */
public abstract class Event {
    protected double time;
    protected EventQueue eventQueue;
    protected EventName name;

    /**
     * Instantiates a new Event.
     *
     * @param eventQueue the event queue stored in an Arraylist.
     * @param time       the time for an event.
     * @throws IllegalArgumentException if incorrect types are passed through.
     */
    public Event(EventQueue eventQueue, double time){
        this.eventQueue = eventQueue;
        this.time = time;
    }

    /**
     * Execute by changing the State .
     *
     * @param state the state
     */
    public void execute(State state) {
        state.update(this);
    }

    /**
     * Gets time for the event.
     *
     * @return the time as an int
     */
    public double getTime(){
        return this.time;
    }

    /**
     * Appends the specified event to the eventQueue.
     *
     * @param e specific event to be added in the queue
     */
    public void addToQueue(Event e) {
        eventQueue.addEvent(e);
    }

    /**
     * Sets EventName.
     *
     * @param name the name of the event
     */
    protected void setName(EventName name) {
        this.name = name;
    }

    /**
     * Gets name of the event.
     *
     * @return the EventName.
     */
    public EventName getName() {
        return name;
    }

    public String toString() {
        return this.name + "";
    }
}
