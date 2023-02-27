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
        System.out.println("INSERT: currentTime," +
                " eventName," +
                " customerNumber," +
                " om det är öppet eller stängt, " +
                "antal lediga kassor," +
                " summa tid kassor varit lediga, " +
                "antal kunder inne i snabbköpet," +
                " antal kunder som handlat," +
                "antal missade kunder," +
                " antal kunder som köat, " +
                " summa tid kunder stått i kundkön," +
                " kassaköns längd hela kassakön, där det för varje par av kunder gäller att den som står till" +
                "vänster har stått längre i kön än den som står till höger. ");

    }

    @Override
    public void update(Observable o, Object arg) {

        viewPrinter(); //Denna körs bara 1 gång

        normalPrinter(); // Denna ska köras värje gång state ändras!

    }


}
