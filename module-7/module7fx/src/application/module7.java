/*
 * Jacob Cannamela
 * CSD420
 * 4/25/2025
 * Module 7 - JavaFX Circle Layout
 */

package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class module7 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // White circle inside the black vertical box
        Circle c1 = new Circle(40);
        c1.getStyleClass().add("plaincircle");

        // Other circles aligned horizontally
        Circle c2 = new Circle(40);
        c2.getStyleClass().add("plaincircle");

        // Create the red circle
        Circle c3 = new Circle(40);
        c3.setId("redcircle");

        // Create the green circle
        Circle c4 = new Circle(40);
        c4.setId("greencircle");

        // VBox for black border box with ONE white circle inside
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(c1);

        // Create the tall black rectangle using a StackPane, center the VBox with one white circle inside, and apply the border style
        StackPane blackBox = new StackPane();
        blackBox.setPrefSize(100, 200);
        blackBox.setAlignment(Pos.CENTER);
        blackBox.getChildren().add(vBox);
        blackBox.getStyleClass().add("border");

        // HBox for remaining horizontally aligned circles
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(20));
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(blackBox, c2, c3, c4);

        // Set up the scene
        Scene scene = new Scene(hBox, 450, 200);
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());

        // Setting the title
        primaryStage.setTitle("Module 7 Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
