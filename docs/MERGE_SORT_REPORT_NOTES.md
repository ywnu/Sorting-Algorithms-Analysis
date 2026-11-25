# Merge Sort — Implementation and Experimental Results

**Course:** Design and Analysis of Algorithms  
**Team project:** Merge Sort vs. Quick Sort  
**Contributor for this section:** Deem Almanea  
**Branch:** `feature/merge-sort`

## Problem and approach
Given an array of integers, sort it in ascending order. Merge Sort uses divide-and-conquer: recursively split the array into halves, then merge the sorted halves. The Java implementation sorts in place from the caller's perspective, while using a temporary buffer of length `n` internally.

## Pseudocode
```text
MERGE-SORT(A):
    if length(A) < 2: return
    temp = new array of length(A)
    SORT(A, temp, 0, length(A))

SORT(A, temp, lo, hi):          // interval [lo, hi)
    if hi - lo < 2: return
    mid = lo + (hi - lo) / 2
    SORT(A, temp, lo, mid)
    SORT(A, temp, mid, hi)
    i = lo; j = mid; k = lo
    while i < mid and j < hi:
        if A[i] <= A[j]: temp[k++] = A[i++]
        else: temp[k++] = A[j++]
    copy any remaining elements from the left half to temp
    copy any remaining elements from the right half to temp
    copy temp[lo:hi] back to A[lo:hi]
```

## Theoretical analysis

| Case | Time complexity |
|---|---|
| Best | O(n log n) |
| Average | O(n log n) |
| Worst | O(n log n) |

The recurrence is `T(n) = 2T(n/2) + O(n)`, giving `O(n log n)` time. Auxiliary space is `O(n)` for the temporary array, plus `O(log n)` recursive call-stack space, so total auxiliary space is `O(n)`. The merge uses `<=`, so equal elements retain their relative order (stable sort).

## Experiment setup
Source: `MergeSortBenchmark.java`. Input sizes: 1,000; 10,000; 100,000; 1,000,000. Input patterns: Random, Sorted, Reverse. For each case, the program runs 3 warm-up sorts and averages 7 timed sorts. Only the sorting call is timed; cloning the input is outside the timer. Sortedness is checked after each run. Random inputs use a fixed seed derived from the size.

### Observed results (milliseconds)
These values are transcribed from the committed `MergeSort_Results.csv` (a separate local benchmark run) and are not universal performance guarantees.

| Input type | 1,000 | 10,000 | 100,000 | 1,000,000 |
|---|---:|---:|---:|---:|
| Random | 0.066 | 0.824 | 8.884 | 105.741 |
| Sorted | 0.045 | 0.309 | 3.093 | 35.644 |
| Reverse | 0.044 | 0.324 | 3.362 | 38.535 |

**Interpretation:** Running time increases with input size. The recorded random inputs took longer than sorted/reverse inputs at larger sizes. These are empirical observations from one environment, not a change in Merge Sort's asymptotic complexity. JVM JIT compilation, system load, memory allocation, and timing noise can affect measurements. A fair comparison with Quick Sort must run both algorithms on identical input copies under the same environment and benchmarking methodology.

## Files and execution

- `MergeSort.java`: algorithm and correctness checks, including empty, duplicate, negative, sorted and reverse-sorted cases
- `MergeSortBenchmark.java`: timing experiment and sortedness verification
- `MergeSortVisualizer.java`: supplementary animated demonstration

Run from the repository directory:

```powershell
javac MergeSort.java MergeSortBenchmark.java MergeSortVisualizer.java
java -cp . MergeSort
java -cp . MergeSortBenchmark
java -cp . MergeSortVisualizer
```

## For the final report
Use the algorithm and pseudocode above, cite the measured results as a local experiment, include screenshots of the Java implementation and terminal output, and add a chart comparing both algorithms after the team completes the Quick Sort benchmark. The visualizer is a demonstration aid, not a substitute for algorithm analysis.
