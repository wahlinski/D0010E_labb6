package labb6.supermarket;

import java.util.ArrayList;

/**
 * CustomerQueue keeps track of the queue in the shop.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class CustomerQueue extends ArrayList<Customer> {
    private ShopState state;

    /**
     * Instantiates a new Customer queue.
     *
     * @param state the state
     */
    public CustomerQueue(ShopState state) {
        super();
        this.state = state;
    }

    /**
     * Adds customer to the customerQueue stored in an Arraylist.
     *
     * @param customer the customerQueue
     */
    public void addCustomer(Customer customer) {
        this.add(customer);
        state.addPeopleHaveQueued();
    }

    /**
     * Returns the first customer in the queue.
     *
     * @return the first customer in the queue.
     * @throws IndexOutOfBoundsException if the Arraylist is empty -> Null.
     */
    public Customer first() throws IndexOutOfBoundsException {
        if (size() == 0) {
            throw new IndexOutOfBoundsException("CustomerQueue is empty.");
        }
        return this.get(0);
    }

    /**
     * Remove first customer in the queue.
     */
    public void removeFirst() {
        if (this.size() != 0) {
            this.remove(0);
        }
    }

    @Override
    public String toString() {
        if (size() <= 0) return "[]";

        String t = "";
        for (Customer c : this) {
            t += c.getCustomerID() + ", ";
        }
        t = t.substring(0, t.length() - 2);
        return "[" + t + "]";
    }
}
