
public class LowerBoundBS {

    /**
     * Problem: Find the smallest index such that arr[index] >= x. If not found,
     * return n.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Traverse the array from left to right. - Return the first index
     * where arr[i] >= x.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int lowerBoundBrute(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= x) {
                return i;
            }
        }
        return arr.length;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea: - Since array is sorted, apply binary search. - Maintain answer
     * when arr[mid] >= x.
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int lowerBoundOptimal(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};

        System.out.println(lowerBoundBrute(arr, 5));
        System.out.println(lowerBoundOptimal(arr, 5));

        System.out.println(lowerBoundBrute(arr, 7));
        System.out.println(lowerBoundOptimal(arr, 7));
    }
}
