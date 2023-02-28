/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.generalSimulator;

public abstract class Simulator{
    protected State state;
    protected EventQueue eventQueue;

    public Simulator(EventQueue eventQueue, State state){
        this.eventQueue = eventQueue;
        this.state = state;
    }
    public State currentState(){
        return this.state;
    }
    public void run() {

        System.out.println("startar simulering " + eventQueue);

        while (!state.isStopped()) {
            Event e = eventQueue.getEvent(0);
            eventQueue.removeEvent(0);
            e.execute(state);
        }
    }
}