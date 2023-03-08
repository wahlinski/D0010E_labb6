package labb6.general;

import labb6.supermarket.EventName;

/**
 * An abstract class that can be extended to specify which type of events occur in the simulation.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class Event {
    protected double time;
    protected EventQueue eventQueue;
    protected EventName name;

    /**
     * Instantiates a new Event.
     *
     * @param eventQueue the event queue.
     * @param time       the time for an event.
     */
    public Event(EventQueue eventQueue, double time) {
        this.eventQueue = eventQueue;
        this.time = time;
    }

    /**
     * Execute the event. Changes the state and marks it
     * as updated which allows Observers (View) to update.
     * <p>
     *     When overriding, make sure to call {@code super.execute()} before anything else
     * </p>
     *
     * @param state the state
     */
    public void execute(State state) {
        state.update(this);
    }

    /**
     * Gets time for the event.
     *
     * @return the time as a double
     */
    public double getTime() {
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
     * Gets name of the event.
     *
     * @return the EventName.
     */
    public EventName getName() {
        return name;
    }

    /**
     * Sets EventName.
     *
     * @param name the name of the event
     */
    protected void setName(EventName name) {
        this.name = name;
    }

    public String toString() {
        return this.name + "";
    }
}
