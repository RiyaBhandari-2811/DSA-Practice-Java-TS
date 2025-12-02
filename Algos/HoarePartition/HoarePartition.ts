/**
 * Hoare Partition 
 *
 * Definition:
 * Hoare Partition is a partitioning algorithm used in QuickSort that:
 * - usually selects the first element of the subarray as the pivot
 * - uses two pointers:
 *      one starting near the left moving right,
 *      one starting near the right moving left
 * - swaps elements that are on the wrong side of the pivot
 * - stops when the pointers cross and returns a partition index
 *
 * After partition:
 * - For ascending:
 *      all elements in arr[left..p] are <= all elements in arr[p+1..right]
 * - For descending:
 *      all elements in arr[left..p] are >= all elements in arr[p+1..right]
 * - The pivot is not guaranteed to end up at index p.
 *
 * Time Complexity: O(n)
 * ---------------------
 * Two pointers move toward each other over the subarray [left..right].
 * Each element is inspected a constant number of times and may be swapped once.
 * Total work grows linearly with the subarray size.
 *
 * Space Complexity: O(1)
 * ----------------------
 * Uses only a few extra variables (pivot, i, j, temp during swap).
 * No extra arrays or recursion inside this function, so space is constant.
 */

/**
 * Performs Hoare partition on subarray arr[left..right].
 *
 * For ascending = true:
 *  - pivot = arr[left]
 *  - move i right while arr[i] < pivot
 *  - move j left while arr[j] > pivot
 *  After partition, arr[left..p] <= arr[p+1..right].
 *
 * For ascending = false (descending):
 *  - pivot = arr[left]
 *  - move i right while arr[i] > pivot
 *  - move j left while arr[j] < pivot
 *  After partition, arr[left..p] >= arr[p+1..right].
 *
 * Note:
 *  - The pivot is not guaranteed to be at the partition index.
 *
 * @param arr       Array to partition (modified in place)
 * @param left      Left index of the subarray (inclusive)
 * @param right     Right index of the subarray (inclusive)
 * @param ascending True for ascending partition, false for descending
 * @returns         Partition index p such that:
 *                  - ascending: arr[left..p] <= arr[p+1..right]
 *                  - descending: arr[left..p] >= arr[p+1..right]
 */
export function hoarePartition(
  arr: number[],
  left: number,
  right: number,
  ascending: boolean = true
): number {
  if (!arr) {
    throw new Error("Array cannot be null or undefined");
  }
  if (left < 0 || right >= arr.length) {
    throw new Error(`Invalid range: [${left}, ${right}] for length ${arr.length}`);
  }
  if (left >= right) {
    // Single element or empty logical range
    return left;
  }

  const pivot = arr[left];
  let i = left - 1;
  let j = right + 1;

  while (true) {
    if (ascending) {
      // Move i right until arr[i] >= pivot
      do {
        i++;
      } while (arr[i] < pivot);

      // Move j left until arr[j] <= pivot
      do {
        j--;
      } while (arr[j] > pivot);
    } else {
      // Descending

      // Move i right until arr[i] <= pivot
      do {
        i++;
      } while (arr[i] > pivot);

      // Move j left until arr[j] >= pivot
      do {
        j--;
      } while (arr[j] < pivot);
    }

    // Pointers crossed or met
    if (i >= j) {
      return j;
    }

    // Swap out-of-place elements
    swap(arr, i, j);
  }
}

/**
 * Swaps two elements in an array in place.
 */
function swap(arr: number[], i: number, j: number): void {
  if (i === j) return;
  const temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}

/* ----------------- Small demo tests ----------------- */

function testHoare(
  name: string,
  input: number[],
  left: number,
  right: number,
  ascending: boolean
): void {
  const arr = [...input]; // copy for clean logging
  console.log(`Test: ${name}`);
  console.log(`Order: ${ascending ? "Ascending" : "Descending"}`);
  console.log("Before:", arr);

  const p = hoarePartition(arr, left, right, ascending);

  console.log("Partition index returned:", p);
  console.log("After: ", arr);
  console.log("---------");
}

// Edge / size cases
testHoare("Single element (asc)", [5], 0, 0, true);
testHoare("Two elements (asc)", [9, 3], 0, 1, true);

// Content edge cases ascending
testHoare("Duplicates (asc)", [4, 2, 4, 1, 4], 0, 4, true);
testHoare("All equal (asc)", [7, 7, 7, 7], 0, 3, true);
testHoare("Negatives only (asc)", [-8, -3, -10, -1], 0, 3, true);
testHoare("Mixed signs (asc)", [-3, 5, -1, 2, 0], 0, 4, true);
testHoare("Already sorted (asc)", [1, 2, 3, 4, 5], 0, 4, true);
testHoare("Reverse sorted (asc)", [5, 4, 3, 2, 1], 0, 4, true);

// Content edge cases descending
testHoare("Duplicates (desc)", [4, 2, 4, 1, 4], 0, 4, false);
testHoare("All equal (desc)", [7, 7, 7, 7], 0, 3, false);
testHoare("Mixed signs (desc)", [-3, 5, -1, 2, 0], 0, 4, false);
testHoare("Reverse sorted (desc)", [5, 4, 3, 2, 1], 0, 4, false);
testHoare("Already sorted (desc)", [1, 2, 3, 4, 5], 0, 4, false);
