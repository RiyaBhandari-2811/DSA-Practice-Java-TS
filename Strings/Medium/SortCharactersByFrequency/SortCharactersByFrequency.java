
import java.util.*;

/**
 * Problem: Sort characters in a string by descending frequency. If two
 * characters have the same frequency, any order is acceptable.
 */
public class SortCharactersByFrequency {

    /* ============================================================
       Approach 1: Pure Brute Force
       ============================================================ */
    /**
     * Idea: - Count frequency using HashMap - Repeatedly scan the map to find
     * the max frequency character - Append it and remove it from the map
     *
     * Time Complexity: - Frequency count: O(n) - Finding max k times: O(k^2) -
     * Total: O(n + k^2)
     *
     * Space Complexity: O(k)
     *
     * Stable: No
     */
    public static String frequencySortBrute(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        StringBuilder result = new StringBuilder();

        while (!freq.isEmpty()) {
            char maxChar = 0;
            int maxFreq = 0;

            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                if (entry.getValue() > maxFreq) {
                    maxFreq = entry.getValue();
                    maxChar = entry.getKey();
                }
            }

            for (int i = 0; i < maxFreq; i++) {
                result.append(maxChar);
            }

            freq.remove(maxChar);
        }

        return result.toString();
    }

    /* ============================================================
       Approach 2: Sorting Map Entries (Better)
       ============================================================ */
    /**
     * Idea: - Count frequency using HashMap - Convert map entries to list -
     * Sort list by value descending - Build result string
     *
     * Time Complexity: - Frequency count: O(n) - Sorting: O(k log k)
     *
     * Space Complexity: O(k)
     *
     * Stable: No
     */
    public static String frequencySortBySorting(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> entries
                = new ArrayList<>(freq.entrySet());

        entries.sort((a, b) -> b.getValue() - a.getValue());

        StringBuilder result = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : entries) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        }

        return result.toString();
    }

    /* ============================================================
       Approach 3: Max Heap (PriorityQueue)
       ============================================================ */
    /**
     * Idea: - Count frequency - Push characters into max heap based on
     * frequency - Pop from heap and build result
     *
     * Time Complexity: - Heap build: O(k) - Heap operations: O(k log k)
     *
     * Space Complexity: O(k)
     *
     * Stable: No
     */
    public static String frequencySortHeap(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap
                = new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));

        maxHeap.addAll(freq.keySet());

        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            int count = freq.get(ch);

            for (int i = 0; i < count; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }

    /* ============================================================
       Approach 4: Bucket Sort (Optimal)
       ============================================================ */
    /**
     * Idea: - Max frequency is at most n - Create buckets where index
     * represents frequency - Place characters into buckets - Traverse buckets
     * from high to low
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Stable: No
     */
    public static String frequencySortBucket(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        List<Character>[] buckets = new List[s.length() + 1];

        for (char ch : freq.keySet()) {
            int f = freq.get(ch);
            if (buckets[f] == null) {
                buckets[f] = new ArrayList<>();
            }
            buckets[f].add(ch);
        }

        StringBuilder result = new StringBuilder();

        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char ch : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        result.append(ch);
                    }
                }
            }
        }

        return result.toString();
    }

    /* ============================================================
       Main
       ============================================================ */
    public static void main(String[] args) {
        String s = "tree";

        System.out.println(frequencySortBrute(s));
        System.out.println(frequencySortBySorting(s));
        System.out.println(frequencySortHeap(s));
        System.out.println(frequencySortBucket(s));

        System.out.println(frequencySortBucket("Aabb"));
        System.out.println(frequencySortBucket("cccaaa"));
    }
}
