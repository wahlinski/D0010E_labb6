package labb6.main;

import labb6.supermarket.ShopState;

import java.util.Random;

/**
 * This class is used to run different simulation in order to optimize amount of registers.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public class Optimize {

    public static void main(String[] args) {
        final boolean metod2 = false;
        if (metod2) {
            int[] results = optimizeSetSeedWithPrint(K.SEED);
            System.out.printf("Minsta antalet kassor som ger minimalt antal missade (%d): %d", results[1], results[0]);

        } else {
            optimizeRandom(new Random(K.SEED));
        }
    }

    /**
     * Simulates the supermarket simulation once with the parameters in {@link K}
     *
     * @param maxRegisters the max amount of registers.
     * @param seed         integer used in {@link Random} for random values in the simulation.
     * @return the {@link ShopState} after the simulation is over.
     */
    public static ShopState simulateOnce(int maxRegisters, int seed) {
        RunSim run = new RunSim(
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


        return run.startSimulator(false);
    }

    /**
     * Optimizes the lowest amount of registers needed
     * to miss the least amount customers.
     *
     * @param seed seed used in {@link Random}
     * @return an array of integers with length 2: {@code [lowestAmountOfRegisters, leastMissedPeople]}
     */
    public static int[] optimizeSetSeed(int seed) {
        int minRegs = 1;
        int maxRegs = K.M;

        int minMissed = simulateOnce(maxRegs, seed).getPeopleMissed();
        int[] res = binarySearch(seed, minRegs, maxRegs, minRegs, minMissed);

        return new int[]{res[0], res[1]};
    }

    /**
     * Prints data regarding the simulation and calls {@link #optimizeSetSeed}.
     * @param seed seed used in {@link Random}
     * @return an array of integers with length 2: {@code [lowestAmountOfRegisters, leastMissedPeople]}
     */
    public static int[] optimizeSetSeedWithPrint(int seed) {
        System.out.println("\n" +
                "Max som ryms, M..........: " + K.M + "\n" +
                "Ankomshastighet, lambda..: " + K.L + "\n" +
                "Plocktider, [P_min..Pmax]: [" + K.LOW_COLLECTION_TIME + ".." + K.HIGH_COLLECTION_TIME + "]" + "\n" +
                "Betaltider, [K_min..Kmax]: [" + K.LOW_PAYMENT_TIME + ".." + K.HIGH_PAYMENT_TIME + "]" + "\n" +
                "Frö, f...................: " + seed + "\n"
        );

        System.out.printf("Stängning sker tiden %s och stophändelsen sker tiden %s", K.END_TIME, K.STOP_TIME);
        System.out.println("\n");
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
     * Optimizes the lowest amount of registers needed
     * to miss the least amount customers without using
     * set seed.
     *
     * @param random the random to be used
     */
    public static void optimizeRandom(Random random) {
        int counter = 0;
        int[] first = optimizeSetSeed(random.nextInt());
        while (counter < 100) {
            int[] comperable = optimizeSetSeed(random.nextInt());
            if (first[0] > comperable[0]) {
                counter++;
            } else {
                first[0] = comperable[0];
                first[1] = comperable[1];
                counter = 0;
            }
        }
        System.out.println("Kassor: " + Integer.toString(first[0]) + ", Missade kunder: " + Integer.toString(first[1]));
    }

}
