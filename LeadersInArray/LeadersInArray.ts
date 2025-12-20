/**
 * Problem:
 * Given an array of integers, find all leaders in the array.
 *
 * Definition:
 * An element is a leader if it is greater than or equal to
 * all the elements to its right.
 *
 * Note:
 * - The rightmost element is always a leader.
 *
 * Example:
 * Input:  [16, 17, 4, 3, 5, 2]
 * Output: [17, 5, 2]
 */

class LeadersInArray {
  /**
   * Approach 1: Brute Force
   *
   * Idea:
   * - For each element, check all elements to its right.
   * - If no element on the right is greater, it is a leader.
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static leadersBrute(arr: number[]): number[] {
    const leaders: number[] = [];

    for (let i = 0; i < arr.length; i++) {
      let isLeader = true;

      for (let j = i + 1; j < arr.length; j++) {
        if (arr[j] > arr[i]) {
          isLeader = false;
          break;
        }
      }

      if (isLeader) {
        leaders.push(arr[i]);
      }
    }

    return leaders;
  }

  /**
   * Approach 2: Better (Right Maximum Array)
   *
   * Idea:
   * - Create a rightMax array where rightMax[i] stores
   *   the maximum element to the right of index i.
   * - If arr[i] >= rightMax[i], it is a leader.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static leadersBetter(arr: number[]): number[] {
    const n = arr.length;
    const rightMax: number[] = new Array(n);
    const leaders: number[] = [];

    rightMax[n - 1] = Number.NEGATIVE_INFINITY;

    for (let i = n - 2; i >= 0; i--) {
      rightMax[i] = Math.max(arr[i + 1], rightMax[i + 1]);
    }

    for (let i = 0; i < n; i++) {
      if (arr[i] >= rightMax[i]) {
        leaders.push(arr[i]);
      }
    }

    return leaders;
  }

  /**
   * Approach 3: Optimal
   *
   * Idea:
   * - Traverse from right to left.
   * - Maintain maxSoFar.
   * - If current element >= maxSoFar, it is a leader.
   * - Reverse result to preserve original order.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static leadersOptimal(arr: number[]): number[] {
    const leaders: number[] = [];
    let maxSoFar = Number.NEGATIVE_INFINITY;

    for (let i = arr.length - 1; i >= 0; i--) {
      if (arr[i] >= maxSoFar) {
        leaders.push(arr[i]);
        maxSoFar = arr[i];
      }
    }

    return leaders.reverse();
  }
}

// Test cases
const arr = [16, 17, 4, 3, 5, 2];

console.log(LeadersInArray.leadersBrute(arr));
console.log(LeadersInArray.leadersBetter(arr));
console.log(LeadersInArray.leadersOptimal(arr));
