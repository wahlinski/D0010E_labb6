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

    public void viewPrinter(){
        System.out.println("PARAMETRAR");
        System.out.println("========== \n" +
                "Antal kassor, N..........: "+"INSERT KASSA"+ "\n" +
                "Max som ryms, M..........: "+"INSERT MAXANT"+ "\n" +
                "Ankomshastighet, lambda..: "+"INSERT ANKOMSTtid"+ "\n" +
                "Ankomshastighet, lambda..: "+"INSERT ankomtshast"+  "\n" +
                "Betaltider, [K_min..Kmax]: "+"INSERT [K_min..Kmax]"+ "\n" +
                "Frö, f...................:"+"INSERT FRÖ"+ "\n" +
                "FÖRLOPP\n" +
                "=======\n" +
                "Tid Händelse  Kund  ?  led    ledT    I     $    :-(   köat    köT   köar  [Kassakö..] ");

    }

    public void normalPrinter(){


    }

    @Override
    public void update(Observable o, Object arg) {

        viewPrinter(); //Denna körs bara 1 gång

        normalPrinter(); // Denna ska köras värje gång state ändras!

    }


}
