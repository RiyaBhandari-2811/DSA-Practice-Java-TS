/**
 * FindMaxInRotatedSortedArray.ts
 *
 * This file contains two approaches to find the maximum value
 * in a rotated sorted array with distinct elements.
 */
export class FindMaxInRotatedSortedArray {
  /**
   * Linear scan approach to find the maximum in a rotated sorted array.
   * Works for any array, ignores the rotated property.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public static findMaxLinear(nums: number[]): number {
    if (!nums || nums.length === 0) {
      throw new Error("Array must not be empty");
    }

    let max = nums[0];

    for (let i = 1; i < nums.length; i++) {
      if (nums[i] > max) {
        max = nums[i];
      }
    }

    return max;
  }

  /**
   * Binary search approach to find the maximum in a rotated sorted array
   * with distinct elements.
   *
   * Idea:
   * - First find the index of the minimum element using binary search.
   * - In a rotated sorted array, the maximum element is just before the minimum.
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   */
  public static findMaxBinarySearch(nums: number[]): number {
    if (!nums || nums.length === 0) {
      throw new Error("Array must not be empty");
    }

    const n = nums.length;

    // If array is already sorted and not rotated
    if (nums[0] <= nums[n - 1]) {
      return nums[n - 1];
    }

    // Find index of minimum element
    const minIndex = this.findMinIndex(nums);

    // Maximum is the element just before the minimum in circular manner
    const maxIndex = (minIndex - 1 + n) % n;
    return nums[maxIndex];
  }

  /**
   * Helper method to find index of the minimum element
   * in a rotated sorted array with distinct elements.
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   */
  private static findMinIndex(nums: number[]): number {
    let left = 0;
    let right = nums.length - 1;

    while (left < right) {
      const mid = left + Math.floor((right - left) / 2);

      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left; // left == right at index of minimum
  }
}

// Demo
const arr = [7, 9, 12, 15, 2, 3, 5];

console.log(FindMaxInRotatedSortedArray.findMaxLinear(arr));        // 15
console.log(FindMaxInRotatedSortedArray.findMaxBinarySearch(arr));  // 15
