
public class BubbleSort {

    /**
     * BUBBLE SORT
     *
     * Idea: - Repeatedly compare adjacent elements - Swap if out of order -
     * After each pass, the largest element moves to its correct position
     *
     * Optimization: - If no swaps occur in a pass, array is already sorted →
     * break early
     *
     * Time Complexity: O(n^2) Space Complexity: O(1) Stable: Yes
     */
    public static void bubbleSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // Swap
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;

                    swapped = true;
                }
            }

            // No swaps means array already sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr2 = {5, 1, 4, 2, 8};

        bubbleSort(arr2);

        System.out.println("\nBubble Sort Result:");
        for (int x : arr2) {
            System.out.print(x + " ");
        }
    }
}
