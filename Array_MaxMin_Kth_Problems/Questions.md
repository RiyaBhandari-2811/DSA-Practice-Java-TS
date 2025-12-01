# **Array Element Analysis Problems — 52 DSA Questions**

A comprehensive collection of array-based problems covering maximum/minimum retrieval, Kth element queries, heap usage, subarray techniques, edge-case handling, and matrix extensions.

Each problem includes a short definition and one example.

---

# Problems

---

## **1. Largest Element in Array**

Return the largest element in an integer array.

**Example:** `[3, 5, 1, 9, 2] → 9`

---

## **2. Smallest Element in Array**

Return the smallest element in the array.

**Example:** `[3, 5, 1, 9, 2] → 1`

---

## **3. Second Largest Element (Distinct)**

Return the second largest distinct element.

**Example:** `[5, 1, 5, 3] → 3`

---

## **4. Second Smallest Element (Distinct)**

Return the second smallest distinct element.

**Example:** `[4, 2, 2, 7, 3] → 3`

---

## **5. Kth Largest Element**

Return the Kth largest element.

**Example:** `[3,2,1,5,6,4]`, `k=2 → 5`

---

## **6. Kth Smallest Element**

Return the Kth smallest element.

**Example:** `[7,10,4,3,20,15]`, `k=3 → 7`

---

## **7. Largest and Smallest in One Pass**

Find max and min in a single traversal.

**Example:** `[2,8,1,4] → (8,1)`

---

## **8. Two Largest Elements**

Return the two largest distinct elements.

**Example:** `[10, 5, 9, 8] → [10, 9]`

---

## **9. Two Smallest Elements**

Return the two smallest distinct elements.

**Example:** `[6,2,9,1] → [1, 2]`

---

## **10. Maximum Difference (max - min)**

Return `max(nums) - min(nums)`.

**Example:** `[8,1,6,4] → 7`

---

## **11. Largest Element (With Duplicates)**

Return the largest even if duplicates exist.

**Example:** `[5,5,3] → 5`

---

## **12. Second Largest (Distinct, With Duplicates)**

Ignore duplicates of the maximum.

**Example:** `[5,5,3,2] → 3`

---

## **13. Largest & Smallest in Rotated Sorted Array**

Find min and max.

**Example:** `[4,5,6,1,2,3] → (6,1)`

---

## **14. Largest & Smallest in Mountain Array**

Array increases then decreases.

**Example:** `[1,3,5,4,2] → (5,1)`

---

## **15. Largest Element Smaller Than K**

Return largest element `< K`.

**Example:** `[1,4,6,8]`, `K=5 → 4`

---

## **16. Smallest Element Greater Than K**

Return smallest element `> K`.

**Example:** `[2,3,10]`, `K=3 → 10`

---

## **17. Largest Odd & Smallest Even**

Return `(largest odd, smallest even)`.

**Example:** `[2,7,9,4,6] → (9,2)`

---

## **18. Maximum Product Pair**

Return max product from any pair.

**Example:** `[5, -10, -6, 3] → 60`

---

## **19. Minimum Product Pair**

Return smallest product.

**Example:** `[5, -10, -6, 3] → -60`

---

## **20. Maximum & Minimum Sum Pair**

Return `(max pair sum, min pair sum)`.

**Example:** `[1,2,3,4] → (7,3)`

---

## **21. Kth Largest Using Sorting**

Sort and return Kth largest.

**Example:** `[3,2,1,5,6,4]`, `k=2 → 5`

---

## **22. Kth Smallest Using Heap**

Use max-heap of size k.

**Example:** `[7,10,4,3,20]`, `k=4 → 10`

---

## **23. Kth Largest Unique Element**

Return Kth largest distinct value.

**Example:** `[5,5,4,3,2]`, `k=2 → 4`

---

## **24. Top K Largest Elements**

Return list of K largest elements.

**Example:** `[3,2,1,5,6,4]`, `k=3 → [6,5,4]`

---

## **25. Bottom K Smallest Elements**

Return K smallest elements.

**Example:** `[7,10,4,3,20]`, `k=2 → [3,4]`

---

## **26. Kth Largest in Data Stream**

Support add() to maintain Kth largest.

**Example:** `nums=[4,5,8,2], k=3`, add(3) → `4`

---

## **27. Median of Array**

Return median.

**Example:** `[1,3,2] → 2`

---

## **28. Mode + Max + Min**

Return the mode plus largest and smallest.

**Example:** `[1,2,2,3] → mode=2, max=3, min=1`

---

## **29. Sliding Window Maximum**

Return maximum of each subarray of size K.

**Example:** `[1,3,-1,-3,5,3,6,7]`, `k=3 → [3,3,5,5,6,7]`

---

## **30. Sliding Window Minimum**

Return minimum of each subarray of size K.

**Example:** `[2,1,3,4,6,3,8,9]`, `k=4 → [1,1,3,3,3]`

---

## **31. Sum of K Largest Elements**

Return sum of K largest values.

**Example:** `[5,3,1,2,4]`, `k=2 → 9`

---

## **32. Sum of K Smallest Elements**

Return sum of K smallest.

**Example:** `[5,3,1,2,4]`, `k=3 → 6`

---

## **33. Maximum Difference with Order (j > i)**

Return max difference where bigger comes later.

**Example:** `[7,1,5,3,6,4] → 5`

---

## **34. Max & Min in Each Prefix**

For each prefix, return `(max, min)`.

**Example:** `[2,5,1] → [(2,2),(5,2),(5,1)]`

---

## **35. Largest Number Less Than N Using Digits**

Arrange digits of N to form largest number < N.

**Example:** `321 → 312`

---

## **36. Minimum & Maximum Pair Sum**

Return smallest and largest sum from any pair.

**Example:** `[1,2,3,4] → (3,7)`

---

## **37. Second Largest Digit in a Number**

Return second largest distinct digit.

**Example:** `816275 → 7`

---

## **38. Maximum Subarray Sum (Kadane)**

Return maximum contiguous subarray sum.

**Example:** `[-2,1,-3,4,-1,2,1,-5,4] → 6`

---

## **39. Smallest Positive Missing Number**

Return smallest positive integer missing.

**Example:** `[3,4,-1,1] → 2`

---

## **40. Max & Min After One Operation**

If `x` is added to any one element, return resulting max and min.

**Example:** `[1,2,3], x=5 → (8,1)`

---

## **41. Largest in 2D Matrix**

Return maximum element.

**Example:** `[[1,4,3],[9,2,6]] → 9`

---

## **42. Smallest in 2D Matrix**

Return minimum element.

**Example:** `[[1,4,3],[9,2,6]] → 1`

---

## **43. Row with Largest Sum**

Return index of row with maximum sum.

**Example:** `[[1,2],[3,4],[0,9]] → 1`

---

## **44. Column with Smallest Sum**

Return column with minimum sum.

**Example:** `[[1,2],[3,4]] → 0`

---

## **45. Largest in Each Row**

Return largest element from each row.

**Example:** `[[1,4],[3,2]] → [4,3]`

---

## **46. Smallest in Each Column**

Return smallest element from each column.

**Example:** `[[5,2],[3,4]] → [3,2]`

---

## **47. Kth Largest in Sorted Matrix**

Rows sorted ascending.

**Example:** `[[1,5,9],[10,11,13],[12,13,15]]`, `k=8 → 13`

---

## **48. Array of Identical Elements**

Handle cases like second largest etc.

**Example:** `[5,5,5] → no second largest`

---

## **49. Single Element Array**

Edge case for size-1 array.

**Example:** `[7] → largest=7`

---

## **50. Empty Array**

Edge case requiring sentinel or error.

**Example:** `[] → invalid`

---

## **51. Mixed Positive & Negative Numbers**

Ensure correct logic for product or max/min.

**Example:** `[-10,-3,5,6] → max product=30`

---

## **52. Already Sorted Arrays**

Check correctness for sorted arrays.

**Example:** `[1,2,3,4,5] → second largest=4`
