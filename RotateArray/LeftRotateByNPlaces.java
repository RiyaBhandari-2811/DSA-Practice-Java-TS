public class LeftRotateByNPlaces {

    /**
     * Brute force left rotation by N places.
     *
     * Idea:
     * - Perform left rotation by 1 place, N times.
     * - Each rotation shifts all elements left once.
     *
     * Time Complexity: O(n * k)
     * - n = array length
     * - k = rotation count
     * - Because each of the k rotations shifts n elements.
     *
     * Space Complexity: O(1)
     * - Only temporary variable used.
     *
     * Edge Cases:
     * - null array -> IllegalArgumentException
     * - [] → do nothing
     * - n = 1 → no rotation
     * - k = 0 → no rotation
     * - k > length → use k = k % length
     */
    public static void leftRotateBrute(int[] arr, int k) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non-null");
        }

        int n = arr.length;
        if (n <= 1) return;

        k = k % n;
        if (k == 0) return;

        for (int r = 0; r < k; r++) {
            int first = arr[0];
            for (int i = 1; i < n; i++) {
                arr[i - 1] = arr[i];
            }
            arr[n - 1] = first;
        }
    }

    /**
     * Optimised left rotation by N places using the Reversal Algorithm.
     *
     * Idea:
     * To rotate array left by k:
     *   1. Reverse first k elements.
     *   2. Reverse remaining (n - k) elements.
     *   3. Reverse the entire array.
     *
     * Example:
     *   arr = [1,2,3,4,5], k = 2
     *   Step1 reverse(0..1) → [2,1,3,4,5]
     *   Step2 reverse(2..4) → [2,1,5,4,3]
     *   Step3 reverse whole → [3,4,5,1,2]
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Edge Cases:
     * - null array -> IllegalArgumentException
     * - [] or [x] → no rotation
     * - k = 0 → no rotation
     * - k > length → use k = k % length
     */
    public static void leftRotateOptimised(int[] arr, int k) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non-null");
        }

        int n = arr.length;
        if (n <= 1) return;

        k = k % n;
        if (k == 0) return;

        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }

    /** Helper: reverse a subarray from l to r */
    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    /** Utility to print array */
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        int[][] testArrays = {
            {1,2,3,4,5},
            {10},
            {},
            {-1,-2,-3,-4,-5},
            {5,5,5,5},
            {1,2}
        };

        int[] rotations = {2, 3, 4, 7, 1, 5};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr1 = testArrays[i].clone();
            int[] arr2 = testArrays[i].clone();
            int k = rotations[i];

            System.out.println("Original:");
            printArray(arr1);
            System.out.println("Rotate by " + k);

            leftRotateBrute(arr1, k);
            System.out.print("Brute Result:     ");
            printArray(arr1);

            leftRotateOptimised(arr2, k);
            System.out.print("Optimised Result: ");
            printArray(arr2);

            System.out.println();
        }
    }
}
