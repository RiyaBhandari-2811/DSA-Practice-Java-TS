
public class Sort012 {

    /**
     * Approach 1: Brute force
     *
     * Idea: - Simply use built-in sorting (not optimal but works).
     *
     * Time Complexity: O(n log n) Space Complexity: O(1) or O(n) depending on
     * the sort implementation
     */
    public static void sortBrute(int[] nums) {
        java.util.Arrays.sort(nums);
    }

    /**
     * Approach 2: Better (Counting Sort Idea)
     *
     * Idea: - Count number of 0s, 1s, 2s - Rewrite the array
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     */
    public static void sortCounting(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        for (int v : nums) {
            if (v == 0) {
                count0++; 
            }else if (v == 1) {
                count1++; 
            }else {
                count2++;
            }
        }

        int i = 0;
        while (count0-- > 0) {
            nums[i++] = 0;
        }
        while (count1-- > 0) {
            nums[i++] = 1;
        }
        while (count2-- > 0) {
            nums[i++] = 2;
        }
    }

    /**
     * Approach 3: Optimal (Dutch National Flag Algorithm)
     *
     * Idea: - Use 3 pointers: low, mid, high - nums[0..low-1] = 0 -
     * nums[low..mid-1] = 1 - nums[mid..high] = unknown - nums[high+1..end] = 2
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     */
    public static void sortDNF(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    int temp0 = nums[low];
                    nums[low] = nums[mid];
                    nums[mid] = temp0;
                    low++;
                    mid++;
                    break;

                case 1:
                    mid++;
                    break;

                case 2:
                    int temp2 = nums[mid];
                    nums[mid] = nums[high];
                    nums[high] = temp2;
                    high--;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 0, 2, 1, 1, 0};
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();

        sortBrute(arr1);
        sortCounting(arr2);
        sortDNF(arr3);

        System.out.println(java.util.Arrays.toString(arr1));
        System.out.println(java.util.Arrays.toString(arr2));
        System.out.println(java.util.Arrays.toString(arr3));
    }
}
