مشروع ديم: Merge Sort

1) افتحي مجلد Deem_MergeSort_Ready داخل VS Code.
2) لتشغيل الأنيميشن (حركة فعلية للأعمدة أثناء الدمج):
   javac MergeSortVisualizer.java
   java -cp . MergeSortVisualizer
   اضغطي Play، واستخدمي Speed للتحكم بالسرعة، وShuffle لتغيير ترتيب الأرقام.
3) لاختبار صحة Merge Sort:
   javac MergeSort.java
   java -cp . MergeSort
   يجب أن تظهر PASS لكل الحالات.
4) لتجربة أعداد كبيرة وقياس الوقت:
   javac MergeSort.java MergeSortBenchmark.java
   java -cp . MergeSortBenchmark
   النتائج بالمللي ثانية، تختلف من جهاز لآخر. أرسليها لحنان للمقارنة مع Quick Sort.

مهم: الأنيميشن للأرقام القليلة فقط، أما قياس الأداء للأرقام الكبيرة فبدون رسوم أو حركة.
Time: Best/Average/Worst O(n log n). Auxiliary space O(n).
ملاحظة: لا يُستخدم زمن واجهة الأنيميشن للمقارنة العلمية.
