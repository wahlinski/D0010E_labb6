package labb6.supermarketSimulator;
import labb6.util.UniformRandomStream;
/**
 * This class is used for calculating the PayTime for the customers in the simulation.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class PayTime {
    private final double kMin;
    private final double kMax;
    private final UniformRandomStream rand;

    /**
     * Instantiates a new Pay time.
     *
     * @param kMin the k min specifies minimum PayTime.
     * @param kMax the k max specifies maximum PayTime.
     * @param seed the seed is a random integer used to randomize the PayTime.
     */
    public PayTime(double kMin, double kMax, long seed) {
        this.kMin = kMin;
        this.kMax = kMax;
        rand = new UniformRandomStream(kMin, kMax, seed);
    }

    /**
     * Calculate when a customer has finished paying.
     *
     * @param currentTime The current time in the state.
     * @return The absolute time the event occurs.
     */
    public double calculate(double currentTime) {
        return currentTime + rand.next();
    }

    /**
     * Returns maximum payTime for the customer.
     *
     * @return the k max as double.
     */
    public double getKMax() {
        return kMax;
    }

    /**
     * Returns minimum payTime for the customer.
     *
     * @return the k min as a double.
     */
    public double getKMin() {
        return kMin;
    }
}
