import java.util.Arrays;
import java.util.Random;

public class MergeSortBenchmark {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] kinds = {"Random", "Sorted", "Reverse"};
        System.out.println("Type,Size,Average_ms");
        for (int n : sizes) for (String kind : kinds) {
            int[] base = new int[n];
            if (kind.equals("Random")) {
                Random random = new Random(2026 + n);
                for (int i=0; i<n; i++) base[i] = random.nextInt();
            } else for (int i=0; i<n; i++) base[i] = kind.equals("Sorted") ? i : n-i;
            for (int w=0; w<3; w++) MergeSort.mergeSort(base.clone());
            long total = 0;
            for (int rep=0; rep<7; rep++) {
                int[] data=base.clone();
                long start=System.nanoTime();
                MergeSort.mergeSort(data);
                total+=System.nanoTime()-start;
                if (!isSorted(data)) throw new AssertionError("Incorrect sorting");
            }
            System.out.printf(java.util.Locale.US, "%s,%d,%.3f%n",kind,n,total/7.0/1_000_000.0);
        }
    }
    static boolean isSorted(int[] a) { for(int i=1;i<a.length;i++) if(a[i-1]>a[i]) return false; return true; }
}
