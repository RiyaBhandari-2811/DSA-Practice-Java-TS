public class SingleElementInSortedArray {

    /**
     * Problem:
     * Find the element that appears only once in a sorted array
     * where every other element appears exactly twice.
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Traverse the array and compare adjacent elements.
     * - If an element is not equal to its neighbor, it is the answer.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int singleElementBrute(int[] arr) {
        int n = arr.length;

        if (n == 1) return arr[0];
        if (arr[0] != arr[1]) return arr[0];
        if (arr[n - 1] != arr[n - 2]) return arr[n - 1];

        for (int i = 1; i < n - 1; i++) {
            if (arr[i] != arr[i - 1] && arr[i] != arr[i + 1]) {
                return arr[i];
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea:
     * - Pairs start at even index before the single element.
     * - After the single element, pairs start at odd index.
     * - Use this property to binary search.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int singleElementOptimal(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // Ensure mid is even
            if (mid % 2 == 1) mid--;

            if (arr[mid] == arr[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }

        return arr[low];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 4, 8, 8};

        System.out.println(singleElementBrute(arr));
        System.out.println(singleElementOptimal(arr));
    }
}
