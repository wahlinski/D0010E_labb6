package labb6.supermarketSimulator;
/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
import java.util.ArrayList;


public class CustomerQueue extends ArrayList<Integer> {
    private ShopState state;
    public CustomerQueue(State state){
        super();
        this.state = state;
    }
    public void addCustomer(int customer){
        this.add(customer);
        state.addPeopleHaveQueued();
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
