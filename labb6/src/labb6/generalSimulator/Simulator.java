/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.generalSimulator;

public abstract class Simulator{
    private State state;
    private EventQueue eventQueue;

    public Simulator(EventQueue eventQueue, State state){
        this.eventQueue = eventQueue;
        this.state = state;
    }
    public State currentState(){
        return this.state;
    }
    public abstract void run();
}