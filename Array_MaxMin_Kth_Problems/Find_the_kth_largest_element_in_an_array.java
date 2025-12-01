
/**
 *
 * Problem. Return the Kth largest element (1-based). Aim for O(n) average using
 * Quickselect or use heap. Non distinct and distinct
 *
 *  *Input: nums, k
 *
 *  *Example
 *
 *  *Input: nums=[3,2,1,5,6,4], k=2 Output: 5
 *
 *
 *  *Constraints
 *
 *  *1 <= k <= nums.length
 *
 *  *Sample test cases
 *
 *  *[3,2,1,5,6,4],k=2 -> 5 [1],k=1 -> 1 [2,2,3,1],k=3 -> 2
 *
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Find_the_kth_largest_element_in_an_array {

    public static void main(String[] args) {
        Find_the_kth_largest_element_in_an_array obj = new Find_the_kth_largest_element_in_an_array();

        int[][] testArrays = {
            {3, 2, 1, 5, 6, 4}, // distinct
            {2, 2, 3, 1}, // non-distinct
            {1}, // single element
            {5, 3, 3, 5, 4, 5, 2, 1}, // mixed
        };
        int[] ks = {2, 3, 1, 4};

        System.out.println("=== Using Sorting ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useSorting(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }

        System.out.println("\n=== Using Min Heap ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useMinHeap(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }

        System.out.println("\n=== Using Max Heap ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useMaxHeap(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }

        System.out.println("\n=== Using Quickselect ===");
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Input: " + Arrays.toString(testArrays[i]) + ", k=" + ks[i]
                    + " -> " + obj.useQuickSelect(Arrays.copyOf(testArrays[i], testArrays[i].length), ks[i]));
        }
    }

    // ------------------------------
    // Method 1: Sorting
    // ------------------------------
    private int useSorting(int[] nums, int k) {
        // Time Complexity: O(n log n)
        // Space Complexity: O(1) or O(n) depending on JVM sort implementation
        Arrays.sort(nums); // ascending order
        return nums[nums.length - k];
    }

    // ------------------------------
    // Method 2: Max Heap
    // ------------------------------
    private int useMaxHeap(int[] nums, int k) {
        // Time Complexity: O(n log n)
        // Space Complexity: O(n)
        PriorityQueue<Integer> maxh = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxh.add(num);
        }
        for (int i = 1; i < k; i++) {
            maxh.poll();
        }
        return maxh.peek();
    }

    // ------------------------------
    // Method 3: Min Heap
    // ------------------------------
    private int useMinHeap(int[] nums, int k) {
        // Time Complexity: O(n log k)
        // Space Complexity: O(k)
        PriorityQueue<Integer> minh = new PriorityQueue<>();
        for (int num : nums) {
            minh.add(num);
            if (minh.size() > k) {
                minh.poll();
            }
        }
        return minh.peek();
    }

    // ------------------------------
    // Method 4: Quickselect
    // ------------------------------
    private int useQuickSelect(int[] nums, int k) {
        // Average Time Complexity: O(n)
        // Worst-case Time Complexity: O(n^2)
        // Space Complexity: O(1)
        int target = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, target);
    }

    private int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) {
            return nums[left];
        }

        int pivotIndex = partition(nums, left, right);
        if (pivotIndex == kSmallest) {
            return nums[pivotIndex];
        } else if (pivotIndex < kSmallest) {
            return quickSelect(nums, pivotIndex + 1, right, kSmallest);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, kSmallest);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
