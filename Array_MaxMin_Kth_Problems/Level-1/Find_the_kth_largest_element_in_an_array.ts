/**
 * Problem: Return the Kth largest element (1-based)
 * Handles both distinct and non-distinct elements.
 */

function useSorting(nums: number[], k: number): number {
  // Time Complexity: O(n log n)
  // Space Complexity: O(n)
  nums.sort((a, b) => a - b);
  return nums[nums.length - k];
}

function useMinHeap(nums: number[], k: number): number {
  // Using a simple array-based min heap simulation
  // Time Complexity: O(n * k log k)  -> because sorting on every push
  // Space Complexity: O(k)
  const heap: number[] = [];

  const push = (val: number) => {
    heap.push(val);
    heap.sort((a, b) => a - b); // keep ascending order
  };

  const pop = () => heap.shift(); // remove smallest

  for (const num of nums) {
    push(num);
    if (heap.length > k) pop();
  }

  return heap[0];
}

function useMaxHeap(nums: number[], k: number): number {
  // Time Complexity: O(n log n)
  // Space Complexity: O(n)
  const heap: number[] = [...nums].sort((a, b) => b - a);
  return heap[k - 1];
}

function useQuickSelect(nums: number[], k: number): number {
  // Average Time Complexity: O(n)
  // Worst-case Time Complexity: O(n^2)
  // Space Complexity: O(1) (in-place)
  const target = nums.length - k;

  const partition = (left: number, right: number): number => {
    const pivot = nums[right];
    let storeIndex = left;
    for (let i = left; i < right; i++) {
      if (nums[i] < pivot) {
        [nums[i], nums[storeIndex]] = [nums[storeIndex], nums[i]];
        storeIndex++;
      }
    }
    [nums[storeIndex], nums[right]] = [nums[right], nums[storeIndex]];
    return storeIndex;
  };

  const quickSelect = (left: number, right: number): number => {
    if (left === right) return nums[left];

    const pivotIndex = partition(left, right);
    if (pivotIndex === target) return nums[pivotIndex];
    else if (pivotIndex < target) return quickSelect(pivotIndex + 1, right);
    else return quickSelect(left, pivotIndex - 1);
  };

  return quickSelect(0, nums.length - 1);
}

// === TEST CASES ===

const testCases: { nums: number[]; k: number }[] = [
  { nums: [3, 2, 1, 5, 6, 4], k: 2 },       // distinct
  { nums: [2, 2, 3, 1], k: 3 },             // non-distinct
  { nums: [1], k: 1 },                      // single element
  { nums: [5, 3, 3, 5, 4, 5, 2, 1], k: 4 }, // mixed
];

console.log("=== Using Sorting ===");
for (const { nums, k } of testCases)
  console.log(`[${nums}] k=${k} -> ${useSorting([...nums], k)}`);

console.log("\n=== Using Min Heap ===");
for (const { nums, k } of testCases)
  console.log(`[${nums}] k=${k} -> ${useMinHeap([...nums], k)}`);

console.log("\n=== Using Max Heap ===");
for (const { nums, k } of testCases)
  console.log(`[${nums}] k=${k} -> ${useMaxHeap([...nums], k)}`);

console.log("\n=== Using Quickselect ===");
for (const { nums, k } of testCases)
  console.log(`[${nums}] k=${k} -> ${useQuickSelect([...nums], k)}`);
