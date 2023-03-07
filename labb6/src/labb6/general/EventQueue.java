/**
 * @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
 */

package labb6.general;

import java.util.ArrayList;


/**
 * Keeps track of all the future {@link Event}s to happen.
 * Refer to {@link ArrayList} for more information.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class EventQueue extends ArrayList<Event> {

    /**
     * Instantiates a new Event queue.
     */
    public EventQueue() {
        super();
    }

    /**
     * Appends the specified event and sorts the queue.
     *
     * @param event the event to be appended.
     */
    public void addEvent(Event event) {
        this.add(event);
        // Sorts the queue based on the time the event will happen.
        sort((o1, o2) -> {
            double x = o1.getTime() - o2.getTime();
            if (x > 0) return 1;
            if (x < 0) return -1;
            return 0;
        });
    }

    /**
     * Removes the event at the specified position in this list.
     * Shifts any subsequent event to the left (subtracts one from their indices).
     *
     * @param index the index of the event to be removed
     * @return the event that was removed from the list
     * @throws IndexOutOfBoundsException  if the index is out of range (index &lt; 0 || index >= size())
     */
    public Event removeEvent(int index) {
        return this.remove(index);
    }

    /**
     * Returns the event at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the event at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index >= size())
     */
    public Event getEvent(int index) {
        return this.get(index);
    }
}
