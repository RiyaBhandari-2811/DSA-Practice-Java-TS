/**
 * Problem: Return the Kth smallest element (1-based)
 * Handles both distinct and non-distinct elements.
 */

/* -----------------------------------------
   Method 1: Sorting (Brute Force)
----------------------------------------- */
function useSortingForSmallest(nums: number[], k: number): number {
  // Time Complexity: O(n log n)
  // Space Complexity: O(n)
  nums.sort((a, b) => a - b);
  return nums[k - 1]; // kth smallest
}

/* -----------------------------------------
   Method 2: Max Heap (Better)
   Keep a heap of size k to store k smallest
----------------------------------------- */
function useMaxHeapForSmallest(nums: number[], k: number): number {
  // Time Complexity: O(n log k)
  // Space Complexity: O(k)
  const heap: number[] = [];

  const push = (val: number) => {
    heap.push(val);
    heap.sort((a, b) => b - a); // max heap behavior
  };

  const pop = () => heap.shift(); // remove largest

  for (const num of nums) {
    push(num);
    if (heap.length > k) pop();
  }

  return heap[0];
}

/* -----------------------------------------
   Method 3: Min Heap (Better but heavier)
----------------------------------------- */
function useMinHeapForSmallest(nums: number[], k: number): number {
  // Time Complexity: O(n log n)
  // Space Complexity: O(n)
  const heap: number[] = [];

  const push = (val: number) => {
    heap.push(val);
    heap.sort((a, b) => a - b);
  };

  const pop = () => heap.shift();

  for (const num of nums) {
    push(num);
  }

  for (let i = 1; i < k; i++) {
    pop();
  }

  return heap[0];
}

/* -----------------------------------------
   Method 4: Quickselect (Optimized)
   Hoare-style partition, pivot at start
----------------------------------------- */
function useQuickSelectForSmallest(nums: number[], k: number): number {
  // Average Time: O(n)
  // Worst Time: O(n^2)
  // Space: O(1) in-place

  const targetIndex = k - 1; // kth smallest → index k-1

  const partitionHoare = (left: number, right: number): number => {
    const pivot = nums[left];
    let i = left + 1;
    let j = right;

    while (true) {
      while (i <= j && nums[i] <= pivot) i++;
      while (i <= j && nums[j] >= pivot) j--;
      if (i >= j) break;
      [nums[i], nums[j]] = [nums[j], nums[i]];
    }

    [nums[left], nums[j]] = [nums[j], nums[left]];
    return j;
  };

  const quickSelect = (left: number, right: number): number => {
    while (true) {
      if (left === right) return nums[left];

      const pivotIndex = partitionHoare(left, right);

      if (pivotIndex === targetIndex) return nums[pivotIndex];
      else if (pivotIndex > targetIndex) right = pivotIndex - 1;
      else left = pivotIndex + 1;
    }
  };

  return quickSelect(0, nums.length - 1);
}

/* -----------------------------------------
   TEST CASES
----------------------------------------- */
const testCasesForSmallest: { nums: number[]; k: number }[] = [
  { nums: [3, 2, 1, 5, 6, 4], k: 2 },       // distinct
  { nums: [2, 2, 3, 1], k: 2 },             // non-distinct
  { nums: [1], k: 1 },                      // single element
  { nums: [5, 3, 3, 5, 4, 5, 2, 1], k: 4 }, // mixed
];

console.log("=== Using Sorting ===");
for (const { nums, k } of testCasesForSmallest)
  console.log(`[${nums}] k=${k} -> ${useSortingForSmallest([...nums], k)}`);

console.log("\n=== Using Max Heap ===");
for (const { nums, k } of testCasesForSmallest)
  console.log(`[${nums}] k=${k} -> ${useMaxHeapForSmallest([...nums], k)}`);

console.log("\n=== Using Min Heap ===");
for (const { nums, k } of testCasesForSmallest)
  console.log(`[${nums}] k=${k} -> ${useMinHeapForSmallest([...nums], k)}`);

console.log("\n=== Using Quickselect (Hoare Partition) ===");
for (const { nums, k } of testCasesForSmallest)
  console.log(`[${nums}] k=${k} -> ${useQuickSelectForSmallest([...nums], k)}`);
