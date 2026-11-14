# Sorting Algorithms Analysis — Merge Sort vs Quick Sort

Java project comparing Merge Sort and Quick Sort with correctness tests, benchmarks, graphs and supporting reports.

## Project structure

- `MergeSort.java`: Deem's standalone Merge Sort implementation and correctness tests.
- `MergeSortBenchmark.java`: Deem's standalone Merge Sort benchmark.
- `MergeSortVisualizer.java`: Deem's Swing visualization.
- `MergeSort_Results.csv`, `MergeSort_Output.txt`: Deem's saved benchmark and test evidence.
- `MERGE_SORT_REPORT_SECTION.md`, `MERGE_SORT_REPORT_NOTES.md`, `MERGE_SORT_PRESENTATION.md`: Merge Sort report and presentation drafts.
- `benchmark/MergeSort.java`, `benchmark/QuickSort.java`, `benchmark/SortingBenchmark.java`: Hanan's integrated comparison sources (Quick Sort code matches Aseel's implementation).
- `benchmark/results/`: comparison CSV, plotting script and graphs.
- `benchmark/screenshots/`: recorded execution screenshots.
- `anlays proj2/`: Aseel's original Quick Sort source, report PDF, pseudocode image and text report (kept unchanged for attribution).
- `analys proj.zip`: Aseel's original archive, retained as submitted.

## Run the programs

From the repository root with Java JDK installed:

```bash
javac MergeSort.java MergeSortBenchmark.java MergeSortVisualizer.java
java MergeSort
java MergeSortBenchmark
java MergeSortVisualizer
```

For the **integrated comparison**, compile only the files inside `benchmark/` (the root contains another `MergeSort` class with the same name):

```bash
javac benchmark/MergeSort.java benchmark/QuickSort.java benchmark/SortingBenchmark.java
java -cp benchmark SortingBenchmark
```

The integrated benchmark writes `benchmark/results/results.csv` when run from the repository root. Existing plots and screenshots are saved experimental artifacts. Re-running the benchmark may produce different timings.

To run Aseel's standalone Quick Sort source, copy or rename `anlays proj2/QuickSort (1).java` to `QuickSort.java` in a separate directory before compiling, because its public Java class is named `QuickSort`.

## Experiment design

The integrated benchmark tests sizes 1,000 / 10,000 / 100,000 on random, sorted and reverse-sorted arrays, using two warm-up runs and five measured runs for each algorithm. Both algorithms sort clones of the same input; results are checked against Java's `Arrays.sort`. Quick Sort uses median-of-three pivot selection and Lomuto partitioning.

Deem's standalone benchmark uses a different set of sizes and repetitions. **Do not directly compare times from the standalone and integrated benchmarks as if they came from one controlled experiment.**

## Remaining submission steps

- Recompile and run all programs on the final submission machine.
- Correct the written comparison conclusion: Merge Sort is faster for random size 1,000 and reverse size 10,000 in the recorded comparison; Quick Sort wins the other seven cases.
- Assemble and proofread the final combined report and presentation.
- Verify against the instructor's official rubric before submitting.

## Team branches

- `feature/merge-sort` — Deem
- `feature/quick-sort` — Aseel
- `feature/benchmark-comparison` — Hanan

Original feature branches are preserved.
