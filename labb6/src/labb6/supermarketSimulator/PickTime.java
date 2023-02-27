/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.util.UniformRandomStream;

public class PickTime {
    private final double pMin;
    private final double pMax;
    private final UniformRandomStream rand;

    public PickTime(double pMin, double pMax, long seed) {
        this.pMin = pMin;
        this.pMax = pMax;
        rand = new UniformRandomStream(pMin, pMax, seed);
    }

    /**
     * räknar ut när kunden har plockat klart sina varor
     * @param currentTime den nuvarande tiden i state
     * @return absoluta tiden som händelsen kommer att hända
     */
    public double calculate(int currentTime) {
        return currentTime + rand.next();
    }

    public double getPMax() {
        return pMax;
    }

    public double getPMin() {
        return pMin;
    }
}
