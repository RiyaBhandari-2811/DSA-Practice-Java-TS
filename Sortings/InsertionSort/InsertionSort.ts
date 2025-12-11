/**
 * INSERTION SORT
 *
 * Idea:
 * - Assume left side is sorted.
 * - Insert arr[i] into correct sorted position in the left side.
 *
 * Time:
 * - Best: O(n)
 * - Worst: O(n^2)
 *
 * Space: O(1)
 * Stable: Yes
 */
export function insertionSort(arr: number[]): number[] {
  const nums = [...arr];

  for (let i = 1; i < nums.length; i++) {
    let key = nums[i];
    let j = i - 1;

    // Shift all bigger elements to the right
    while (j >= 0 && nums[j] > key) {
      nums[j + 1] = nums[j];
      j--;
    }

    nums[j + 1] = key;
  }

  return nums;
}

/* TEST */
console.log(insertionSort([12, 11, 13, 5, 6]));
