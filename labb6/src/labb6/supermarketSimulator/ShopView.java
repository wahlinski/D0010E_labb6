/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator;

import labb6.generalSimulator.Event;
import labb6.generalSimulator.StopEvent;
import labb6.generalSimulator.View;
import labb6.supermarketSimulator.events.CustomerEvent;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class ShopView extends View{
    private boolean firstCall = true;

    private void viewPrinter(ShopState state){
        if (this.firstCall){
            this.firstCall = false;
            System.out.println("PARAMETRAR");
            System.out.println("========== \n" +
                "Antal kassor, N..........: "+ Integer.toString(state.getMaxRegisters()) + "\n" +
                "Max som ryms, M..........: "+ Integer.toString(state.getMaxPeopleInStore()) + "\n" +
                "Ankomshastighet, lambda..: "+ Double.toString(state.lambda()) + "\n" +
                "Plocktider, [P_min..Pmax]: ["+ Double.toString(state.pMin()) + ".." + Double.toString(state.pMax()) + "]" + "\n" +
                "Betaltider, [K_min..Kmax]: ["+ Double.toString(state.kMin()) + ".." + Double.toString(state.kMax()) + "]" + "\n" +
                "Frö, f...................:"+ Long.toString(state.seed()) + "\n\n" +
                "FÖRLOPP\n" +
                "=======");

            System.out.println(String.format("%-10.10s  %-15.15s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10s",
                    "Tid",
                    "Händelse",
                    "Kund",
                    "?",
                    "led",
                    "ledT",
                    "I",
                    "$",
                    ":-(",
                    "köat",
                    "köT",
                    "köar",
                    "[Kassakö..]"
                    ));
        }
    }

    public void infoPrinter(Observable o, Object arg){
        if (arg instanceof StopEvent){
            System.out.printf("%-10.10s  %-15.15s",
                    roundTime(((ShopState) o).getTime()),
                    "Stop");
            System.out.println();

            System.out.println("\n1) Av " + Integer.toString(((ShopState) o).getPeoplePaid()+((ShopState) o).getPeopleMissed()) 
                + " kunder handlade " + ((ShopState) o).getPeoplePaid() + " medan " + ((ShopState) o).getPeopleMissed() + " missades.\n");
            System.out.println("2) Total tid " + Integer.toString(((ShopState) o).getMaxRegisters()) 
                + " kassor varit lediga: " + roundTime(((ShopState) o).getTimeRegistersNotUsed()) + " te");
            System.out.println("Genomsnittlig ledig kassatid: " + roundTime((((ShopState) o).getTimeRegistersNotUsed()/((ShopState) o).getMaxRegisters())) 
                + " te (dvs " + roundTime(((((ShopState) o).getTimeRegistersNotUsed()/((ShopState) o).getMaxRegisters())/ ((ShopState) o).getCustomerLastPayedTime())*100) 
                + "% av tiden från öppning tills sista kunden betalat). \n");
            System.out.println("3) Total tid " + Integer.toString(((ShopState) o).getPeopleHaveQueued()) 
                + " kunder tvingats köa: " + roundTime(((ShopState) o).getTimeInQueue()) 
                + " te. Genomsnittlig kötid: " + roundTime(((ShopState) o).getTimeInQueue()/((ShopState) o).getPeopleHaveQueued()) + " te. \n");

        }
        else{
            ShopState state = ((ShopState) o);
            Event event = ((Event) arg);
            String open = "S";
            if (state.isOpen()){open = "Ö";}

            String id = "";
            if (event instanceof CustomerEvent c) {
                id = c.getCustomer().getCustomerID() + "";
            }



            System.out.printf("%-10.10s  %-15.15s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10s",
                    roundTime(event.getTime()),
                    event,
                    id,
                    open,
                    Integer.toString(state.openRegisters()),
                    roundTime(state.getTimeRegistersNotUsed()),
                    Integer.toString(state.getPeopleInStore()),
                    Integer.toString(state.getPeoplePaid()),
                    Integer.toString(state.getPeopleMissed()),
                    Integer.toString(state.getPeopleHaveQueued()),
                    roundTime(state.getTimeInQueue()),
                    Integer.toString(state.getCustomerQueue().size()),
                    state.getCustomerQueue().toString());
            System.out.println();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        viewPrinter((ShopState) o); //Denna körs bara 1 gång

        //normalPrinter(o, arg); // Denna ska köras värje gång state ändras!

        infoPrinter(o, arg); // Denna körs en gång efter att när StopEvent triggas.

    }

    private static String roundTime(double x) {
        double t = ((double) Math.round(x * 100)) / 100;
        String text = Double.toString(t);
        text = text.replace('.', ',');
        switch (text.split(",")[1].length()) {
            case 1 -> text += "0";
            case 0 -> text += "00";
        }
        return text;
    }


}
