
public class InsertionSort {

    /**
     * INSERTION SORT
     *
     * Idea: - Treat the left portion of the array as sorted. - Take nums[i],
     * insert it into its correct position in the sorted left side.
     *
     * Time Complexity: - Best case: O(n) (already sorted) - Worst/Avg: O(n^2)
     *
     * Space Complexity: O(1)
     *
     * Stable: Yes
     *
     * Works well for: - Nearly sorted arrays (very fast) - Small arrays
     */
    public static void insertionSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int key = nums[i];
            int j = i - 1;

            // Shift elements larger than key to the right
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }

            // Place key in correct position
            nums[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        insertionSort(arr);

        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
