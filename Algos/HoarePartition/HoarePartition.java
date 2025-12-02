import java.util.Arrays;

/**
 * Hoare Partition
 *
 * Definition:
 * Hoare Partition is a partitioning algorithm used in QuickSort that:
 * 1. Usually selects the first element of the subarray as the pivot.
 * 2. Uses two pointers:
 *      - one starting near the left moving to the right,
 *      - one starting near the right moving to the left.
 * 3. Moves the left pointer until it finds an element that should be on the
 *    other side of the pivot, and similarly moves the right pointer.
 * 4. Swaps the elements at these pointers when they are on the wrong side.
 * 5. Stops when the pointers cross and returns a partition index.
 *
 * After partition:
 *  - For ascending order:
 *        all elements in arr[low..p] are less than or equal to
 *        all elements in arr[p+1..high].
 *  - The pivot is not guaranteed to be at index p.
 *
 * Time & Space Complexity:
 *
 * Time Complexity: O(n)
 * ---------------------
 * Hoare Partition scans the subarray with two pointers that move toward
 * each other. Each element is compared a constant number of times and at
 * most swapped once. Therefore, the total work grows linearly with the 
 * number of elements in the subarray.
 *
 * Best Case:    O(n)
 * Worst Case:   O(n)
 * Average Case: O(n)
 *
 * Space Complexity: O(1)
 * ----------------------
 * Only a constant number of variables are used (pivot, i, j, temp during swap).
 * No additional data structures or recursion are required inside this function.
 */
public class HoarePartition {

    public static void main(String[] args) {
        // Edge / size cases
        test("Single element (asc)", new int[]{5}, 0, 0, true);
        test("Two elements (asc)", new int[]{9, 3}, 0, 1, true);

        // Content edge cases (ascending)
        test("Duplicates (asc)", new int[]{4, 2, 4, 1, 4}, 0, 4, true);
        test("All equal (asc)", new int[]{7, 7, 7, 7}, 0, 3, true);
        test("Negatives only (asc)", new int[]{-8, -3, -10, -1}, 0, 3, true);
        test("Mixed signs (asc)", new int[]{-3, 5, -1, 2, 0}, 0, 4, true);
        test("Already sorted (asc)", new int[]{1, 2, 3, 4, 5}, 0, 4, true);
        test("Reverse sorted (asc)", new int[]{5, 4, 3, 2, 1}, 0, 4, true);

        // Content edge cases (descending)
        test("Duplicates (desc)", new int[]{4, 2, 4, 1, 4}, 0, 4, false);
        test("All equal (desc)", new int[]{7, 7, 7, 7}, 0, 3, false);
        test("Mixed signs (desc)", new int[]{-3, 5, -1, 2, 0}, 0, 4, false);
        test("Reverse sorted (desc)", new int[]{5, 4, 3, 2, 1}, 0, 4, false);
        test("Already sorted (desc)", new int[]{1, 2, 3, 4, 5}, 0, 4, false);
    }

    private static void test(String name, int[] arr, int low, int high, boolean ascending) {
        System.out.println("Test: " + name);
        System.out.println("Order: " + (ascending ? "Ascending" : "Descending"));
        System.out.println("Before: " + Arrays.toString(arr));

        int p = hoarePartition(arr, low, high, ascending);

        System.out.println("Partition index returned: " + p);
        System.out.println("After:  " + Arrays.toString(arr));
        System.out.println();
    }

    /**
     * Hoare partition on subarray arr[low..high].
     *
     * For ascending = true:
     *  - pivot = arr[low]
     *  - move i right while arr[i] < pivot
     *  - move j left while arr[j] > pivot
     *  After partition, all elements in arr[low..p] are <= all elements in arr[p+1..high].
     *
     * For ascending = false (descending):
     *  - pivot = arr[low]
     *  - move i right while arr[i] > pivot
     *  - move j left while arr[j] < pivot
     *  After partition, all elements in arr[low..p] are >= all elements in arr[p+1..high].
     *
     * Note: the pivot is not guaranteed to end up at the partition index.
     *
     * @param arr       the array to partition (modified in place)
     * @param low       left index of the subarray (inclusive)
     * @param high      right index of the subarray (inclusive)
     * @param ascending true for ascending partition, false for descending
     * @return          partition index p such that:
     *                  - for ascending: arr[low..p] <= arr[p+1..high]
     *                  - for descending: arr[low..p] >= arr[p+1..high]
     */
    public static int hoarePartition(int[] arr, int low, int high, boolean ascending) {
        if (arr == null) {
            throw new NullPointerException("Array cannot be null");
        }
        if (low < 0 || high >= arr.length) {
            throw new IllegalArgumentException("Invalid range: [" + low + ", " + high + "]");
        }
        if (low >= high) {
            // Single element or empty logical range
            return low;
        }

        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            if (ascending) {
                // Move i right until arr[i] >= pivot
                do {
                    i++;
                } while (arr[i] < pivot);

                // Move j left until arr[j] <= pivot
                do {
                    j--;
                } while (arr[j] > pivot);
            } else {
                // Descending

                // Move i right until arr[i] <= pivot (stop when arr[i] <= pivot)
                do {
                    i++;
                } while (arr[i] > pivot);

                // Move j left until arr[j] >= pivot (stop when arr[j] >= pivot)
                do {
                    j--;
                } while (arr[j] < pivot);
            }

            // When pointers cross or meet, partition is complete
            if (i >= j) {
                return j;
            }

            // Swap the out-of-place elements
            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
