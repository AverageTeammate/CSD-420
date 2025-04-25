// Jacob Cannamela
// CSD420
// 4/25/2025
// ThreeThreads assignment

public class JacobThreeThreads {

    // Set how many characters we want to print per thread
    private static final int COUNT = 10000;

    public static void main(String[] args) {
        // Thread for spamming random lowercase letters
        Thread lettersThread = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                char letter = (char) ('a' + (int)(Math.random() * 26));
                System.out.print(letter);
            }
        });

        // Thread for spamming random digits 
        Thread digitsThread = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                char digit = (char) ('0' + (int)(Math.random() * 10));
                System.out.print(digit);
            }
        });

        // Thread for throwing special characters everywhere 
        Thread specialCharThread = new Thread(() -> {
            char[] specialChars = {'!', '@', '#', '$', '%', '&', '*'};
            for (int i = 0; i < COUNT; i++) {
                char special = specialChars[(int)(Math.random() * specialChars.length)];
                System.out.print(special);
            }
        });

        // Start all three threads 
        lettersThread.start();
        digitsThread.start();
        specialCharThread.start();

        // Wait for the madness to finish before exiting
        try {
            lettersThread.join();
            digitsThread.join();
            specialCharThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace(); // Error handling for thread interruption
        }

        // Self explanatory message
        // to show that all threads have completed
        System.out.println("\nAll threads have completed. You survived the storm of characters!");

    }
}
