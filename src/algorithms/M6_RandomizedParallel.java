
package algorithms;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class M6_RandomizedParallel {

    private static final Random RANDOM = new Random();

    // 1. Randomized QuickSort
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        int pivotIndex = low + RANDOM.nextInt(high - low + 1);
        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[high];
        arr[high] = temp;

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        int p = i + 1;
        randomizedQuickSort(arr, low, p - 1);
        randomizedQuickSort(arr, p + 1, high);
    }

    // 2. Reservoir Sampling: choose k records from a stream.
    public static List<Integer> reservoirSample(
            Iterable<Integer> stream, int k) {

        if (k < 0) throw new IllegalArgumentException("k cannot be negative");

        List<Integer> reservoir = new ArrayList<>();
        Random random = new Random();
        int count = 0;

        for (int item : stream) {
            count++;

            if (reservoir.size() < k) {
                reservoir.add(item);
            } else if (k > 0) {
                int index = random.nextInt(count);
                if (index < k) reservoir.set(index, item);
            }
        }
        return reservoir;
    }

    // 3. Miller-Rabin primality test.
    // BigInteger arithmetic avoids multiplication overflow.
    public static boolean isProbablyPrime(long number) {
        if (number < 2) return false;
        if (number == 2 || number == 3) return true;
        if (number % 2 == 0) return false;

        BigInteger n = BigInteger.valueOf(number);
        BigInteger nMinusOne = n.subtract(BigInteger.ONE);
        BigInteger d = nMinusOne;
        int s = 0;

        while (!d.testBit(0)) {
            d = d.shiftRight(1);
            s++;
        }

        long[] bases = {2, 3, 5, 7, 11, 13, 17};

        for (long base : bases) {
            if (base >= number) continue;

            BigInteger a = BigInteger.valueOf(base);
            BigInteger x = a.modPow(d, n);

            if (x.equals(BigInteger.ONE) || x.equals(nMinusOne)) {
                continue;
            }

            boolean passed = false;
            for (int r = 1; r < s; r++) {
                x = x.multiply(x).mod(n);

                if (x.equals(nMinusOne)) {
                    passed = true;
                    break;
                }
            }

            if (!passed) return false;
        }

        // These bases are not sufficient for every possible long value.
        return true;
    }

    // 4. Blelloch-style use case: inclusive prefix scan.
    // This straightforward implementation is sequential.
    public static int[] prefixScan(int[] arr) {
        int[] result = new int[arr.length];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            result[i] = sum;
        }
        return result;
    }

    // 5. Parallel Reduce: sum document usage counts.
    public static int parallelReduce(int[] usageCounts) {
        return Arrays.stream(usageCounts)
                     .parallel()
                     .sum();
    }

    public static void main(String[] args) {
        int[] scores = {50, 20, 80, 10, 40};

        randomizedQuickSort(scores, 0, scores.length - 1);
        System.out.println("Sorted scores: "
            + Arrays.toString(scores));

        List<Integer> usageStream =
            Arrays.asList(12, 18, 25, 31, 42, 55, 61);

        System.out.println("Sampled usage records: "
            + reservoirSample(usageStream, 3));

        System.out.println("Is 101 prime? "
            + isProbablyPrime(101));

        int[] usage = {3, 5, 2, 7};
        System.out.println("Prefix scan: "
            + Arrays.toString(prefixScan(usage)));

        System.out.println("Total usage: "
            + parallelReduce(usage));
    }
}
