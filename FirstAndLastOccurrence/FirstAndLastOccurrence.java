
public class FirstAndLastOccurrence {

    /**
     * Problem: Find first and last occurrence of a target in a sorted array.
     */
    /**
     * Approach 1: Brute Force
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int[] firstLastBrute(int[] arr, int x) {
        int first = -1;
        int last = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }

        return new int[]{first, last};
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea: - Use binary search twice - First search finds first occurrence -
     * Second search finds last occurrence
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int[] firstLastOptimal(int[] arr, int x) {
        int first = findFirst(arr, x);
        int last = findLast(arr, x);
        return new int[]{first, last};
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

        int[] result = firstLastOptimal(arr, 2);
        System.out.println("First: " + result[0] + ", Last: " + result[1]);
    }
}
