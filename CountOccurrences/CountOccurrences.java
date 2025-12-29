public class CountOccurrences {

    /**
     * Problem:
     * Count the number of times a given number appears in a sorted array.
     */

    /**
     * Approach 1: Brute Force
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int countBrute(int[] arr, int x) {
        int count = 0;

        for (int num : arr) {
            if (num == x) count++;
        }

        return count;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea:
     * - Find first occurrence of x
     * - Find last occurrence of x
     * - Count = last - first + 1
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int countOptimal(int[] arr, int x) {
        int first = findFirst(arr, x);
        if (first == -1) return 0;

        int last = findLast(arr, x);
        return last - first + 1;
    }

    private static int findFirst(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                ans = mid;
                high = mid - 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private static int findLast(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                ans = mid;
                low = mid + 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4};

        System.out.println(countBrute(arr, 2));
        System.out.println(countOptimal(arr, 2));
    }
}
