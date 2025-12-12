/**
 * Approach 1: Brute Force
 *
 * Time: O(n^2)
 * Space: O(1)
 */
export function twoSumBrute(nums: number[], target: number): number[] {
    const n = nums.length;
  
    for (let i = 0; i < n; i++) {
      for (let j = i + 1; j < n; j++) {
        if (nums[i] + nums[j] === target) {
          return [i, j];
        }
      }
    }
  
    return [-1, -1];
  }
  
  /**
   * Approach 2: HashMap (Optimal for unsorted arrays)
   *
   * Time: O(n)
   * Space: O(n)
   */
  export function twoSumHash(nums: number[], target: number): number[] {
    const map = new Map<number, number>(); // value → index
  
    for (let i = 0; i < nums.length; i++) {
      const need = target - nums[i];
  
      if (map.has(need)) {
        return [map.get(need)!, i];
      }
  
      map.set(nums[i], i);
    }
  
    return [-1, -1];
  }
  
  /**
   * Approach 3: Two Pointers (Array MUST be sorted)
   *
   * Time: O(n log n) because of sorting
   * Space: O(n) if keeping original indices
   */
  export function twoSumTwoPointer(nums: number[], target: number): number[] {
    const n = nums.length;
  
    // pair array to store original index
    const pairs = nums.map((val, idx) => ({ val, idx }));
  
    pairs.sort((a, b) => a.val - b.val);
  
    let left = 0;
    let right = n - 1;
  
    while (left < right) {
      const sum = pairs[left].val + pairs[right].val;
  
      if (sum === target) {
        return [pairs[left].idx, pairs[right].idx];
      }
  
      if (sum < target) left++;
      else right--;
    }
  
    return [-1, -1];
  }
  
  /* TEST */
  console.log(twoSumBrute([2, 7, 11, 15], 9));
  console.log(twoSumHash([2, 7, 11, 15], 9));
  console.log(twoSumTwoPointer([2, 7, 11, 15], 9));
  