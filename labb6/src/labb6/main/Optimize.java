package labb6.main;
import labb6.supermarketSimulator.ShopState;
import labb6.util.K;
import java.util.Random;

/**
 * This class is used to run different simulation in order to optimize.
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class Optimize {

    public static void main(String[] args) {
        //metod2(K.SEED);
        metod3(new Random(K.SEED));
    }

    /**
     * Metod 1 shop state.
     *
     * @param maxRegisters the max registers.
     * @param seed         the seed random integer used to randomize the simulation.
     * @return the shop state
     */
    public static ShopState metod1(int maxRegisters, int seed) {
        RunSim r = new RunSim(
                maxRegisters,
                K.M,
                K.L,
                K.LOW_COLLECTION_TIME,
                K.HIGH_COLLECTION_TIME,
                K.LOW_PAYMENT_TIME,
                K.HIGH_PAYMENT_TIME,
                seed,
                K.END_TIME,
                K.STOP_TIME
        );

        return r.startSimulator(false);
    }

    /**
     * Metod 2 int [ ].
     *
     * @param seed the seed
     * @return the int [ ]
     */
    public static int[] metod2(int seed) {
        int minRegs = 1;
        int maxRegs = K.M;

        /* använder långsam sökning
        ShopState state;
        int leastMissed = metod1(maxRegs, seed).getPeopleMissed();
        for(int i = maxRegs - 1; i >= minRegs; i--) {
            state = metod1(i, seed);
            if (state.getPeopleMissed() < leastMissed) {
                optimalRegs = i;
                leastMissed = state.getPeopleMissed();
            }
        }
        */

        int minMissed = metod1(maxRegs, seed).getPeopleMissed();
        int[] res = binarySearch(seed, minRegs, maxRegs, minRegs, minMissed);



        /*System.out.println("Optimala:");
        System.out.println("Kassor: " + res[0]);
        System.out.println("Missade: " + res[1]);*/
        return new int[] {res[0],res[1]};
    }

    private static int[] binarySearch(int seed, int minRegs, int maxRegs, int bestRegs, int bestMissed) {


        int mid = (minRegs + maxRegs) / 2;
        ShopState state = metod1(mid, seed);
        // maxMissed --- state.missed --- minMissed
        // minRegs --- mid --- maxRegs

        int missed = state.getPeopleMissed();
        //System.out.println(minRegs + " | " + mid + " | " + maxRegs + " | " + bestRegs + " | " + missed + " | " + bestMissed);
        if (maxRegs == mid || minRegs == mid) {
            return new int[] { bestRegs, bestMissed };
        }

        if (missed > bestMissed) {
            return binarySearch(seed, mid, maxRegs, bestRegs, bestMissed);
        }
        return binarySearch(seed, minRegs, mid, mid, missed);
    }

    /**
     * Metod 3.
     *
     * @param random the random
     */
    public static void metod3(Random random) {
        int counter = 0;
        int[] first = metod2(random.nextInt());
        while (counter < 100){
            int comperable = metod2(random.nextInt())[0];
            if (first[0] > comperable){
                counter++;
                System.out.println(counter);
            } else {
                first[0] = comperable;
                counter = 0;
            }
        }
        System.out.println("Kassor: " + Integer.toString(first[0]) + " Missade kunder: " + Integer.toString(first[1]));
    }

}
