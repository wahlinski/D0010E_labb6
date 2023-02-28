package labb6.generalSimulator;
import java.util.ArrayList;


/**
 * This class Event queue  keeps track of the events with an Arraylist.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class EventQueue extends ArrayList<Event>{
    private int numberOfEvents;

    /**
     * Instantiates a new Event queue.
     */
    public EventQueue(){
       super();
    }

    /**
     * Appends the specified event to the end of this list.
     *
     * @param event the event to be appended to this list.
     *
     * @throws Error "xxx"
     */
    public void addEvent(Event event){
        for (int i = 0; i < this.size(); i++){
            if (event.getTime() < this.get(i).getTime()){
                this.add(event);
            }
        }
    }

    /**
     * Removes the event at the specified position in this list. Shifts any subsequent event to the left (subtracts one from their indices).
     *
     * @param index the index of the event to be removed
     * @return the event that was removed from the list
     * @throws IndexOutOfBoundsException  if the index is out of range (index < 0 || index >= size())
     */
    public Event removeEvent(int index){
        return this.remove(index);
    }

    /**
     * Returns the event at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the event at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public Event getEvent(int index){
        return this.get(index);
    }
}
