package labb6.supermarket;

/**
 * This class is for keeping track of customers this object called Custmer.
 *
 * @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
 */
public class Customer {

    private int customerID;

    /**
     * Instantiates a new Customer.
     *
     * @param customerID the customer id unique identifier for each customer.
     */
    public Customer(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Get customer id integer.
     *
     * @return the int customerID
     */
    public int getCustomerID() {
        return this.customerID;
    }
}