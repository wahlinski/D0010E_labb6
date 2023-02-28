/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.generalSimulator.View;

import java.util.Observable;

public class ShopView extends View{
    private boolean firstCall = true;

    public void viewPrinter(){
        if (this.firstCall){
            this.firstCall = false;
            System.out.println("PARAMETRAR");
            System.out.println("========== \n" +
                "Antal kassor, N..........: "+ Integer.toString(state.getMaxRegisters()) + "\n" +
                "Max som ryms, M..........: "+ Integer.toString(state.getMaxPeopleInStore()) + "\n" +
                "Ankomshastighet, lambda..: "+ Double.toString(state.lambda()) + "\n" +
                "Plocktider, [P_min..Pmax]: ["+ Double.toString(state.pMin()) + ".." + Double.toString(state.pMax()) + "]" + "\n" +
                "Betaltider, [K_min..Kmax]: ["+ Double.toString(state.kMin()) + ".." + Double.toString(state.kMax()) + "]" + "\n" +
                "Frö, f...................:"+ Double.toString(state.seed()) + "\n\n" +
                "FÖRLOPP\n" +
                "=======\n" +
                "Tid Händelse  Kund  ?  led    ledT    I     $    :-(   köat    köT   köar  [Kassakö..] ");
        }
    }


    public void normalPrinter(Observable o, Object arg){
        State state = ((ShopState) o);
        Event event = ((Event) arg);
        String open = "S";
        if (state.isOpen()){open = "Ö"}

        System.out.printf("%-10.10s  %-15.15s %-10.10s %-10.10s %-10.10s %-10.10s, %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s", 
        Double.toString(arg.getTime()), 
        event, 
        event.getCustomer.getCustomerID(), 
        open, 
        Integer.toString(state.openRegisters()), 
        state.getTimeRegistersNotUsed(), 
        Integer.toString(state.getPeopleInStore()),
        Integer.toString(state.getPeoplePaid()),
        Integer.toString(state.getPeopleMissed()),
        Integer.toString(state.getPeopleHaveQueued()),
        Double.toString(state.getTimeInQueue()),
        Integer.toString(state.getCustomerQueue.size()),
        state.getCustomerQueue.toString());
    }

    @Override
    public void update(Observable o, Object arg) {

        viewPrinter(); //Denna körs bara 1 gång

        normalPrinter(o, arg); // Denna ska köras värje gång state ändras!

    }


}
