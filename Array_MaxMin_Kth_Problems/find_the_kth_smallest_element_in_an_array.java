
/**
 *
 * Problem. Return the Kth smallest element (1-based). Aim for O(n) average using
 * Quickselect or use heap. Non distinct and distinct
 *
 *  *Input: nums, k
 *
 *  *Example
 *
 *  *Input: nums=[7,10,4,3,20,15], k=3 Output: 7
 *
 *
 *  *Constraints
 *
 *  *1 <= k <= nums.length
 *
 *  *Sample test cases
 *
 *  *[7,10,4,3,20,15],k=3 -> 7 
 *  *[1],k=1 -> 1 
 *  *[2,2,3,1],k=2 -> 2
 *
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class find_the_kth_smallest_element_in_an_array {

    public static void main(String[] args) {
        find_the_kth_smallest_element_in_an_array obj = new find_the_kth_smallest_element_in_an_array();

        int[][] testArrays = {
            {3, 2, 1, 5, 6, 4}, // distinct
            {2, 2, 3, 1}, // non-distinct
            {1}, // single element
            {5, 3, 3, 5, 4, 5, 2, 1}, // mixed
        };
        int[] ks = {2, 3, 1, 4};

        System.out.println("=== Using Sorting (Brute) ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useSorting(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }

        System.out.println("\n=== Using Max Heap (Better) ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useMaxHeap(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }

        System.out.println("\n=== Using Min Heap (Better) ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useMinHeap(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }

        System.out.println("\n=== Using Quickselect (Optimized, Hoare Partition) ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useQuickSelect(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }
    }

    // ------------------------------
    // Method 1: Sorting (Brute force)
    // ------------------------------
    private int useSorting(int[] nums, int k) {
        // Time Complexity: O(n log n)
        // Space Complexity: O(1) or O(n) depending on JVM sort implementation
        Arrays.sort(nums); // ascending order
        return nums[k - 1]; // kth smallest
    }

    // ------------------------------
    // Method 2: Max Heap (Better)
    // Keep a max heap of size k
    // ------------------------------
    private int useMaxHeap(int[] nums, int k) {
        // Time Complexity: O(n log k)
        // Space Complexity: O(k)
        PriorityQueue<Integer> maxh = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxh.add(num);
            if (maxh.size() > k) {
                maxh.poll(); // remove largest so far, keep k smallest elements
            }
        }
        return maxh.peek(); // root is kth smallest
    }

    // ------------------------------
    // Method 3: Min Heap (Better, but heavier than Max Heap for this)
    // Build min heap and pop k-1 times
    // ------------------------------
    private int useMinHeap(int[] nums, int k) {
        // Time Complexity: O(n log n) (building + k pops)
        // Space Complexity: O(n)
        PriorityQueue<Integer> minh = new PriorityQueue<>();
        for (int num : nums) {
            minh.add(num);
        }
        for (int i = 1; i < k; i++) {
            minh.poll();
        }
        return minh.peek();
    }

    // ------------------------------
    // Method 4: Quickselect (Optimized)
    // Using Hoare-style partition, pivot at start
    // ------------------------------
    private int useQuickSelect(int[] nums, int k) {
        // Average Time Complexity: O(n)
        // Worst-case Time Complexity: O(n^2)
        // Space Complexity: O(1)
        int targetIndex = k - 1; // kth smallest -> index k-1
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        while (true) {
            if (left == right) {
                return nums[left];
            }

            int pivotIndex = partitionHoare(nums, left, right);

            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            } else if (pivotIndex > targetIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    // Hoare partition scheme
    // Pivot at nums[left], i from left+1 to right, j from right to left
    private int partitionHoare(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left + 1;
        int j = right;

        while (true) {
            while (i <= j && nums[i] <= pivot) {
                i++;
            }
            while (i <= j && nums[j] >= pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }

        swap(nums, left, j); // place pivot at its correct position
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
