/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.main;

import labb6.util.K;

public class Optimize {
    public static void main(String[] args) {
        metod1(10, true);
        metod2();
        metod3();
    }

    public static void metod1(int maxRegisters, boolean withView) {
        RunSim r = new RunSim(
                maxRegisters,
                K.M,
                K.L,
                K.LOW_COLLECTION_TIME,
                K.HIGH_COLLECTION_TIME,
                K.LOW_PAYMENT_TIME,
                K.HIGH_PAYMENT_TIME,
                K.SEED,
                K.END_TIME,
                K.STOP_TIME
        );

        r.startSimulator(withView);
    }

    public static void metod2() {

    }

    public static void metod3() {

    }
}
