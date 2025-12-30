
public class MinInRotatedSortedArray {

    /**
     * Problem: Find the minimum element in a rotated sorted array (no
     * duplicates).
     */
    /**
     * Approach: Binary Search (Optimal)
     *
     * Idea: - One half of the array is always sorted. - The minimum lies in the
     * unsorted half. - Compare mid with high to decide direction.
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int findMin(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[high]) {
                // Minimum lies in right half
                low = mid + 1;
            } else {
                // Minimum lies in left half (including mid)
                high = mid;
            }
        }

        return arr[low];
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin(arr)); // 0
    }
}
