# Merge Sort — Presentation Content and Speaker Notes

**Presenter:** Deem Almanea  
**Project:** Merge Sort vs Quick Sort

## Slide 1 — Merge Sort Overview

**Slide text**
- Comparison-based sorting algorithm
- Uses divide and conquer
- Divides the array into halves
- Recursively sorts each half
- Merges sorted halves into one array

**What to say (Arabic):**  
"أنا بشرح خوارزمية Merge Sort. فكرتها إنها تقسم المصفوفة إلى نصفين، وتكرر التقسيم لين تصير الأجزاء صغيرة، وبعدها تبدأ تدمجها بالترتيب الصحيح. يعني عندنا مرحلتين أساسيتين: Divide وMerge."

## Slide 2 — Example and Pseudocode

**Slide text**
- Input: [38, 27, 43, 3, 9, 82, 10]
- Divide until each part contains one element
- Merge sorted parts
- Output: [3, 9, 10, 27, 38, 43, 82]

```text
MERGE_SORT(A):
    if length(A) < 2: return
    split A into left and right halves
    MERGE_SORT(left)
    MERGE_SORT(right)
    merge both sorted halves into A
```

**What to say (Arabic):**  
"مثلًا هذي المصفوفة قبل الترتيب. نقسمها لأجزاء أصغر، وبعدها نقارن العناصر وقت الدمج. في النهاية ترجع المصفوفة مرتبة تصاعديًا. هذا الـPseudocode يلخص الفكرة، أما الكود الفعلي فموجود في GitHub."

## Slide 3 — Complexity Analysis

**Slide text**
- Best: O(n log n)
- Average: O(n log n)
- Worst: O(n log n)
- Auxiliary space: O(n)
- Stable sorting algorithm

**What to say (Arabic):**  
"ميزة Merge Sort إن التعقيد الزمني عندها ثابت من ناحية الـBig O في أفضل ومتوسط وأسوأ حالة، وهو O(n log n). لكن تحتاج مساحة إضافية O(n) عشان تخزن العناصر مؤقتًا وقت الدمج. وهي Stable، يعني تحافظ على ترتيب العناصر المتساوية."

## Slide 4 — Implementation and Testing

**Slide text**
- Implemented in Java
- Seven correctness test cases: all PASS
- Benchmarked four array sizes
- Tested Random, Sorted, Reverse inputs
- 3 warm-up runs + 7 timed runs per case

**What to say (Arabic):**  
"طبقت الخوارزمية بلغة Java، واختبرتها على سبع حالات، منها مصفوفة فاضية، عناصر مكررة، وأرقام سالبة، وكلها نجحت. بعدها اختبرت الأداء على أربعة أحجام مختلفة وثلاثة أنواع من البيانات، وحسبت متوسط سبع تشغيلات لكل حالة."

## Slide 5 — Benchmark Results and Visualization

**Slide text**
- At n = 1,000,000:
  - Random: 105.741 ms
  - Sorted: 35.644 ms
  - Reverse: 38.535 ms
- Timing increases with input size
- Animated Merge Sort visualizer demonstrates the steps

**What to say (Arabic):**  
"هذي نتائج اختبار الأداء المسجلة عندي. لاحظنا إن الوقت يزيد لما يزيد حجم المصفوفة. وفي تجربتنا، البيانات العشوائية أخذت وقت أطول عند الأحجام الكبيرة. وسويت Visualizer يوضح مراحل التقسيم والدمج بشكل متحرك عشان تكون الفكرة أسهل."

**Demo:** Run `java -cp . MergeSortVisualizer` and show the divide and merge animation.

## Hand-off to the team

These slides cover **Merge Sort only**. The group should add Quick Sort, a fair comparison using matching inputs, a shared chart, and the final conclusion. Use the committed CSV values for any chart or table.

## Suggested evidence screenshots

1. `MergeSort.java` open in VS Code, showing the algorithm.
2. Terminal showing the seven PASS results.
3. Terminal showing benchmark output or the saved CSV table.
4. The Merge Sort visualizer running.

Screenshots are not included in this Markdown file and must be captured from the actual local application.
