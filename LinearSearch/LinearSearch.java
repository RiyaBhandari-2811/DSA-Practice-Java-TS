public class LinearSearch {

    /**
     * Linear search to find the target in the array.
     *
     * Idea:
     * - Traverse the array from left to right.
     * - Compare each element with the target.
     * - Return index when match found.
     * - Return -1 if not found.
     *
     * Edge cases:
     * - null array -> IllegalArgumentException.
     * - empty array -> return -1.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int linearSearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non-null");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1; // not found
    }

    private static void printResult(int[] arr, int target) {
        int index = linearSearch(arr, target);
        System.out.println("Searching for " + target + " -> Index: " + index);
    }

    public static void main(String[] args) {

        int[][] tests = {
            {1, 2, 3, 4, 5},
            {10, 20, 30},
            {},
            {5, 5, 5},
            {-3, -1, 0, 2}
        };

        int[] targets = {4, 25, 3, 5, -1};

        for (int i = 0; i < tests.length; i++) {
            System.out.print("Array: ");
            for (int x : tests[i]) System.out.print(x + " ");
            System.out.println();

            printResult(tests[i], targets[i]);
            System.out.println();
        }
    }
}
