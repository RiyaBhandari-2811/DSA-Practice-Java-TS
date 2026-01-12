public class MedianOfTwoSortedArrays {

    /**
     * Problem:
     * Find the median of two sorted arrays of different sizes.
     */

    /**
     * Approach 1: Brute Force (Merge Arrays)
     *
     * Idea:
     * - Merge both arrays into one sorted array
     * - Find the median from merged array
     *
     * Time Complexity: O(m + n)
     * Space Complexity: O(m + n)
     *
     * Stable: Yes
     */
    public static double findMedianBrute(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < m) merged[k++] = arr1[i++];
        while (j < n) merged[k++] = arr2[j++];

        int len = m + n;

        if (len % 2 == 1) {
            return merged[len / 2];
        } else {
            return (merged[len / 2 - 1] + merged[len / 2]) / 2.0;
        }
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea:
     * - Perform binary search on the smaller array
     * - Partition both arrays such that:
     *   left part size = right part size
     * - Ensure left elements <= right elements
     *
     * Time Complexity: O(log(min(m, n)))
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static double findMedianOptimal(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) {
            return findMedianOptimal(arr2, arr1);
        }

        int m = arr1.length;
        int n = arr2.length;
        int low = 0, high = m;

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r1 = (cut1 == m) ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = (cut2 == n) ? Integer.MAX_VALUE : arr2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return 0.0;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3};
        int[] arr2 = {2};

        System.out.println(findMedianBrute(arr1, arr2));
        System.out.println(findMedianOptimal(arr1, arr2));
    }
}
