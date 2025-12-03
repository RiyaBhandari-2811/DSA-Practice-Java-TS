/**
 * Brute force linear scan to find the peak index in a mountain array.
 *
 * Idea:
 * - Scan entire array and track the index of the maximum element.
 * - Works for any array, but does NOT validate mountain property.
 *
 * Edge cases:
 * - Null/empty/length < 3 → throw error.
 * - All equal → returns first max index.
 * - Duplicate maximum → returns first occurrence.
 * - Not strictly mountain → still returns index of maximum.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
export function findPeakBrute(arr: number[]): number {
    if (!arr || arr.length < 3) {
        throw new Error("Array must have length >= 3");
    }

    let peakIndex = 0;

    for (let i = 1; i < arr.length; i++) {
        if (arr[i] > arr[peakIndex]) {
            peakIndex = i;
        }
    }

    return peakIndex;
}

/**
 * Better linear approach using mountain behaviour.
 *
 * Idea:
 * - Move while arr[i] < arr[i+1].
 * - The first point arr[i] > arr[i+1] is the peak.
 * - Finishes earlier than full scan.
 *
 * Edge cases:
 * - Null/length < 3 → throw error.
 * - Not strictly mountain → returns local max index.
 * - Duplicate at peak → stops early and returns index before equality breaks increasing pattern.
 *
 * Time Complexity: O(k), worst O(n)
 * Space Complexity: O(1)
 */
export function findPeakBetter(arr: number[]): number {
    if (!arr || arr.length < 3) {
        throw new Error("Array must have length >= 3");
    }

    let i = 0;

    while (i + 1 < arr.length && arr[i] < arr[i + 1]) {
        i++;
    }

    return i;
}

/**
 * Optimised binary search approach to find the peak index.
 *
 * Idea:
 * - If arr[mid] < arr[mid + 1] → peak lies to the right → left = mid + 1.
 * - Else → peak is mid or left of mid → right = mid.
 * - Converges to the peak index.
 *
 * Assumptions:
 * - Valid mountain array (strict increase then decrease).
 *
 * Edge cases:
 * - Null/length < 3 → throw error.
 * - Not a strict mountain → gives local maximum.
 * - Duplicates near peak → binary behaviour may pick any local max.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
export function findPeakOptimised(arr: number[]): number {
    if (!arr || arr.length < 3) {
        throw new Error("Array must have length >= 3");
    }

    let left = 0;
    let right = arr.length - 1;

    while (left < right) {
        const mid = Math.floor(left + (right - left) / 2);

        if (arr[mid] < arr[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}

/** Test cases */
const tests = [
    { name: "mountain1", arr: [0, 2, 1, 0] },
    { name: "mountain2", arr: [0, 1, 2, 3, 2, 1] },
    { name: "mountain3", arr: [3, 5, 3, 2, 0] },
    { name: "mountain4", arr: [1, 2, 3, 4, 5, 3, 1] },
    { name: "mountain5", arr: [1, 3, 3, 2] },
    { name: "mountain6", arr: [1, 2, 1] }
];

for (const t of tests) {
    console.log(`Testing ${t.name}`);
    console.log("Brute:     ", findPeakBrute(t.arr));
    console.log("Better:    ", findPeakBetter(t.arr));
    console.log("Optimised: ", findPeakOptimised(t.arr));
    console.log("");
}
