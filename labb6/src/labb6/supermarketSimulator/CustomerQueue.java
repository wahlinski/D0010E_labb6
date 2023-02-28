package labb6.supermarketSimulator;
/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
import java.util.ArrayList;


public class CustomerQueue extends ArrayList<Customer> {
    private ShopState state;
    public CustomerQueue(ShopState state){
        super();
        this.state = state;
    }
    public void addCustomer(Customer customer){
        this.add(customer);
        state.addPeopleHaveQueued();
    }
    public Customer first(){
        return this.get(0);
    }
    public void removeFirst(){
        if(this.size() != 0){
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
        t = t.substring(0, t.length()-2);
        return "[" + t + "]";
    }
}
