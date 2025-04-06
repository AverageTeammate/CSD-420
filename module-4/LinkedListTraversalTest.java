/*
 * Name: Jacob Cannamela
 * Class: CSD 420
 * Date: April 6, 2025
 * Description: This program demonstrates the performance difference between traversing a LinkedList
 * using an iterator vs. using get(index). It compares both methods across 50,000 and 500,000 entries.
 * Notes included at the end explain why traversal time varies significantly.
 */

 import java.util.LinkedList;
 import java.util.Iterator;
 
 public class LinkedListTraversalTest {
 
     public static void main(String[] args) {
         // Run both tests with 50,000 and 500,000 integers
         runTest(50000);
         runTest(500000);
     }
 
     public static void runTest(int numberOfElements) {
         // Announce test size
         System.out.println("\n--- Running test with " + numberOfElements + " elements ---");
 
         // Step 1: Fill the LinkedList with sequential integers
         LinkedList<Integer> linkedList = new LinkedList<>();
         for (int i = 0; i < numberOfElements; i++) {
             linkedList.add(i);
         }
 
         // Step 2: Measure traversal using an iterator
         long startTimeIterator = System.currentTimeMillis();
         Iterator<Integer> iter = linkedList.iterator();
         while (iter.hasNext()) {
             Integer value = iter.next();
         }
         long endTimeIterator = System.currentTimeMillis();
 
         // Step 3: Measure traversal using get(index)
         long startTimeIndexAccess = System.currentTimeMillis();
         for (int i = 0; i < linkedList.size(); i++) {
             Integer value = linkedList.get(i);
         }
         long endTimeIndexAccess = System.currentTimeMillis();
 
         // Step 4: Print results
         System.out.println("Iterator traversal time: " + (endTimeIterator - startTimeIterator) + " ms");
         System.out.println("get(index) traversal time: " + (endTimeIndexAccess - startTimeIndexAccess) + " ms");
     }
 }
 /*
 * Explanation of Results:
 * When traversing the LinkedList using an iterator, the time is significantly lower compared to using get(index).
 * This happens because:
 *
 * - Iterator maintains a reference to the next node, moving through the list in O(1) time per step.
 * - The get(index) method, on the other hand, starts from the beginning each time and moves forward index-by-index,
 *   which results in O(n) time for each access — and O(n^2) total time for the full traversal.
 *
 * With 50,000 elements, you may see a noticeable difference — maybe several milliseconds.
 * With 500,000 elements, the difference becomes dramatic — in this example it was about a 2 minute difference for get(index) traversal.
 * This demonstrates that LinkedList is great for forward traversal using iterators,
 * but inefficient when random access by index is needed.
 */
