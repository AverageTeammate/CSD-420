/*  Jacob Cannamela
* CSD 420
* 04/12/2025
* This program reads a list of words from a file and displays all non-duplicate words
* in both ascending and descending order.
*/ 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

public class NonDuplicateWordsSorter {
    public static void main(String[] args) {
        // TreeSet stores unique values in sorted (ascending) order
        TreeSet<String> wordsSet = new TreeSet<>();

        try {
            // Open the file for reading
            File file = new File("collection_of_words.txt");
            Scanner input = new Scanner(file);

            // Read each word from the file
            while (input.hasNext()) {
                String word = input.next();
                word = word.replaceAll("[^a-zA-Z]", ""); // remove punctuation to scale this for other word lists
                if (!word.isEmpty()) {
                    wordsSet.add(word.toLowerCase()); // add word in lowercase to avoid duplicates like "Word" vs "word"
                }
            }

            input.close(); // Close the file reader

            // Print in ascending order
            System.out.println("Non-duplicate words in ascending order:");
            for (String word : wordsSet) {
                System.out.println(word);
            }

            // Print in descending order
            System.out.println("\nNon-duplicate words in descending order:");
            Iterator<String> descIterator = wordsSet.descendingIterator();
            while (descIterator.hasNext()) {
                System.out.println(descIterator.next());
            }

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found. Please make sure it exists in the project folder.");
            e.printStackTrace();
        }
    }
}
