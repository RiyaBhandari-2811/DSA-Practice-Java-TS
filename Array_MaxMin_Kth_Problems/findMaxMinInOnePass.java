/**
 * findMaxMinInOnePass.java
 *
 * This class demonstrates two approaches to find the minimum and maximum
 * values in an array:
 *
 * 1. Brute Force Approach (Sorting)
 *    - Definition: Sort the array in ascending order and return the first element
 *      as the minimum and the last element as the maximum.
 *    - Time Complexity: O(n log n)
 *    - Space Complexity: O(1) auxiliary (or O(log n) because of sorting stack)
 *
 * 2. Single Pass Approach (Optimized)
 *    - Definition: Traverse the array once while maintaining two variables (min, max).
 *      Update them whenever a smaller or larger element is found.
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(1)
 */

public class findMaxMinInOnePass {

    /**
     * Brute Force Approach:
     * Sort the array and return the minimum and maximum.
     *
     * @param arr input array
     * @return array of size 2 → [min, max]
     * @throws IllegalArgumentException if array is empty
     */
    public static int[] bruteForce(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        // Sorting the array
        java.util.Arrays.sort(arr);

        int min = arr[0];
        int max = arr[arr.length - 1];

        return new int[]{min, max};
    }

    /**
     * Single Pass Approach:
     * Traverse the array once and keep track of min and max.
     *
     * @param arr input array
     * @return array of size 2 → [min, max]
     * @throws IllegalArgumentException if array is empty
     */
    public static int[] singlePass(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];

            if (current < min) {
                min = current;
            }

            if (current > max) {
                max = current;
            }
        }

        return new int[]{min, max};
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, -2, 7, 7};

        int[] a = bruteForce(arr.clone());
        System.out.println("Brute Force -> Min: " + a[0] + " Max: " + a[1]);

        int[] b = singlePass(arr.clone());
        System.out.println("Single Pass  -> Min: " + b[0] + " Max: " + b[1]);
    }
}
