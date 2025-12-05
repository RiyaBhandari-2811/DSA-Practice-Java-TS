
public class MoveZerosToEnd {

    /**
     * Brute force approach to move all zeros to the end of the array.
     *
     * Idea: - Create a temporary array. - Copy all non-zero elements into temp.
     * - Fill remaining positions with zeros. - Copy temp back into original
     * array.
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Edge cases: - null array -> IllegalArgumentException - [] -> do nothing -
     * All zeros -> remains the same - No zeros -> array unchanged - Negative
     * values & duplicates allowed
     */
    public static void moveZerosBrute(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non-null");
        }

        int n = arr.length;
        int[] temp = new int[n];
        int index = 0;

        // copy all non-zero elements to temp
        for (int num : arr) {
            if (num != 0) {
                temp[index++] = num;
            }
        }

        // remaining fill as zeros (already zero by default)
        // copy back
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * Optimised approach using the two-pointer technique.
     *
     * Idea: - Use a pointer "writeIndex" which tracks where the next non-zero
     * should go. - Traverse array: - When arr[i] != 0: - place it at
     * arr[writeIndex] - if i != writeIndex, set arr[i] = 0 - increment
     * writeIndex - This keeps the relative order of non-zero elements.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Edge cases: - null array -> IllegalArgumentException - [] -> nothing to
     * move - [0,0,0] -> stays same - [1,2,3] -> stays same - [0,1,0,3,12] ->
     * becomes [1,3,12,0,0]
     */
    public static void moveZerosOptimised(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non-null");
        }

        int n = arr.length;
        int writeIndex = 0;

        // move non-zero elements to the front
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[writeIndex] = arr[i];
                // if moved, set original location to 0
                if (i != writeIndex) {
                    arr[i] = 0;
                }
                writeIndex++;
            }
        }
    }

    /**
     * Utility to print array
     */
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        int[][] tests = {
            {0, 1, 0, 3, 12},
            {1, 2, 3},
            {0, 0, 0},
            {},
            {-1, 0, -2, 0, 3},
            {5, 5, 5, 0, 0}
        };

        String[] names = {
            "case1 [0,1,0,3,12]",
            "case2 [1,2,3]",
            "case3 [0,0,0]",
            "case4 []",
            "case5 [-1,0,-2,0,3]",
            "case6 [5,5,5,0,0]"
        };

        for (int i = 0; i < tests.length; i++) {

            int[] arrBrute = tests[i].clone();
            int[] arrOpt = tests[i].clone();

            System.out.println("Testing " + names[i]);

            moveZerosBrute(arrBrute);
            System.out.print("Brute result:     ");
            printArray(arrBrute);

            moveZerosOptimised(arrOpt);
            System.out.print("Optimised result: ");
            printArray(arrOpt);

            System.out.println();
        }
    }
}
