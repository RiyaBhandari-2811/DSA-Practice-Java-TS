
import java.util.Arrays;

/**
 * Lomuto Partition
 *
 * Definition: Lomuto Partition is a partitioning algorithm used in QuickSort
 * that: 1. Selects the last element of the subarray as the pivot. 2. Rearranges
 * the subarray so that: - all elements less than or equal to the pivot come
 * before it, and - all elements greater than the pivot come after it. 3.
 * Finally places the pivot in its correct sorted position and returns its
 * index.
 *
 * It works by maintaining a boundary index i that tracks the end of the region
 * containing elements <= pivot (for ascending order), and then swapping
 * elements while scanning the array from left to right.
 */
/**
 * Time & Space Complexity (Lomuto Partition)
 *
 * Time Complexity: O(n) --------------------- The algorithm performs a single
 * linear scan from index 'left' to 'right - 1'. For each element, it does one
 * comparison (arr[j] <= pivot) and at most one swap. After the loop ends, it
 * performs one final swap to place the pivot in its correct position. Since the
 * number of operations grows directly with the size of the subarray, the time
 * complexity is linear.
 *
 * Best Case: O(n) → still must scan the entire subarray Worst Case: O(n) →
 * still one pass regardless of pivot position Average Case: O(n)
 *
 * Space Complexity: O(1) ----------------------- Lomuto Partition uses only a
 * constant amount of extra space: - a pivot variable, - two integer pointers (i
 * and j), - and temporary space during swap. No additional data structures or
 * recursion are used. Therefore, the space usage remains constant.
 */
public class Lomuto_Partition {

    public static void main(String[] args) {
        // Ascending order tests
        test("Single element (asc)", new int[]{5}, 0, 0, true);
        test("Two elements (asc)", new int[]{9, 3}, 0, 1, true);
        test("Duplicates (asc)", new int[]{4, 2, 4, 1, 4}, 0, 4, true);
        test("All equal (asc)", new int[]{7, 7, 7, 7}, 0, 3, true);
        test("Negatives only (asc)", new int[]{-8, -3, -10, -1}, 0, 3, true);
        test("Mixed signs (asc)", new int[]{-3, 5, -1, 2, 0}, 0, 4, true);
        test("Already sorted (asc)", new int[]{1, 2, 3, 4, 5}, 0, 4, true);
        test("Reverse sorted (asc)", new int[]{5, 4, 3, 2, 1}, 0, 4, true);

        // Descending order tests
        test("Duplicates (desc)", new int[]{4, 2, 4, 1, 4}, 0, 4, false);
        test("Mixed signs (desc)", new int[]{-3, 5, -1, 2, 0}, 0, 4, false);
    }

    private static void test(String name, int[] arr, int left, int right, boolean ascending) {
        System.out.println("Test: " + name);
        System.out.println("Order: " + (ascending ? "Ascending" : "Descending"));
        System.out.println("Before: " + Arrays.toString(arr));

        int pivotIndex = lomutoPartition(arr, left, right, ascending);

        System.out.println("Pivot index: " + pivotIndex + ", pivot value: " + arr[pivotIndex]);
        System.out.println("After:  " + Arrays.toString(arr));
        System.out.println();
    }

    /**
     * Lomuto partition on subarray [left, right] (both inclusive).
     *
     * @param arr input array
     * @param left left index of subarray
     * @param right right index of subarray (pivot is arr[right])
     * @param ascending true for ascending, false for descending
     * @return final index of the pivot
     */
    public static int lomutoPartition(int[] arr, int left, int right, boolean ascending) {
        if (arr == null) {
            throw new NullPointerException("Array cannot be null");
        }
        if (left < 0 || right >= arr.length) {
            throw new IllegalArgumentException("Invalid range: [" + left + ", " + right + "]");
        }
        if (left >= right) {
            // Single element or empty logical range
            return left;
        }

        int pivotValue = arr[right];   // pivot is the value at "right"
        int i = left - 1;              // boundary of <= pivot (asc) or >= pivot (desc)

        for (int j = left; j < right; j++) {
            if (ascending) {
                if (arr[j] <= pivotValue) {
                    i++;
                    swap(arr, i, j);
                }
            } else {
                if (arr[j] >= pivotValue) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }

        // Place pivot just after the "valid" region
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
