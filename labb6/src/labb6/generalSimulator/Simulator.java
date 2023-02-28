
package labb6.generalSimulator;

/**
 * The simulator is responsible for calling each event's execute method.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class Simulator{
    private State state;
    private EventQueue eventQueue;

    /**
     * Instantiates a new simulator.
     *
     * @param eventQueue the event queue stored in an Arraylist.
     * @param state      the state describes the current state.
     * @throws IllegalArgumentException "xxx"
     */
    public Simulator(EventQueue eventQueue, State state){
        this.eventQueue = eventQueue;
        this.state = state;
    }

    /**
     * Current returns the current state of the simulator.
     *
     * @return the state
     */
    public State currentState(){
        return this.state;
    }

    /**
     * Runs the simulator.
     * @throws Error "XXXX"
     */
    public abstract void run();
}