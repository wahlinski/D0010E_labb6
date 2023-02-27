/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.generalSimulator;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class State extends Observable{
    private int currentTime;
    private boolean stopped;

    public State(){

    }
    public int getTime(){
        return this.currentTime;
    }
    public void setTime(int time) {
        if (time < getTime()) {
            throw new RuntimeException("kan inte gå back i tiden");
        }

        currentTime = time;
    }
    public boolean isStopped(){
        return this.stopped;
    }
    public abstract void begin();
    public void stop() {
        stopped = true;
    }

    public void update() {
        setChanged();
        notifyObservers();
    }
}
