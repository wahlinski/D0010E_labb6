/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.generalSimulator;

public abstract class Event {
    protected double time;
    protected EventQueue eventQueue;

    public Event(EventQueue eventQueue, double time){
        this.eventQueue = eventQueue;
        this.time = time;
    }
    public void execute(State state) {
        state.update();
    }

    public double getTime(){
        return this.time;
    }
    public void addToQueue(Event e) {
        eventQueue.addEvent(e);
    }
}
