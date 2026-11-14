import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length);
    }
    private static void sort(int[] a, int[] tmp, int lo, int hi) {
        if (hi - lo < 2) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, tmp, lo, mid);
        sort(a, tmp, mid, hi);
        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) tmp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        while (i < mid) tmp[k++] = a[i++];
        while (j < hi) tmp[k++] = a[j++];
        System.arraycopy(tmp, lo, a, lo, hi - lo);
    }
    public static void main(String[] args) {
        int[][] cases = {{38,27,43,3,9,82,10}, {9,7,5,3,1}, {1,2,3,4,5}, {4,2,4,1,2}, {}, {7}, {-3,5,0,-3}};
        for (int[] input : cases) {
            int[] expected = input.clone(); Arrays.sort(expected);
            mergeSort(input);
            System.out.println(Arrays.toString(input) + " | " + (Arrays.equals(input, expected) ? "PASS" : "FAIL"));
            if (!Arrays.equals(input, expected)) throw new AssertionError("Sorting failed");
        }
    }
}