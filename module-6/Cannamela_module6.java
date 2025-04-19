/*
 * Jacob Cannamela
 * CSD420 - Bellevue University
 * Date: 04/19/2025
 * 
 * BubbleSortGenerics.java
 * 
 * This program demonstrates two generic implementations of the bubble sort algorithm.
 * - One method uses the Comparable interface
 * - The other uses a Comparator interface
 *
 * Both methods can be used to sort arrays of custom objects or standard Java types.
 * I also included test cases with fun and weird variable names to keep things spicy.
 */

 import java.util.Arrays;
 import java.util.Comparator;
 
 public class Cannamela_module6 {
 
     /**
     * Bubble sort using the Comparable interface.
     * Elements must implement Comparable.
     */
    public static <T extends Comparable<T>> void bubbleSortWithComparable(T[] array) {
        for (int pass = 0; pass < array.length - 1; pass++) {
            for (int i = 0; i < array.length - 1 - pass; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    System.out.println("Swap made using Comparable");
                }
            }
        }
    }

     //Bubble sort using a custom Comparator.
     // This allows sorting objects without them needing to implement Comparable.
    public static <T> void bubbleSortWithComparator(T[] array, Comparator<T> comparator) {
        for (int round = 0; round < array.length - 1; round++) {
            for (int j = 0; j < array.length - 1 - round; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    System.out.println("Swap made using Comparator");
                }
            }
        }
    }

    
    // Utility method to print an array with a label.
    public static <T> void printArray(T[] array, String label) {
        System.out.println(label + ": " + Arrays.toString(array));
    }

    public static void main(String[] args) {
        // Test case 1: Integers using Comparable
        Integer[] highScores = {58, 99, 12, 34, 71};
        System.out.println("Sorting Integer array (Comparable):");
        printArray(highScores, "Before");
        bubbleSortWithComparable(highScores);
        printArray(highScores, "After");

        // Test case 2: Strings using Comparable
        String[] snacks = {"Chips", "Apple", "Banana", "Donut", "Carrot"};
        System.out.println("\nSorting String array (Comparable):");
        printArray(snacks, "Before");
        bubbleSortWithComparable(snacks);
        printArray(snacks, "After");

        // Test case 3: Custom Student objects using Comparator
        Student[] roster = {
            new Student("Miles", 85),
            new Student("Jade", 93),
            new Student("Benny", 76),
            new Student("Lara", 61)
        };

        System.out.println("\nSorting Student objects by score (Comparator):");
        printArray(roster, "Before");
        bubbleSortWithComparator(roster, Comparator.comparing(Student::getScore));
        printArray(roster, "After");
    }

    
    // Simple Student class with name and score.
    static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return name + "(" + score + ")";
        }
    }
}