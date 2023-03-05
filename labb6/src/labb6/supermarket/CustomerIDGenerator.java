package labb6.supermarket;

/**
 * This Class generates a Customer id a way of differentiating customers in the store.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class CustomerIDGenerator {
    private int currentID;

    /**
     * Instantiates a new Customer id generator.
     */
    public CustomerIDGenerator() {
        this.currentID = 0;
    }

    /**
     * Gets a new customerID.
     *
     * @return New CustomerID
     */
    public int getNewID() {
        this.currentID++;
        return this.currentID - 1;
    }
}
