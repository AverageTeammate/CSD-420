package module1FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.util.*;

/**
 * This JavaFX program displays 4 random playing cards from a deck of 52 card images.
 * Users can click a Refresh button to draw new cards,
 * an Exit button to close the app,
 * and a "Re-roll Until 4 of a Kind" button that keeps drawing until all 4 cards share the same rank.
 *
 * Jacob Cannamela - Module 1 Assignment - CSD-420 Advanced Java
 */
public class Main extends Application {

    // How many cards will be displayed
    private static final int NUM_CARDS = 4;

    // The folder path where the card images are stored (relative to the project root)
    private static final String CARD_PATH = "cards/";

    // There are 52 cards total in a standard deck
    private static final int TOTAL_CARDS = 52;

    // This list holds the image views that will display the cards
    private final ArrayList<ImageView> cardViews = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create a horizontal container for the cards
        HBox cardBox = new HBox(20); // spacing between cards
        cardBox.setPadding(new Insets(20)); // add space around the cards
        cardBox.setAlignment(Pos.CENTER); // center the cards horizontally

        // Pick 4 random cards to start
        ArrayList<Integer> cards = getRandomCards();

        // Create image holders for each card and add them to the cardBox
        for (int i = 0; i < NUM_CARDS; i++) {
            ImageView cardView = new ImageView();
            cardView.setFitWidth(120);   // width of each card image
            cardView.setFitHeight(180);  // height of each card image
            cardViews.add(cardView);
            cardBox.getChildren().add(cardView);
        }

        // Display the starting hand of cards
        updateCards(cards);

        // Create the Refresh button
        Button refreshButton = new Button("🔄 Refresh");
        refreshButton.setStyle(buttonStyle("#4CAF50"));
        // When clicked, this button loads a new random hand
        refreshButton.setOnAction(e -> updateCards(getRandomCards()));

        // Create the Exit button to close the app
        Button exitButton = new Button("❌ Exit");
        exitButton.setStyle(buttonStyle("#d9534f"));
        exitButton.setOnAction(e -> primaryStage.close());

        // Create the "Re-roll Until 4 of a Kind" button
        Button rerollButton = new Button("🎲 Re-roll Until 4 of a Kind");
        rerollButton.setStyle(buttonStyle("#2196F3"));

        // When clicked, this button keeps redrawing cards until all 4 are the same rank
        rerollButton.setOnAction(e -> {
            int attempts = 0;
            ArrayList<Integer> current;

            // Loop until we get a 4-of-a-kind
            do {
                current = getRandomCards();
                attempts++;
            } while (!isFourOfAKind(current));

            // Show the winning hand
            updateCards(current);

            // Update the button to show how many rolls it took
            rerollButton.setText("🎉 4 of a Kind in " + attempts + " rolls!");
        });

        // Layout for the buttons
        HBox buttonBox = new HBox(20, refreshButton, rerollButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER); // center the buttons
        buttonBox.setPadding(new Insets(10)); // space around buttons

        // Stack the card area and the buttons vertically
        VBox mainLayout = new VBox(20, cardBox, buttonBox);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        // Create the window (scene)
        Scene scene = new Scene(mainLayout, 900, 600);
        primaryStage.setTitle("Random Card Drawer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method updates the card images being displayed.
     * It loads new images into the ImageViews based on the provided card numbers.
     */
    private void updateCards(ArrayList<Integer> cards) {
        for (int i = 0; i < NUM_CARDS; i++) {
            String imagePath = CARD_PATH + cards.get(i) + ".png";
            File imageFile = new File(imagePath);

            // Convert the image file path into a format JavaFX can use
            Image image = new Image(imageFile.toURI().toString());
            cardViews.get(i).setImage(image);
        }
    }

    /**
     * This method returns a list of 4 unique card numbers from 1 to 52.
     * It simulates shuffling a full deck and taking the top 4.
     */
    private ArrayList<Integer> getRandomCards() {
        ArrayList<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= TOTAL_CARDS; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck); // shuffle the deck randomly
        return new ArrayList<>(deck.subList(0, NUM_CARDS)); // take the top 4
    }

    /**
     * Checks if all 4 cards in the list share the same rank (i.e., are 4 of a kind).
     * Ranks go from 1 (Ace) to 13 (King), regardless of suit.
     */
    private boolean isFourOfAKind(ArrayList<Integer> cards) {
        HashMap<Integer, Integer> rankCounts = new HashMap<>();

        for (int card : cards) {
            int rank = (card - 1) % 13 + 1; // Normalize card number to 1–13
            rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
        }

        // If any rank appears 4 times, it's a 4-of-a-kind
        for (int count : rankCounts.values()) {
            if (count == 4) return true;
        }

        return false;
    }

    /**
     * This helper method returns a string of CSS styling to apply to buttons.
     * You can pass a color hex code to set the background color.
     */
    private String buttonStyle(String backgroundColor) {
        return "-fx-font-size: 16px;" +
               "-fx-background-color: " + backgroundColor + ";" +
               "-fx-text-fill: white;" +
               "-fx-background-radius: 8;" +
               "-fx-cursor: hand;";
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
