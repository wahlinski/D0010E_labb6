/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.generalSimulator;

import java.util.Observable;

/**
 * State is a general class for the simulator to keep track of different states in the simulator.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
@SuppressWarnings("deprecation")
public abstract class State extends Observable{
    private int currentTime;
    private boolean stopped;

    /**
     * Instantiates a new State.
     */
    public State(){

    }

    /**
     * Get time int.
     *
     * @return the int time
     */
    public int getTime(){
        return this.currentTime;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(int time) {
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
    public void update() {
        setChanged();
        notifyObservers();
    }
}
