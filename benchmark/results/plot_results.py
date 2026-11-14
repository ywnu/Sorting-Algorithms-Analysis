
import csv
import os
import matplotlib.pyplot as plt

# Paths
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
CSV_FILE = os.path.join(BASE_DIR, "results.csv")

# Read results
results = []

with open(CSV_FILE, "r", newline="") as file:
    reader = csv.DictReader(file)

    for row in reader:
        results.append(row)

# Dataset sizes
sizes = [1000, 10000, 100000]
datasets = ["Random", "Sorted", "Reverse"]

# Create one chart for each dataset size
for size in sizes:

    merge_times = []
    quick_times = []

    for dataset in datasets:

        merge_time = next(
            float(row["Average Time (ms)"])
            for row in results
            if int(row["Size"]) == size
            and row["Dataset"] == dataset
            and row["Algorithm"] == "Merge Sort"
        )

        quick_time = next(
            float(row["Average Time (ms)"])
            for row in results
            if int(row["Size"]) == size
            and row["Dataset"] == dataset
            and row["Algorithm"] == "Quick Sort"
        )

        merge_times.append(merge_time)
        quick_times.append(quick_time)

    x = range(len(datasets))
    width = 0.35

    plt.figure(figsize=(9, 5))

    plt.bar(
        [i - width / 2 for i in x],
        merge_times,
        width,
        label="Merge Sort"
    )

    plt.bar(
        [i + width / 2 for i in x],
        quick_times,
        width,
        label="Quick Sort"
    )

    plt.title(f"Merge Sort vs Quick Sort - {size} Elements")
    plt.xlabel("Dataset Type")
    plt.ylabel("Average Execution Time (ms)")
    plt.xticks(list(x), datasets)
    plt.legend()
    plt.grid(axis="y", alpha=0.3)

    plt.tight_layout()

    output_file = os.path.join(
        BASE_DIR,
        f"sorting_comparison_{size}.png"
    )

    plt.savefig(output_file, dpi=300)
    plt.close()

    print(f"Chart saved: {output_file}")

print("\nAll charts created successfully!")
