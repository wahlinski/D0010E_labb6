package labb6.supermarketSimulator;

/**
 * This Class generates a Customer id.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class CustomerIDGenerator {
    private int currentID;

    /**
     * Instantiates a new Customer id generator.
     */
    public CustomerIDGenerator(){
        this.currentID = 0;
    }

    /**
     * Gets a new customerId.
     *
     * @return New CustomerId
     * @throws ArithmeticException if currentId < 0
     */
    public int getNewID(){
        this.currentID++;
        return this.currentID-1;
    }

    /**
     * Gets a customerId.
     *
     * @return  int
     * @throws ArithmeticException if currentId < 0
     */
    public int getID(){
        return this.currentID-1;
    }
}
