/**
 *
 * This file contains two approaches to find the minimum value
 * in a rotated sorted array with distinct elements.
 */
export class FindMinInRotatedSortedArray {
  /**
   * Linear scan approach to find the minimum in a rotated sorted array.
   * Works for any array, ignores the rotated property.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public static findMinLinear(nums: number[]): number {
    if (!nums || nums.length === 0) {
      throw new Error("Array must not be empty");
    }

    let min = nums[0];

    for (let i = 1; i < nums.length; i++) {
      if (nums[i] < min) {
        min = nums[i];
      }
    }

    return min;
  }

  /**
   * Binary search approach to find the minimum in a rotated sorted array
   * with distinct elements.
   *
   * Idea:
   * - Use the fact that at least one half of the array is sorted.
   * - Compare the middle element with the rightmost element to decide
   *   which side contains the minimum.
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   */
  public static findMinBinarySearch(nums: number[]): number {
    if (!nums || nums.length === 0) {
      throw new Error("Array must not be empty");
    }

    let left = 0;
    let right = nums.length - 1;

    // If array is already sorted and not rotated
    if (nums[left] <= nums[right]) {
      return nums[left];
    }

    // Binary search on rotated sorted array
    while (left < right) {
      const mid = left + Math.floor((right - left) / 2);

      // If mid element is greater than right element,
      // minimum is in the right half
      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        // Minimum is in the left half including mid
        right = mid;
      }
    }

    // left == right at the minimum element
    return nums[left];
  }
}

// Demo
const nums1 = [3, 4, 5, 1, 2];
const nums2 = [4, 5, 6, 7, 0, 1, 2];
const nums3 = [11, 13, 15, 17];
const nums4 = [2];

console.log(FindMinInRotatedSortedArray.findMinLinear(nums1)); // 1
console.log(FindMinInRotatedSortedArray.findMinBinarySearch(nums1)); // 1

console.log(FindMinInRotatedSortedArray.findMinLinear(nums2)); // 0
console.log(FindMinInRotatedSortedArray.findMinBinarySearch(nums2)); // 0

console.log(FindMinInRotatedSortedArray.findMinLinear(nums3)); // 11
console.log(FindMinInRotatedSortedArray.findMinBinarySearch(nums3)); // 11

console.log(FindMinInRotatedSortedArray.findMinLinear(nums4)); // 2
console.log(FindMinInRotatedSortedArray.findMinBinarySearch(nums4)); // 2
