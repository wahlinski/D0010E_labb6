/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

public class CustomerIDGenerator {
    private int currentID;
    public CustomerIDGenerator(){
        this.currentID = 0;
    }
    public int getNewID(){
        this.currentID++;
        return this.currentID-1;
    }
    public int getID(){
        return this.currentID-1;
    }
}
