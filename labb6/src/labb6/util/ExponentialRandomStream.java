package labb6.util;
import java.util.Random;

/**
 * This class is used to randomize the ShopSimulator based on seed and lambda.
 */
public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;

	/**
	 * Instantiates a new Exponential random stream.
	 *
	 * @param lambda the lambda
	 * @param seed   the seed
	 */
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}

	/**
	 * Instantiates a new Exponential random stream.
	 *
	 * @param lambda the lambda
	 */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}

	/**
	 * Returns the next randomized integer of the type double.
	 *
	 * @return the double
	 */
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
}

