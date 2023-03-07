package labb6.general;

import java.util.Observable;

/**
 * General class for keeping track of the different states during a simulation.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
@SuppressWarnings("deprecation")
public abstract class State extends Observable {
    private double currentTime;
    private boolean stopped;

    /**
     * Instantiates a new State.
     */
    public State() {

    }

    /**
     * @return current time
     */
    public double getTime() {
        return this.currentTime;
    }

    /**
     * Sets the current time.
     *
     * @param time the time
     * @throws RuntimeException if time {@literal <} 0
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
     * @return true if a stop event has occurred, false otherwise.
     */
    public boolean isStopped() {
        return this.stopped;
    }

    /**
     * Begins the simulation
     */
    public abstract void begin();

    /**
     * Stops the simulation
     */
    public void stop() {
        stopped = true;
    }

    /**
     * Notifies all observers that the state has updated
     */
    public void update(Event event) {
        setTime(event.getTime());

        setChanged();
        notifyObservers(event);
    }
}
