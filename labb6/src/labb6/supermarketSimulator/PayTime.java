/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.util.ExponentialRandomStream;
import labb6.util.UniformRandomStream;

public class PayTime {
    private final double kMin;
    private final double kMax;
    private final UniformRandomStream rand;

    public PayTime(double kMin, double kMax, long seed) {
        this.kMin = kMin;
        this.kMax = kMax;
        rand = new UniformRandomStream(kMin, kMax, seed);
    }

    /**
     * räknar ut när kunden har betalat klart
     * @param currentTime den nuvarande tiden i state
     * @return absoluta tiden som händelsen kommer att hända
     */
    public double calculate(int currentTime) {
        return currentTime + rand.next();
    }

    public double getKMax() {
        return kMax;
    }

    public double getKMin() {
        return kMin;
    }
}
