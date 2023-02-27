package labb6.supermarketSimulator;
/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
import java.util.ArrayList;


public class CustomerQueue extends ArrayList<Integer> {
    public CustomerQueue(){
        super();
    }
    public void addCustomer(int customer){
        this.add(customer);
    }
    public int first(){
        return this.get(0);
    }
    public void removeFirst(){
        if(this.size() != 0){
            this.remove(0);
        }
    }
}
