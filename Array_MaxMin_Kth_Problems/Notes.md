
## **Algorithm Learned**

### **1. Partition Scheme** 

**Both schemes are correct** for Quickselect.

The difference lies in pointer movement, complexity of logic, and number of swaps.

#### **Hoare-Style Partition**

* Pivot is chosen at the **start** (`nums[l]`).
* Uses  **two pointers** :
  * `i` moves **left → right**
  * `j` moves **right → left**
* Continues until **`i >= j`**
* Finally swaps the pivot with `j` to place it in its correct position.
* Usually performs  **fewer swaps** , but is trickier to implement correctly.

#### **Lomuto-Style Partition**

* Pivot is chosen at the **end** (`nums[right]`).
* Uses a **single pointer (`storeIndex`)** to maintain boundary of elements `<= pivot`.
* Makes one linear pass through the subarray.
* Swaps the pivot into its correct position at the end.
* Simpler, cleaner, and easier to get right, though may do more swaps.
