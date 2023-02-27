/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
import java.util.ArrayList;

package labb6.supermarketSimulator;

public class CustomerQueue extends ArrayList{
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
            this.remove(0)
        }
    }
}
