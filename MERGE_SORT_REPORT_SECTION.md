# Report-Ready Section: Merge Sort

**Course:** Design and Analysis of Algorithms  
**Project:** Performance Comparison of Merge Sort and Quick Sort  
**Section contributor:** Deem Almanea

## 1. Merge Sort Algorithm

Merge Sort is a comparison-based sorting algorithm that follows the divide-and-conquer paradigm. It divides an input array into two smaller subarrays, recursively sorts each half, and merges the two sorted halves into one sorted array. The implementation in `MergeSort.java` uses an auxiliary array to store merged values before copying them back into the original array.

For example, the input `[38, 27, 43, 3, 9, 82, 10]` becomes `[3, 9, 10, 27, 38, 43, 82]` after sorting.

## 2. Algorithm Pseudocode

```text
MERGE_SORT(A):
    if A is null or length(A) < 2: return
    temp = new array of length(A)
    SORT(A, temp, 0, length(A))

SORT(A, temp, low, high):
    if high - low < 2: return
    mid = low + (high - low) / 2
    SORT(A, temp, low, mid)
    SORT(A, temp, mid, high)

    i = low; j = mid; k = low
    while i < mid and j < high:
        if A[i] <= A[j]:
            temp[k] = A[i]; i++
        else:
            temp[k] = A[j]; j++
        k++
    copy remaining left-half elements to temp
    copy remaining right-half elements to temp
    copy temp[low:high] to A[low:high]
```

## 3. Complexity Analysis

Merge Sort divides the problem into two subproblems of approximately half the size and performs a linear-time merge at each recursion level. Its recurrence is `T(n) = 2T(n/2) + O(n)`, which yields `O(n log n)` running time in the best, average, and worst cases. The implementation allocates a temporary array of size `n`, requiring `O(n)` auxiliary memory; recursive calls add `O(log n)` stack space, leaving the overall auxiliary space complexity at `O(n)`. The algorithm is stable because the merge operation selects the left element first when values are equal.

| Case | Time | Auxiliary space |
|---|---|---|
| Best | O(n log n) | O(n) |
| Average | O(n log n) | O(n) |
| Worst | O(n log n) | O(n) |

## 4. Implementation and Correctness Testing

The algorithm was implemented in Java and tested against Java's `Arrays.sort` on seven cases: a mixed array, reverse-sorted values, already sorted values, duplicates, an empty array, a single-element array, and negative numbers with duplicates. All seven cases returned **PASS**. The saved evidence is available in `MergeSort_Output.txt`.

## 5. Experimental Methodology

The benchmark uses four input sizes (1,000; 10,000; 100,000; and 1,000,000 integers) and three input patterns (Random, Sorted, and Reverse). For each case, the program performs three warm-up runs and then averages seven timed runs measured with `System.nanoTime()`. Input copying is excluded from the measured sorting time. Random input generation uses a deterministic seed, and sortedness is checked after each timed run.

## 6. Experimental Results

The table below is based on the committed `MergeSort_Results.csv` file. Values are average execution times in milliseconds from one local run.

| Input type | 1,000 | 10,000 | 100,000 | 1,000,000 |
|---|---:|---:|---:|---:|
| Random | 0.066 | 0.824 | 8.884 | 105.741 |
| Sorted | 0.045 | 0.309 | 3.093 | 35.644 |
| Reverse | 0.044 | 0.324 | 3.362 | 38.535 |

The measured execution time generally increased with input size. For larger inputs in this run, random arrays took longer than already sorted and reverse-sorted arrays. This does not contradict the `O(n log n)` theoretical complexity: measured times can vary due to JVM optimization, allocation costs, and system conditions. These results alone do not establish whether Merge Sort is faster than Quick Sort.

## 7. Visualization

A supplementary Java Swing application, `MergeSortVisualizer.java`, demonstrates the divide and merge stages using animated bars. It supports classroom explanation, while the correctness tests and benchmark provide the evidence for algorithm behavior and performance.

## 8. Evidence and Integration

- Source code: `MergeSort.java`
- Correctness output: `MergeSort_Output.txt`
- Benchmark source: `MergeSortBenchmark.java`
- Benchmark data: `MergeSort_Results.csv`
- Visualization: `MergeSortVisualizer.java`
- Repository branch: https://github.com/ywnu/Sorting-Algorithms-Analysis/tree/feature/merge-sort

**To complete the group report:** add the Quick Sort implementation and its matching experimental results, a comparison chart based on both algorithms, a joint discussion, and screenshots of code/output/visualization. Do not present Merge Sort-only results as a head-to-head comparison.
