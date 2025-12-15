
import java.util.ArrayList;
import java.util.List;

public class RearrangeArrayAlternateSigns {

    /**
     * BRUTE FORCE APPROACH
     *
     * Idea: - Separate positives and negatives into two lists - Place them
     * alternately back into array
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Stable: Yes (relative order preserved)
     *
     * How it works: - First collect positives and negatives - Then merge them
     * in alternating fashion
     */
    public static void bruteForce(int[] arr) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int num : arr) {
            if (num >= 0) {
                pos.add(num); 
            }else {
                neg.add(num);
            }
        }

        int i = 0, p = 0, n = 0;
        while (p < pos.size() && n < neg.size()) {
            arr[i++] = pos.get(p++);
            arr[i++] = neg.get(n++);
        }

        while (p < pos.size()) {
            arr[i++] = pos.get(p++);
        }
        while (n < neg.size()) {
            arr[i++] = neg.get(n++);
        }

        print(arr);
    }

    /**
     * BETTER APPROACH
     *
     * Idea: - Same logic as brute but using result array
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Stable: Yes
     *
     * How it works: - Fill positives at even index - Negatives at odd index
     */
    public static void betterApproach(int[] arr) {
        int[] res = new int[arr.length];
        int posIdx = 0, negIdx = 1;

        for (int num : arr) {
            if (num >= 0 && posIdx < arr.length) {
                res[posIdx] = num;
                posIdx += 2;
            } else if (num < 0 && negIdx < arr.length) {
                res[negIdx] = num;
                negIdx += 2;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = res[i];
        }
        print(arr);
    }

    /**
     * OPTIMAL APPROACH (When count of positives == negatives)
     *
     * Idea: - Use two pointers - Swap elements in place
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: No
     *
     * How it works: - Place positives at even index - Negatives at odd index by
     * swapping
     */
    public static void optimal(int[] arr) {
        int pos = 0, neg = 1;
        int n = arr.length;

        while (pos < n && neg < n) {
            if (arr[pos] >= 0) {
                pos += 2;
            } else if (arr[neg] < 0) {
                neg += 2;
            } else {
                int temp = arr[pos];
                arr[pos] = arr[neg];
                arr[neg] = temp;
            }
        }

        print(arr);
    }

    private static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, -2, -5, 2, -4};
        bruteForce(arr.clone());
        betterApproach(arr.clone());
        optimal(arr.clone());
    }
}
