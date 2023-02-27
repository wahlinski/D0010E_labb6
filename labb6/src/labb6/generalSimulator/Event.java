/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.generalSimulator;

public abstract class Event {
    private int time;
    private EventQueue eventQueue;

    public Event(EventQueue eventQueue, int time){
        this.eventQueue = eventQueue;
        this.time = time;
    }
    public void execute(State state) {
        state.update();
    }

    public int getTime(){
        return this.time;
    }
    public void addToQueue(){
        
    }
}
