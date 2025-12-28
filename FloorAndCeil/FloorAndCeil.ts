/**
 * Problem:
 * Find floor and ceil of a given number x in a sorted array.
 *
 * Floor: largest element <= x
 * Ceil: smallest element >= x
 */

class FloorAndCeil {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static floorAndCeilBrute(arr: number[], x: number): number[] {
    let floor = -1;
    let ceil = -1;

    for (const num of arr) {
      if (num <= x) floor = num;
      if (num >= x) {
        ceil = num;
        break;
      }
    }

    return [floor, ceil];
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static floorAndCeilOptimal(arr: number[], x: number): number[] {
    let low = 0;
    let high = arr.length - 1;
    let floor = -1;
    let ceil = -1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (arr[mid] === x) {
        return [arr[mid], arr[mid]];
      } else if (arr[mid] < x) {
        floor = arr[mid];
        low = mid + 1;
      } else {
        ceil = arr[mid];
        high = mid - 1;
      }
    }

    return [floor, ceil];
  }
}

// Test
const arr = [3, 4, 7, 8, 8, 10];
const x = 5;

console.log(FloorAndCeil.floorAndCeilBrute(arr, x));
console.log(FloorAndCeil.floorAndCeilOptimal(arr, x));
