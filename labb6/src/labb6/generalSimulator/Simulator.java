/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.generalSimulator;

/**
 * The simulator is responsible for calling each event's execute method.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public abstract class Simulator{
    protected State state;
    protected EventQueue eventQueue;

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
    public void run() {

        System.out.println("startar simulering " + eventQueue);

        while (!state.isStopped()) {
            Event e = eventQueue.getEvent(0);
            eventQueue.removeEvent(0);
            e.execute(state);
        }
    }
}