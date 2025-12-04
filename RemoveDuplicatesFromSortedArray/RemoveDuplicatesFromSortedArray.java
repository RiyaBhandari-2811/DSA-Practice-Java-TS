import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    /**
     * Brute force removal of duplicates from a sorted array using extra space.
     *
     * Problem:
     * - Given a sorted integer array, remove duplicates such that each unique element
     *   appears only once. The relative order of the elements should be kept the same.
     * - The operation should modify the input array in place for the first "new length"
     *   elements. Elements beyond the returned length are irrelevant.
     *
     * Idea:
     * - Since the array is sorted, duplicates are grouped together.
     * - Create a temporary array of the same size.
     * - Copy the first element as is.
     * - For each subsequent element:
     *     - If it is different from the last unique element placed in temp, append it.
     * - After building the temp array of unique elements, copy them back into the
     *   original array in the first "uniqueCount" positions.
     *
     * Assumptions:
     * - Input array is sorted in non decreasing order.
     * - Null array is considered invalid and results in IllegalArgumentException.
     * - Empty array and single element array are valid inputs.
     *
     * Edge cases:
     * - arr == null -> IllegalArgumentException.
     * - [] (empty array) -> returns 0, array remains unchanged.
     * - [x] -> returns 1, single element is already unique.
     * - All elements equal, for example [2, 2, 2, 2] -> returns 1, arr[0] = 2.
     * - No duplicates, for example [1, 2, 3, 4] -> returns 4, array unchanged.
     * - Duplicates at the beginning, middle, and end are all handled, for example
     *   [1, 1, 2, 3, 3, 4, 4] -> returns 4, arr[0..3] = [1, 2, 3, 4].
     * - Negative values and mix of negative and positive values are fine as long as
     *   the array is sorted, for example [-3, -3, -2, 0, 0, 1].
     *
     * Time Complexity: O(n)
     * - We scan the input array once and fill the temporary array once.
     *
     * Space Complexity: O(n)
     * - Uses an extra array of the same size as the input.
     *
     * Example test cases:
     * - [1, 1, 2] -> returns 2, arr[0..1] = [1, 2]
     * - [0, 0, 1, 1, 1, 2, 2, 3, 3, 4] -> returns 5, arr[0..4] = [0, 1, 2, 3, 4]
     * - [] -> returns 0
     * - [1] -> returns 1
     * - [-3, -3, -2, -2, -2, 0, 1, 1] -> returns 4, arr[0..3] = [-3, -2, 0, 1]
     */
    public static int removeDuplicatesBrute(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non null");
        }

        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] temp = new int[n];
        int uniqueCount = 0;

        // First element is always unique in a non empty sorted array
        temp[uniqueCount++] = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] != temp[uniqueCount - 1]) {
                temp[uniqueCount++] = arr[i];
            }
        }

        // Copy unique elements back to original array
        for (int i = 0; i < uniqueCount; i++) {
            arr[i] = temp[i];
        }

        return uniqueCount;
        }

    /**
     * Optimised in place removal of duplicates from a sorted array using two pointers.
     *
     * Problem:
     * - Given a sorted integer array, remove duplicates such that each unique element
     *   appears only once. The relative order should be preserved.
     * - Modify the array in place and return the number of unique elements.
     * - Only the first returnedLength elements of the array are valid after the call.
     *
     * Idea:
     * - Use two pointers:
     *   - writeIndex: points to the position where the next unique element should be placed.
     *   - readIndex: scans the array from left to right.
     * - Initialize writeIndex to 1 (first element is always unique if array is non empty).
     * - For each readIndex from 1 to n - 1:
     *     - If arr[readIndex] is different from arr[writeIndex - 1], it is a new unique element:
     *       - Assign arr[writeIndex] = arr[readIndex]
     *       - Increment writeIndex
     * - After the loop, writeIndex is the count of unique elements.
     *
     * Assumptions:
     * - Input array is sorted in non decreasing order.
     * - Null array is considered invalid and results in IllegalArgumentException.
     *
     * Edge cases:
     * - arr == null -> IllegalArgumentException.
     * - [] (empty array) -> returns 0, nothing to modify.
     * - [x] -> returns 1, single element is unique.
     * - All elements equal, for example [5, 5, 5] -> returns 1, arr[0] = 5.
     * - No duplicates, for example [1, 2, 3, 4] -> returns 4, array remains as is.
     * - Duplicates spread throughout the array:
     *   [1, 1, 2, 3, 3, 4, 4] -> returns 4, arr[0..3] = [1, 2, 3, 4].
     * - Negative values and mixed sign:
     *   [-3, -3, -2, -2, -2, 0, 1, 1] -> returns 4, arr[0..3] = [-3, -2, 0, 1].
     * - If the array is not sorted, the behavior is not correct for the original problem
     *   definition since the algorithm relies on sorted order to group duplicates.
     *
     * Time Complexity: O(n)
     * - Single pass over the array.
     *
     * Space Complexity: O(1)
     * - No additional data structures proportional to input size.
     *
     * Example test cases:
     * - [1, 1, 2] -> returns 2, arr[0..1] = [1, 2]
     * - [0, 0, 1, 1, 1, 2, 2, 3, 3, 4] -> returns 5, arr[0..4] = [0, 1, 2, 3, 4]
     * - [] -> returns 0
     * - [1] -> returns 1
     * - [1, 2, 3, 4] -> returns 4, arr[0..3] = [1, 2, 3, 4]
     * - [-3, -3, -2, -2, -2, 0, 1, 1] -> returns 4, arr[0..3] = [-3, -2, 0, 1]
     */
    public static int removeDuplicatesOptimised(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non null");
        }

        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int writeIndex = 1; // position for the next unique element

        for (int readIndex = 1; readIndex < n; readIndex++) {
            if (arr[readIndex] != arr[writeIndex - 1]) {
                arr[writeIndex] = arr[readIndex];
                writeIndex++;
            }
        }

        return writeIndex;
    }

    /**
     * Helper method to print the array up to a given length.
     * This is useful for visualizing the result of the removal.
     */
    private static void printArray(int[] arr, int length) {
        System.out.print("[");
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i]);
            if (i < length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 1, 2},
                {0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                {},
                {1},
                {1, 2, 3, 4},
                {2, 2, 2, 2},
                {-3, -3, -2, -2, -2, 0, 1, 1},
                {1, 1},
                {1, 2},
                {-1, -1, -1, -1, -1},
        };

        String[] names = {
                "case1: [1, 1, 2]",
                "case2: [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]",
                "case3: [] (empty)",
                "case4: [1]",
                "case5: [1, 2, 3, 4]",
                "case6: [2, 2, 2, 2]",
                "case7: [-3, -3, -2, -2, -2, 0, 1, 1]",
                "case8: [1, 1]",
                "case9: [1, 2]",
                "case10: [-1, -1, -1, -1, -1]"
        };

        for (int t = 0; t < testCases.length; t++) {
            int[] original = testCases[t];
            int[] arrBrute = Arrays.copyOf(original, original.length);
            int[] arrOptimised = Arrays.copyOf(original, original.length);

            System.out.println("Testing " + names[t]);

            int lenBrute = removeDuplicatesBrute(arrBrute);
            System.out.print("Brute length: " + lenBrute + ", array: ");
            printArray(arrBrute, lenBrute);

            int lenOptimised = removeDuplicatesOptimised(arrOptimised);
            System.out.print("Optimised length: " + lenOptimised + ", array: ");
            printArray(arrOptimised, lenOptimised);

            System.out.println();
        }
    }
}
