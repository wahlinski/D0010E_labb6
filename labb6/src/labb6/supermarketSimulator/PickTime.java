/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.supermarketSimulator;
import labb6.util.UniformRandomStream;

/**
 * The type Pick time.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
public class PickTime {
    private final double pMin;
    private final double pMax;
    private final UniformRandomStream rand;

    /**
     * Instantiates a new Pick time.
     *
     * @param pMin the p Min specifies minimum pickTime.
     * @param pMax the p max specifies the maximum pickTime
     * @param seed the seed is a random integer used to randomize the simulation.
     * @throws IllegalArgumentException if
     */
    public PickTime(double pMin, double pMax, long seed) {
        this.pMin = pMin;
        this.pMax = pMax;
        rand = new UniformRandomStream(pMin, pMax, seed);
    }

    /**
     * Calculates when the customer has finished picking up their goods.
     *
     * @param currentTime The current time in the state.
     * @return The absolute time the event occurs.
     */
    public double calculate(double currentTime) {
        return currentTime + rand.next();
    }

    /**
     * Gets p max.
     *
     * @return the p max
     */
    public double getPMax() {
        return pMax;
    }

    /**
     * Gets p min.
     *
     * @return the p min
     */
    public double getPMin() {
        return pMin;
    }
}
