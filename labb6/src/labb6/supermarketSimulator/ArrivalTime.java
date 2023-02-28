/*
Wåhlin Filip
Abdi Abdi Mohamed
Härdelin Viggo
Melander Samuel
 */
package labb6.supermarketSimulator;

import labb6.util.ExponentialRandomStream;

/**
 * This class calculates Arrival time for the customers in the store.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class ArrivalTime {

    private final double lambda;
    private final ExponentialRandomStream rand;

    /**
     * Instantiates a new Arrival time.
     *
     * @param lambda the lambda
     * @param seed   the seed is a random integer used to randomize the simulation.
     */
    public ArrivalTime(double lambda, long seed) {
        this.lambda = lambda;
        rand = new ExponentialRandomStream(lambda, seed);
    }

    /**
     * Calculates the time customer enters the store.
     *
     * @param currentTime currentTime in State
     * @return Returns the absolute time for the event to happen.
     * @throws Error "xxx"
     */
    public double calculate(int currentTime) {
        return currentTime + rand.next();
    }

    /**
     * Returns lambda
     *
     * @return the lambda
     */
    public double getLambda() {
        return lambda;
    }

}
