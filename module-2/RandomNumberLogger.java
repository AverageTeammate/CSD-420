/**
 * Jacob Cannamela - Module 2 Assignment - CSD-420 Advanced Java
 * 3/29/2025
 */

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class RandomNumberLogger {

    public static void main(String[] args) {
        // File name to write to (this file will be created if it doesn't exist)
        String fileName = "cannamela_mod2.dat";

        // We use this to generate random numbers
        Random rand = new Random();

        // Create two arrays for storing numbers
        int[] intValues = new int[5];
        double[] doubleValues = new double[5];

        // Fill the arrays with random values
        for (int i = 0; i < 5; i++) {
            intValues[i] = rand.nextInt(100); // Random integer from 0 to 99
            doubleValues[i] = rand.nextDouble() * 100; // Random double between 0.0 and 100.0
        }

        // Now we try to write to the file
        try {
            // 'true' means we're adding to the file instead of erasing it
            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter writer = new PrintWriter(fw);

            // Write the integer values
            writer.println("Integer values:");
            for (int i = 0; i < intValues.length; i++) {
                writer.print(intValues[i] + " ");
            }
            writer.println(); // move to new line

            // Write the double values
            writer.println("Double values:");
            for (int i = 0; i < doubleValues.length; i++) {
                writer.print(doubleValues[i] + " ");
            }
            writer.println(); // new line

            // Add a separator for readability
            writer.println("----------------------------");

            // Close writer to save the data
            writer.close();

            System.out.println("Data has been written to the file successfully.");
        } catch (IOException e) {
            System.out.println("There was a problem writing to the file.");
        }
    }
}
