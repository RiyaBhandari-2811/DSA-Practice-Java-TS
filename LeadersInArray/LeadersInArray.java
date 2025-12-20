
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeadersInArray {

    /**
     * Problem: Given an array of integers, find all leaders in the array.
     *
     * Definition: An element is called a leader if it is greater than or equal
     * to all the elements to its right side.
     *
     * Note: - The rightmost element is always a leader.
     *
     * Example: Input: [16, 17, 4, 3, 5, 2] Output: [17, 5, 2]
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - For each element, check all elements to its right. - If no
     * element on the right is greater, it is a leader.
     *
     * Time Complexity: O(n^2) Space Complexity: O(n)
     *
     * Stable: Yes - Leaders are added in the same order as input.
     */
    public static List<Integer> leadersBrute(int[] arr) {
        List<Integer> leaders = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    isLeader = false;
                    break;
                }
            }

            if (isLeader) {
                leaders.add(arr[i]);
            }
        }

        return leaders;
    }

    /**
     * Approach 2: Better (Right Maximum Array)
     *
     * Idea: - Create an array rightMax where rightMax[i] stores the maximum
     * element to the right of index i. - An element is a leader if arr[i] >=
     * rightMax[i].
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static List<Integer> leadersBetter(int[] arr) {
        int n = arr.length;
        int[] rightMax = new int[n];
        List<Integer> leaders = new ArrayList<>();

        rightMax[n - 1] = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(arr[i + 1], rightMax[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] >= rightMax[i]) {
                leaders.add(arr[i]);
            }
        }

        return leaders;
    }

    /**
     * Approach 3: Optimal
     *
     * Idea: - Traverse the array from right to left. - Maintain maxSoFar. - If
     * current element >= maxSoFar, it is a leader. - Reverse the result to
     * maintain original order.
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static List<Integer> leadersOptimal(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int maxSoFar = Integer.MIN_VALUE;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] >= maxSoFar) {
                leaders.add(arr[i]);
                maxSoFar = arr[i];
            }
        }

        Collections.reverse(leaders);
        return leaders;
    }

    public static void main(String[] args) {
        int[] arr = {16, 17, 4, 3, 5, 2};

        System.out.println(leadersBrute(arr));
        System.out.println(leadersBetter(arr));
        System.out.println(leadersOptimal(arr));
    }
}
