package labb6.supermarket;

import labb6.util.ExponentialRandomStream;

/**
 * This class calculates Arrival time for the customers in the store. Customers are considered to arrive randomly and independently
 * of each other.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class ArrivalTime {

    private final double lambda;
    private final ExponentialRandomStream rand;
    private final long seed;

    /**
     * Instantiates a new Arrival time.
     *
     * @param lambda the lambda parameter indicates the average number of customers arriving per unit of time.
     * @param seed   the seed is a random integer used to randomize the simulation.
     */
    public ArrivalTime(double lambda, long seed) {
        this.lambda = lambda;
        this.seed = seed;
        rand = new ExponentialRandomStream(lambda, seed);
    }

    /**
     * Calculates the time customer enters the store.
     *
     * @param currentTime currentTime in State
     * @return Returns the absolute time for the event to happen.
     * @throws Error "xxx"
     */
    public double calculate(double currentTime) {
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

    /**
     * Returns Seed
     *
     * @return the Seed
     */

    public long getSeed() {
        return seed;
    }
}
