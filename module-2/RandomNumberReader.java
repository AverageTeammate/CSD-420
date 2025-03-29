/**
 * Jacob Cannamela - Module 2 Assignment - CSD-420 Advanced Java
 * 3/29/2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomNumberReader {

    public static void main(String[] args) {
        // File name to read from 
        String fileName = "cannamela_mod2.dat";

        // Try to open the file and read its contents
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            System.out.println("Reading contents of the file:\n");

            // Keep printing lines until the file ends
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }

            reader.close(); // Closeing the file reader
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found. Make sure it exists first.");
        }
    }
}
