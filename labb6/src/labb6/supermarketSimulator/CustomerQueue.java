package labb6.supermarketSimulator;
import java.util.ArrayList;


/**
 * CustomerQueue keeps track of the queue in the shop with help of ArrayList.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class CustomerQueue extends ArrayList<Integer> {
    /**
     * Instantiates a new Customer queue.
     */
    public CustomerQueue(){
        super();
    }

    /**
     * Adds customer to the customerQueue stored in an Arraylist.
     *
     * @param customer the customerQueue
     * @throws Error xxx
     */
    public void addCustomer(int customer){
        this.add(customer);
    }

    /**
     * Returns the first customer in the queue.
     *
     * @return the first customer in the queue.
     * @throws ArrayIndexOutOfBoundsException  if the Arraylist is empty -> Null.
     */
    public int first(){
        return this.get(0);
    }

    /**
     * Remove first customer in the queue.
     * @throws Error if the ArrayList is empty -> Null
     */
    public void removeFirst(){
        if(this.size() != 0){
            this.remove(0);
        }
    }
}
