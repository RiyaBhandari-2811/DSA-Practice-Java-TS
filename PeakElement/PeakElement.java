
public class PeakElement {

    /**
     * Problem: Find the index of a peak element in the array. A peak element is
     * greater than its neighbors.
     *
     * Note: arr[-1] and arr[n] are treated as -infinity.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Check each element. - If it is greater than both neighbors,
     * return its index.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int findPeakBrute(int[] arr) {
        int n = arr.length;

        if (n == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }

        for (int i = 1; i < n - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea: - If arr[mid] > arr[mid + 1], peak lies on the left. - Else, peak
     * lies on the right. - Works even for increasing, decreasing, or mixed
     * arrays.
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int findPeakOptimal(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[mid + 1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1};

        System.out.println(findPeakBrute(arr));
        System.out.println(findPeakOptimal(arr));
    }
}
