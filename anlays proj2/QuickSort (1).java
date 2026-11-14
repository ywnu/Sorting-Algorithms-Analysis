import java.util.Arrays;
import java.util.Random;
public class QuickSort {

    public static void quickSort(int[] arr, int left, int right) {

        if (left < right) {

            int middle = (left + right) / 2;

            int pivotIndex = medianOfThreeIndex(arr, left, middle, right);

            swap(arr, pivotIndex, right);

            int p = partition(arr, left, right);

            quickSort(arr, left, p - 1);

            quickSort(arr, p + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {

        int pivot = arr[right];

        int i = left - 1;

        for (int j = left; j < right; j++) {

            if (arr[j] <= pivot) {

                i++;

                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);

        return i + 1;
    }

    public static int medianOfThreeIndex(int[] arr, int left, int middle, int right) {

        if ((arr[left] <= arr[middle] && arr[middle] <= arr[right]) ||
            (arr[right] <= arr[middle] && arr[middle] <= arr[left])) {

            return middle;
        }

        else if ((arr[middle] <= arr[left] && arr[left] <= arr[right]) ||
                 (arr[right] <= arr[left] && arr[left] <= arr[middle])) {

            return left;
        }

        else {

            return right;
        }
    }

    public static void swap(int[] arr, int i, int j) {

        int temp = arr[i];

        arr[i] = arr[j];

        arr[j] = temp;
    }

    // ===== ADDED (1): verification method =====
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i - 1] > a[i]) return false;
        return true;
    }

    public static void main(String[] args) {

        // ===== ADDED (2): Part 1 replaced with multiple correctness tests =====
        // Part 1: Test sorting correctness
        int[][] tests = {
            {8, 3, 6, 2, 9, 4, 5, 1, 7},   // random
            {1, 2, 3, 4, 5},               // already sorted
            {5, 4, 3, 2, 1},               // reverse
            {4, 2, 4, 2, 4},               // duplicates
            {7},                           // single element
            {}                             // empty
        };

        System.out.println("Correctness Tests:");
        for (int[] t : tests) {
            int[] copy = t.clone();
            quickSort(copy, 0, copy.length - 1);
            int[] expected = t.clone();
            Arrays.sort(expected);
            System.out.println(Arrays.toString(t) + " -> " + Arrays.toString(copy)
                + (Arrays.equals(copy, expected) ? "  PASS" : "  FAIL"));
        }
        System.out.println();

        // Part 2: Performance Testing
        int[] sizes = {100, 1000, 10000, 100000};

        Random random = new Random();

        System.out.println("Quick Sort Performance:");
        System.out.println("Size\tAverage Time (ms)");

        for (int size : sizes) {

            int[] original = new int[size];

            for (int i = 0; i < size; i++) {
                original[i] = random.nextInt(100000);
            }

            // ===== ADDED (3): stronger warm-up (10 runs instead of 1) =====
            for (int w = 0; w < 10; w++) {
                int[] warmUp = Arrays.copyOf(original, size);
                quickSort(warmUp, 0, warmUp.length - 1);
            }

            long totalTime = 0;

            for (int trial = 0; trial < 5; trial++) {

                int[] testArr = Arrays.copyOf(original, size);

                long startTime = System.nanoTime();

                quickSort(testArr, 0, testArr.length - 1);

                long endTime = System.nanoTime();

                totalTime += (endTime - startTime);

                // ===== ADDED (4a): check the result is really sorted =====
                if (!isSorted(testArr)) System.out.println("ERROR: not sorted!");
            }

            double averageTime = (totalTime / 5.0) / 1_000_000.0;

            // ===== ADDED (4b): clean printing =====
            System.out.printf("%d\t%.4f%n", size, averageTime);
        }
    }
}

   
