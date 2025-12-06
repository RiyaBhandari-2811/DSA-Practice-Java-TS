import java.util.*;

/**
 * Utility class for finding the union of two integer arrays.
 */
public class FindTheUnion {

    /**
     * Brute force union of two arrays using a HashSet.
     *
     * Problem:
     * - Given two arrays (not necessarily sorted), return the union of both:
     *   all distinct elements that appear in either array.
     *
     * Idea:
     * - Use a HashSet to store unique elements from both arrays.
     * - Then convert the set to a list or array.
     * - Optionally sort the result to return in increasing order.
     *
     * Assumptions:
     * - Arrays may be unsorted.
     * - Arrays can contain duplicates, negative values, zero.
     *
     * Edge cases:
     * - arr1 == null or arr2 == null -> IllegalArgumentException.
     * - arr1 empty, arr2 non-empty -> union is all distinct of arr2.
     * - arr2 empty, arr1 non-empty -> union is all distinct of arr1.
     * - both empty -> union is empty.
     *
     * Time Complexity: O((n + m) * log(n + m))
     * - Inserting into HashSet is average O(1),
     *   but sorting the final result is O(k log k), where k <= n + m.
     *
     * Space Complexity: O(n + m)
     * - HashSet and result list store up to all elements.
     *
     * @param arr1 first input array (can be unsorted)
     * @param arr2 second input array (can be unsorted)
     * @return int[] containing the sorted union of distinct elements
     */
    public static int[] findUnionBrute(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Input arrays must be non-null");
        }

        Set<Integer> set = new HashSet<>();

        for (int num : arr1) {
            set.add(num);
        }
        for (int num : arr2) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        int idx = 0;
        for (int value : set) {
            result[idx++] = value;
        }

        Arrays.sort(result); // optional: return in sorted order
        return result;
    }

    /**
     * Optimised union for two sorted arrays using two pointers.
     *
     * Problem:
     * - Given two sorted arrays (non decreasing), return the union as a sorted
     *   list of distinct elements.
     *
     * Idea:
     * - Use two indices i and j to traverse arr1 and arr2.
     * - At each step:
     *   - Compare arr1[i] and arr2[j].
     *   - Add the smaller one to result if it is not a duplicate of the last added.
     *   - Move the corresponding pointer.
     * - If they are equal, add once and move both pointers.
     * - After one array ends, append remaining values from the other array,
     *   skipping duplicates.
     *
     * Assumptions:
     * - Both arrays are sorted in non decreasing order.
     *
     * Edge cases:
     * - arr1 == null or arr2 == null -> IllegalArgumentException.
     * - One or both arrays can be empty.
     * - Arrays can contain duplicates.
     *
     * Time Complexity: O(n + m)
     * - Each element of both arrays is visited at most once.
     *
     * Space Complexity: O(n + m)
     * - Result list can store up to all distinct elements from both arrays.
     *
     * @param arr1 first sorted array
     * @param arr2 second sorted array
     * @return int[] union of both sorted arrays, with distinct elements
     */
    public static int[] findUnionOptimised(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Input arrays must be non-null");
        }

        int n = arr1.length;
        int m = arr2.length;

        int i = 0;
        int j = 0;

        List<Integer> result = new ArrayList<>();

        while (i < n && j < m) {
            int a = arr1[i];
            int b = arr2[j];

            if (a == b) {
                // add if result is empty or last element is different
                if (result.isEmpty() || result.get(result.size() - 1) != a) {
                    result.add(a);
                }
                i++;
                j++;
            } else if (a < b) {
                if (result.isEmpty() || result.get(result.size() - 1) != a) {
                    result.add(a);
                }
                i++;
            } else { // a > b
                if (result.isEmpty() || result.get(result.size() - 1) != b) {
                    result.add(b);
                }
                j++;
            }
        }

        // remaining elements of arr1
        while (i < n) {
            int a = arr1[i];
            if (result.isEmpty() || result.get(result.size() - 1) != a) {
                result.add(a);
            }
            i++;
        }

        // remaining elements of arr2
        while (j < m) {
            int b = arr2[j];
            if (result.isEmpty() || result.get(result.size() - 1) != b) {
                result.add(b);
            }
            j++;
        }

        // convert to array
        int[] union = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            union[k] = result.get(k);
        }

        return union;
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k]);
            if (k < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 2, 3, 5};
        int[] arr2 = {2, 3, 4, 4, 6};
        int[] arr3 = {};
        int[] arr4 = {10, 10, 10};
        int[] arr5 = {-3, -1, 0, 2};
        int[] arr6 = {-2, -1, 0, 3, 3};

        int[][] aList = {arr1, arr3, arr5};
        int[][] bList = {arr2, arr4, arr6};
        String[] names = {
            "case1 arr1 & arr2 (mixed duplicates, positive)",
            "case2 arr3 & arr4 (empty + all equal)",
            "case3 arr5 & arr6 (negatives and positives)"
        };

        for (int t = 0; t < aList.length; t++) {
            int[] a = aList[t];
            int[] b = bList[t];

            System.out.println("Testing " + names[t]);
            System.out.print("arr1: ");
            printArray(a);
            System.out.print("arr2: ");
            printArray(b);

            int[] brute = findUnionBrute(a, b);
            int[] opt = findUnionOptimised(a, b);

            System.out.print("Brute union:     ");
            printArray(brute);

            System.out.print("Optimised union: ");
            printArray(opt);

            System.out.println();
        }
    }
}
