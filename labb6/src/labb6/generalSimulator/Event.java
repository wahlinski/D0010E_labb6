/**
 * This general Event class can be inherited and used to specific events.
 *  @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */

package labb6.generalSimulator;

import labb6.util.EventName;

public abstract class Event {
    protected double time;
    protected EventQueue eventQueue;
    protected EventName name;

    /**
     * Instantiates a new Event.
     *
     * @param eventQueue the event queue stored in an Arraylist.
     * @param time the time for an event.
     * @throws Error "xxxx"
     */
    public Event(EventQueue eventQueue, double time){
        this.eventQueue = eventQueue;
        this.time = time;
    }

    /**
     * Execute.
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
     * @param e  specific event to be added in the queue
     */
    public void addToQueue(Event e) {
        eventQueue.addEvent(e);
    }

    protected void setName(EventName name) {
        this.name = name;
    }

    public EventName getName() {
        return name;
    }

    public String toString() {
        return this.name + "";
    }
}
