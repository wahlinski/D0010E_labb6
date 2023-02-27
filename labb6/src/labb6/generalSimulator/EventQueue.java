package labb6.generalSimulator;

import java.util.ArrayList;

public class EventQueue extends ArrayList<Event>{
    private int numberOfEvents;

    public EventQueue(){
       super();
    }
    public void addEvent(Event event){
        for (int i = 0; i < this.size(); i++){
            if (event.getTime() < this.get(i).getTime()){
                this.add(event);
            }
        }
    }
    public Event removeEvent(int index){
        return this.remove(index);
    }
    public Event getEvent(int index){
        return this.get(index);
    }
}
