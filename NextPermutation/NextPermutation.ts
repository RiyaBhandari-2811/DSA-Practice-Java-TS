/**
 * BRUTE FORCE APPROACH
 *
 * Time Complexity: O(n! * n)
 * Space Complexity: O(n!)
 *
 * Stable: No
 */
function bruteForce(nums: number[]): void {
  const perms: number[][] = [];

  function permute(arr: number[], idx: number) {
    if (idx === arr.length) {
      perms.push([...arr]);
      return;
    }
    for (let i = idx; i < arr.length; i++) {
      [arr[i], arr[idx]] = [arr[idx], arr[i]];
      permute(arr, idx + 1);
      [arr[i], arr[idx]] = [arr[idx], arr[i]];
    }
  }

  permute([...nums], 0);

  perms.sort((a, b) => {
    for (let i = 0; i < a.length; i++) {
      if (a[i] !== b[i]) return a[i] - b[i];
    }
    return 0;
  });

  for (let i = 0; i < perms.length; i++) {
    if (perms[i].every((v, idx) => v === nums[idx])) {
      const next = perms[(i + 1) % perms.length];
      for (let j = 0; j < nums.length; j++) nums[j] = next[j];
      break;
    }
  }
}

/**
 * BETTER APPROACH
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 *
 * Stable: No
 */
function betterApproach(nums: number[]): void {
  let i = nums.length - 2;

  while (i >= 0 && nums[i] >= nums[i + 1]) i--;

  if (i >= 0) {
    let j = nums.length - 1;
    while (nums[j] <= nums[i]) j--;
    [nums[i], nums[j]] = [nums[j], nums[i]];
  }

  const suffix = nums.splice(i + 1).sort((a, b) => a - b);
  nums.push(...suffix);
}

/**
 * OPTIMAL APPROACH
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Stable: No
 */
function optimal(nums: number[]): void {
  let idx = -1;

  for (let i = nums.length - 2; i >= 0; i--) {
    if (nums[i] < nums[i + 1]) {
      idx = i;
      break;
    }
  }

  if (idx === -1) {
    nums.reverse();
    return;
  }

  for (let i = nums.length - 1; i > idx; i--) {
    if (nums[i] > nums[idx]) {
      [nums[i], nums[idx]] = [nums[idx], nums[i]];
      break;
    }
  }

  let l = idx + 1, r = nums.length - 1;
  while (l < r) {
    [nums[l], nums[r]] = [nums[r], nums[l]];
    l++;
    r--;
  }
}

// Test
const nums = [1, 2, 3];
optimal(nums);
console.log(nums);
