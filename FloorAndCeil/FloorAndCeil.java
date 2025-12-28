
public class FloorAndCeil {

    /**
     * Problem: Find floor and ceil of a given number x in a sorted array.
     *
     * Floor: largest number <= x
     * Ceil: smallest number >= x
     */
    /**
     * Approach 1: Brute Force
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int[] floorAndCeilBrute(int[] arr, int x) {
        int floor = -1;
        int ceil = -1;

        for (int num : arr) {
            if (num <= x) {
                floor = num;
            }
            if (num >= x) {
                ceil = num;
                break;
            }
        }

        return new int[]{floor, ceil};
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea: - Use binary search to find floor and ceil. - Update floor when
     * arr[mid] <= x.
     * - Update ceil when arr[mid] >= x.
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int[] floorAndCeilOptimal(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int floor = -1, ceil = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                return new int[]{arr[mid], arr[mid]};
            } else if (arr[mid] < x) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }

        return new int[]{floor, ceil};
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 7, 8, 8, 10};
        int x = 5;

        int[] result1 = floorAndCeilBrute(arr, x);
        System.out.println("Brute -> Floor: " + result1[0] + ", Ceil: " + result1[1]);

        int[] result2 = floorAndCeilOptimal(arr, x);
        System.out.println("Optimal -> Floor: " + result2[0] + ", Ceil: " + result2[1]);
    }
}
