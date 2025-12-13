/**
 * Majority Element (> n/2 times)
 */

/* -------------------------------------------------------------------------- */
/*                               1. BRUTE FORCE                               */
/* -------------------------------------------------------------------------- */
export function majorityBrute(nums: number[]): number {
    const n = nums.length;
  
    for (let i = 0; i < n; i++) {
      let count = 0;
      for (let j = 0; j < n; j++) {
        if (nums[i] === nums[j]) count++;
      }
      if (count > Math.floor(n / 2)) return nums[i];
    }
  
    return -1;
  }
  
  /* -------------------------------------------------------------------------- */
  /*                                2. HASHING                                  */
  /* -------------------------------------------------------------------------- */
  export function majorityHash(nums: number[]): number {
    const map = new Map<number, number>();
    const n = nums.length;
  
    for (const num of nums) {
      map.set(num, (map.get(num) ?? 0) + 1);
      if ((map.get(num) as number) > Math.floor(n / 2)) {
        return num;
      }
    }
  
    return -1;
  }
  
  /* -------------------------------------------------------------------------- */
  /*                                3. SORTING                                  */
  /* -------------------------------------------------------------------------- */
  export function majoritySorting(nums: number[]): number {
    const arr = [...nums].sort((a, b) => a - b);
    return arr[Math.floor(arr.length / 2)];
  }
  
  /* -------------------------------------------------------------------------- */
  /*                        4. BOYER MOORE (OPTIMAL)                             */
  /* -------------------------------------------------------------------------- */
  export function majorityBoyerMoore(nums: number[]): number {
    let count = 0;
    let candidate = 0;
  
    for (const num of nums) {
      if (count === 0) candidate = num;
      count += num === candidate ? 1 : -1;
    }
  
    return candidate;
  }
  
  /* TEST */
  console.log(majorityBrute([3, 2, 3]));
  console.log(majorityHash([2, 2, 1, 1, 1, 2, 2]));
  console.log(majoritySorting([2, 2, 1, 1, 1, 2, 2]));
  console.log(majorityBoyerMoore([2, 2, 1, 1, 1, 2, 2]));
  