
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SortingBenchmark {

    static final int[] SIZES = {1000, 10000, 100000};
    static final int REPETITIONS = 5;

    interface Sorter {
        void sort(int[] array);
    }

    public static void main(String[] args) throws IOException {

        File resultsFolder = new File("../results");
        resultsFolder.mkdirs();

        File resultsFile = new File(resultsFolder, "results.csv");

        try (FileWriter writer = new FileWriter(resultsFile)) {

            writer.write("Size,Dataset,Algorithm,Average Time (ms),Status\n");

            // Actual sorting algorithm implementations
            Sorter mergeSort = array -> MergeSort.mergeSort(array);

            Sorter quickSort = array ->
                QuickSort.quickSort(array, 0, array.length - 1);

            for (int size : SIZES) {

                System.out.println("\nDataset size: " + size);

                int[] random = generateRandom(size);

                int[] sorted = random.clone();
                Arrays.sort(sorted);

                int[] reversed = sorted.clone();
                reverse(reversed);

                testDataset(writer, size, "Random",
                    random, mergeSort, quickSort);

                testDataset(writer, size, "Sorted",
                    sorted, mergeSort, quickSort);

                testDataset(writer, size, "Reverse",
                    reversed, mergeSort, quickSort);
            }
        }

        System.out.println("\nExperiments completed!");
        System.out.println("Results saved successfully!");
    }

    static int[] generateRandom(int size) {

        int[] array = new int[size];
        Random random = new Random(42 + size);

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000);
        }

        return array;
    }

    static void reverse(int[] array) {

        for (int i = 0; i < array.length / 2; i++) {

            int temp = array[i];

            array[i] = array[array.length - 1 - i];

            array[array.length - 1 - i] = temp;
        }
    }

    static double measureTime(Runnable task) {

        long start = System.nanoTime();

        task.run();

        long end = System.nanoTime();

        return (end - start) / 1_000_000.0;
    }

    static double calculateAverage(double[] times) {

        double sum = 0;

        for (double time : times) {
            sum += time;
        }

        return sum / times.length;
    }

    static void runAndVerify(int[] data, int[] expected, Sorter sorter) {

        sorter.sort(data);

        if (!Arrays.equals(data, expected)) {
            throw new IllegalStateException(
                "Sorting algorithm produced incorrect results!"
            );
        }
    }

    static double runExperiments(int[] original, Sorter sorter) {

        // Create the expected sorted result
        int[] expected = original.clone();
        Arrays.sort(expected);

        // Warm-up: 2 runs
        for (int i = 0; i < 2; i++) {

            int[] warmupData = original.clone();

            runAndVerify(warmupData, expected, sorter);
        }

        // Measure execution time
        double[] times = new double[REPETITIONS];

        for (int i = 0; i < REPETITIONS; i++) {

            int[] data = original.clone();

            times[i] = measureTime(
                () -> sorter.sort(data)
            );

            // Verify the complete sorting result
            if (!Arrays.equals(data, expected)) {

                throw new IllegalStateException(
                    "Sorting algorithm produced incorrect results!"
                );
            }
        }

        return calculateAverage(times);
    }

    static void testDataset(
        FileWriter writer,
        int size,
        String datasetName,
        int[] data,
        Sorter mergeSort,
        Sorter quickSort
    ) throws IOException {

        System.out.println("\nTesting " + datasetName);

        double mergeTime = runExperiments(data, mergeSort);

        double quickTime = runExperiments(data, quickSort);

        saveResult(writer, size, datasetName,
            "Merge Sort", mergeTime);

        saveResult(writer, size, datasetName,
            "Quick Sort", quickTime);

        System.out.printf("Merge Sort: %.4f ms%n", mergeTime);

        System.out.printf("Quick Sort: %.4f ms%n", quickTime);

        System.out.println(
            "Both results verified successfully."
        );
    }

    static void saveResult(
        FileWriter writer,
        int size,
        String dataset,
        String algorithm,
        double averageTime
    ) throws IOException {

        writer.write(
            size + "," +
            dataset + "," +
            algorithm + "," +
            averageTime + "," +
            "REAL" + "\n"
        );
    }
}
