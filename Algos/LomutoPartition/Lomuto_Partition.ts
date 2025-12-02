/**
 * Lomuto Partition 
 *
 * Time Complexity: O(n)
 * - Single linear scan from left to right - 1.
 * - Each step does one comparison and at most one swap.
 *
 * Space Complexity: O(1)
 * - Uses only a few extra variables (pivotValue, i, j).
 */

/**
 * Partitions the subarray arr[left..right] around a pivot using Lomuto scheme.
 * Pivot is chosen as arr[right].
 *
 * After partition:
 * - Ascending: all elements <= pivot are on the left, > pivot on the right.
 * - Descending: all elements >= pivot are on the left, < pivot on the right.
 *
 * @param arr       The array to partition (modified in place)
 * @param left      Left index of the subarray (inclusive)
 * @param right     Right index of the subarray (inclusive, pivot = arr[right])
 * @param ascending If true, partition for ascending order, else for descending
 * @returns         Final index of the pivot
 */
export function lomutoPartition(
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

  const pivotValue = arr[right];
  let i = left - 1;

  for (let j = left; j < right; j++) {
    if (ascending) {
      if (arr[j] <= pivotValue) {
        i++;
        swap(arr, i, j);
      }
    } else {
      if (arr[j] >= pivotValue) {
        i++;
        swap(arr, i, j);
      }
    }
  }

  swap(arr, i + 1, right);
  return i + 1;
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

function testLomuto(
  name: string,
  input: number[],
  left: number,
  right: number,
  ascending: boolean
): void {
  const arr = [...input]; // copy so original is not mutated in logs
  console.log(`Test: ${name}`);
  console.log(`Order: ${ascending ? "Ascending" : "Descending"}`);
  console.log("Before:", arr);

  const pivotIndex = lomutoPartition(arr, left, right, ascending);

  console.log(`Pivot index: ${pivotIndex}, pivot value: ${arr[pivotIndex]}`);
  console.log("After: ", arr);
  console.log("---------");
}

// Run only if this file is the main script (optional check, Node style)
// You can just call testLomuto manually in your environment.
testLomuto("Mixed signs asc", [-3, 5, -1, 2, 0], 0, 4, true);
testLomuto("Mixed signs desc", [-3, 5, -1, 2, 0], 0, 4, false);
testLomuto("Duplicates asc", [4, 2, 4, 1, 4], 0, 4, true);
testLomuto("Negatives asc", [-8, -3, -10, -1], 0, 3, true);
