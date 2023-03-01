package labb6.generalSimulator;
import java.util.Observable;

/**
 * State is a general class for the simulator to keep track of different states in the simulator.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
@SuppressWarnings("deprecation")
public abstract class State extends Observable{
    private double currentTime;
    private boolean stopped;

    /**
     * Instantiates a new State.
     */
     
    public State(){

    }
    
     /**
     * Get time double.
     *
     * @return the int time
     */
    public double getTime(){
        return this.currentTime;
    }
    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(double time) {
        if (time < getTime()) {
            throw new RuntimeException("kan inte gå back i tiden");
        }

        currentTime = time;
    }

    /**
     * Is stopped boolean.
     *
     * @return the boolean
     */
    public boolean isStopped(){
        return this.stopped;
    }

    /**
     * Begin.
     */
    public abstract void begin();

    /**
     * Stop.
     * @throws Error "Simulator already stopped"
     */
    public void stop() {
        stopped = true;
    }
    /**
     * Update.
     *
     */
    public void update(Event event) {
        setTime(event.getTime());

        setChanged();
        notifyObservers(event);
    }
}
