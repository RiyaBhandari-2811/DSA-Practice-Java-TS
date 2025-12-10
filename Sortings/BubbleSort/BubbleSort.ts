/**
 * BUBBLE SORT
 *
 * Idea:
 * - Repeatedly compare adjacent items and swap if needed.
 * - After each pass, the largest element bubbles to the end.
 *
 * Optimization:
 * - Stop early if no swaps in a full pass.
 *
 * Time: O(n^2)
 * Space: O(1)
 * Stable: Yes
 */
export function bubbleSort(arr: number[]): number[] {
  const nums = [...arr];
  const n = nums.length;

  for (let i = 0; i < n - 1; i++) {
    let swapped = false;

    for (let j = 0; j < n - 1 - i; j++) {
      if (nums[j] > nums[j + 1]) {
        [nums[j], nums[j + 1]] = [nums[j + 1], nums[j]];
        swapped = true;
      }
    }

    if (!swapped) break;
  }

  return nums;
}

/* TESTING */
const a2 = [5, 1, 4, 2, 8];

console.log("Bubble Sort:", bubbleSort(a2));
