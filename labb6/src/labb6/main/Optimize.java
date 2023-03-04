package labb6.main;

import labb6.supermarket.ShopState;

import java.util.Random;

/**
 * This class is used to run different simulation in order to optimize.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class Optimize {

    public static void main(String[] args) {

        final boolean metod2 = false;

        if (metod2) {
            int[] results = optimizeSetSeed(K.SEED, true);
            System.out.printf("Minsta antalet kassor som ger minimalt antal missade (%d): %d", results[1], results[0]);

        } else {
            optimizeRandom(new Random(K.SEED));
        }
    }

    /**
     * Metod 1 shop state.
     *
     * @param maxRegisters the max registers.
     * @param seed         the seed random integer used to randomize the simulation.
     * @return the shop state
     */
    public static ShopState simulateOnce(int maxRegisters, int seed) {
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
    public static int[] optimizeSetSeed(int seed) {
        int minRegs = 1;
        int maxRegs = K.M;

        int minMissed = simulateOnce(maxRegs, seed).getPeopleMissed();
        int[] res = binarySearch(seed, minRegs, maxRegs, minRegs, minMissed);

        return new int[]{res[0], res[1]};
    }

    public static int[] optimizeSetSeed(int seed, boolean withPrint) {
        if (withPrint) {
            System.out.println("\n" +
                    "Max som ryms, M..........: " + K.M + "\n" +
                    "Ankomshastighet, lambda..: " + K.L + "\n" +
                    "Plocktider, [P_min..Pmax]: [" + K.LOW_COLLECTION_TIME + ".." + K.HIGH_COLLECTION_TIME + "]" + "\n" +
                    "Betaltider, [K_min..Kmax]: [" + K.LOW_PAYMENT_TIME + ".." + K.HIGH_PAYMENT_TIME + "]" + "\n" +
                    "Frö, f...................: " + seed + "\n"
            );

            System.out.printf("Stängning sker tiden %s och stophändelsen sker tiden %s", K.END_TIME, K.STOP_TIME);
            System.out.println("\n");
        }
        return optimizeSetSeed(seed);
    }

    private static int[] binarySearch(int seed, int minRegs, int maxRegs, int bestRegs, int bestMissed) {
        int mid = (minRegs + maxRegs) / 2;
        ShopState state = simulateOnce(mid, seed);

        int missed = state.getPeopleMissed();
        if (maxRegs == mid || minRegs == mid) {
            return new int[]{bestRegs, bestMissed};
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
    public static void optimizeRandom(Random random) {
        int counter = 0;
        int[] first = optimizeSetSeed(random.nextInt());
        while (counter < 100) {
            int comperable = optimizeSetSeed(random.nextInt())[0];
            if (first[0] > comperable) {
                counter++;
            } else {
                first[0] = comperable;
                counter = 0;
            }
        }
        System.out.println("Kassor: " + Integer.toString(first[0]) + ", Missade kunder: " + Integer.toString(first[1]));
    }

}
