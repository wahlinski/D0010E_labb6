/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */

package labb6.generalSimulator;

import java.util.ArrayList;

public class EventQueue extends ArrayList<Event>{
    private int numberOfEvents;

    public EventQueue(){
       super();
    }
    public void addEvent(Event event){
        /*
        for (int i = 0; i < this.size(); i++){
            if (event.getTime() < this.get(i).getTime()){
                this.add(event);
            }
        }
        */

        this.add(event);
        sort((o1, o2) -> {
            double x = o1.getTime() - o2.getTime();
            if (x > 0) return 1;
            if (x < 0) return -1;
            return 0;
        });
    }
    public Event removeEvent(int index){
        return this.remove(index);
    }
    public Event getEvent(int index){
        return this.get(index);
    }
}
