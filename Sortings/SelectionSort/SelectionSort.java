
public class SelectionSort {

    /**
     * SELECTION SORT
     *
     * Idea: - For each position i, find the minimum element in the unsorted
     * part (i..n-1) - Swap it with nums[i]
     *
     * Time Complexity: O(n^2) Space Complexity: O(1) Stable: No (because of
     * swaps)
     */
    public static void selectionSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap nums[i] and nums[minIndex]
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {64, 25, 12, 22, 11};

        selectionSort(arr1);

        System.out.println("Selection Sort Result:");
        for (int x : arr1) {
            System.out.print(x + " ");
        }
    }
}
