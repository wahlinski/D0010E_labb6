/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.util.ExponentialRandomStream;

public class ArrivalTime {

    private final double lambda;
    private final ExponentialRandomStream rand;

    public ArrivalTime(double lambda, long seed) {
        this.lambda = lambda;
        rand = new ExponentialRandomStream(lambda, seed);
    }

    /**
     * räknar ut när nästa kund kommer till butiken
     * @param currentTime den nuvarande tiden i state
     * @return absoluta tiden som händelsen kommer att hända
     */
    public double calculate(int currentTime) {
        return currentTime + rand.next();
    }

    public double getLambda() {
        return lambda;
    }

}
