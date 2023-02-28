/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator;

public class Customer{
    
    private int customerID;

    public Customer(int customerID){
        this.customerID = customerID;
    }
    public int getCustomerID(){
        return this.customerID;
    }
}