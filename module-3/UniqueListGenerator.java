/*
 * Name: Jacob Cannamela
 * Class: CSD 420
 * Date: April 6, 2025
 * Description: This Java program demonstrates how to generate a list of 50 random integers
 * between 1 and 20, and remove duplicates using a generic method. I added output to show
 * how many duplicates were removed and the size of each list.
 */

 import java.util.ArrayList;
 import java.util.Random;
 
 public class UniqueListGenerator {
 
     public static void main(String[] args) {
         // Step 1: Create a list of random integers from 1 to 20
         ArrayList<Integer> randomNumbers = new ArrayList<>();
         Random generator = new Random();
 
         for (int i = 0; i < 50; i++) {
             int num = generator.nextInt(20) + 1; // Generate number from 1 to 20
             randomNumbers.add(num);
         }
 
         // Display the full list with potential duplicates
         System.out.println("Generated List (may include duplicates):");
         System.out.println(randomNumbers);
 
         // Step 2: Remove duplicates using the method defined below
         ArrayList<Integer> uniqueNumbers = getUniqueItems(randomNumbers);
 
         // Step 3: Display the cleaned-up list
         System.out.println("\nFiltered List (duplicates removed):");
         System.out.println(uniqueNumbers);
 
         // Step 4: Show summary
         System.out.println("\nSummary:");
         System.out.println("Original list size: " + randomNumbers.size());
         System.out.println("Unique list size: " + uniqueNumbers.size());
         System.out.println("Duplicates removed: " + (randomNumbers.size() - uniqueNumbers.size()));
     }
 
     /**
      * Generic method that removes duplicate elements from an ArrayList.
      * @param list An ArrayList of any data type.
      * @param <T> A generic type.
      * @return A new ArrayList containing only unique values.
      */
     public static <T> ArrayList<T> getUniqueItems(ArrayList<T> list) {
         ArrayList<T> cleanedList = new ArrayList<>();
 
         for (int i = 0; i < list.size(); i++) {
             T currentItem = list.get(i);
             if (!cleanedList.contains(currentItem)) {
                 cleanedList.add(currentItem);
             }
         }
 
         return cleanedList;
     }
 }
 