/**
 * SELECTION SORT
 *
 * Idea:
 * - For each index i, find the minimum element in the remaining array.
 * - Swap it with arr[i].
 *
 * Time: O(n^2)
 * Space: O(1)
 * Stable: No
 */
export function selectionSort(arr: number[]): number[] {
  const nums = [...arr];
  const n = nums.length;

  for (let i = 0; i < n - 1; i++) {
    let minIndex = i;

    for (let j = i + 1; j < n; j++) {
      if (nums[j] < nums[minIndex]) {
        minIndex = j;
      }
    }

    // Swap
    [nums[i], nums[minIndex]] = [nums[minIndex], nums[i]];
  }

  return nums;
}

/* TESTING */
const a1 = [64, 25, 12, 22, 11];

console.log("Selection Sort:", selectionSort(a1));
