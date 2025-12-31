public class RotationCount {

    /**
     * Problem:
     * Find how many times a sorted array has been rotated.
     * (Index of minimum element)
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Find the minimum element.
     * - Return its index.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int rotationCountBrute(int[] arr) {
        int min = arr[0];
        int index = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }

        return index;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea:
     * - Minimum element index = number of rotations.
     * - Use binary search to locate the minimum element.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int rotationCountOptimal(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(rotationCountBrute(arr));
        System.out.println(rotationCountOptimal(arr));
    }
}
