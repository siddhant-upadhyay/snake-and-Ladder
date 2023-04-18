package com.example.snakeladderapril;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SnakeLadder extends Application {

    // create the size of the individual block of the board
    public static final int tileSize = 40, width = 10, height = 10;
    public static final int buttonLine = height * tileSize + 50, infoLine = buttonLine - 30;

    private static Dice dice = new Dice();
    private Player playerOne1, playerTwo2;

    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;

    // create out own method
    private Pane createContent() throws FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(  width *tileSize, height *tileSize+ 100);
        // creation of the whole grid
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // create the tile
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                // single block of tile is created
                root.getChildren().add(tile);
            }
        }
        // add images to get perfect fit
        FileInputStream file = new FileInputStream("C:\\Users\\KIIT\\IdeaProjects\\SnakeLadder-april\\src\\main\\istockphoto-531466314-1024x1024.jpg");
        Image img = new Image(file);
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height * tileSize);
        board.setFitWidth(width * tileSize);


        // add button to the board
        Button playerOneb = new Button("Player one");
        Button playerTwob = new Button("Player Two");
        Button startb = new Button("Start");

        playerOneb.setTranslateY(buttonLine);
        playerOneb.setTranslateX(20);
        playerOneb.setDisable(true);
        playerTwob.setTranslateY(buttonLine);
        playerTwob.setTranslateX(300);
        playerTwob.setDisable(true);
        startb.setTranslateY(buttonLine);
        startb.setTranslateX(160);

        // info dispaly
        Label playerOnel = new Label("");
        Label playerTwol = new Label("");
        Label diecel = new Label("Start The Game");

        playerOnel.setTranslateY(infoLine);
        playerOnel.setTranslateX(20);
        playerTwol.setTranslateY(infoLine);
        playerTwol.setTranslateX(280);
        diecel.setTranslateY(infoLine);
        diecel.setTranslateX(160);

        playerOne1 = new Player(tileSize, Color.BLACK, "sid");
        playerTwo2 = new Player(tileSize - 5, Color.WHITE, "bhanu");

        playerOneb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted) {
                    if (playerOneTurn) {

                        int diceValue = dice.getRolledDiceValue();
                        diecel.setText("Diece value: " + diceValue);
                        playerOne1.movePlayer(diceValue);
                        // winning condition
                        if (playerOne1.isWinner()) {
                            diecel.setText("Winner is " + playerOne1.getName());
                            playerOneTurn = false;
                            playerOneb.setDisable(true);
                            playerOnel.setText("");
                            playerTwoTurn = true;
                            playerTwob.setDisable(true);
                            playerTwol.setText("");

                            startb.setDisable(false);
                            startb.setText("Restart Game");
                            gameStarted = false;
                        } else {
                            playerOneTurn = false;
                            playerOneb.setDisable(true);
                            playerOnel.setText("");
                            playerTwoTurn = true;
                            playerTwob.setDisable(false);
                            playerTwol.setText("Your Turn " + playerTwo2.getName());
                        }
                    }
                }
            }
        });

        playerTwob.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted) {
                    if (playerTwoTurn) {

                        int diceValue = dice.getRolledDiceValue();
                        diecel.setText("Diece value :" + diceValue);
                        playerTwo2.movePlayer(diceValue);
                        // winning condition
                        if (playerTwo2.isWinner()) {
                            diecel.setText("Winner is " + playerTwo2.getName());
                            playerOneTurn = false;
                            playerOneb.setDisable(true);
                            playerOnel.setText("");
                            playerTwoTurn = true;
                            playerTwob.setDisable(false);
                            playerTwol.setText("");

                            startb.setDisable(false);
                            startb.setText("Restart Game");
                        } else {
                            playerOneTurn = true;
                            playerOneb.setDisable(false);
                            playerOnel.setText("Your Turn " + playerOne1.getName());

                            playerTwoTurn = false;
                            playerTwob.setDisable(true);
                            playerTwol.setText("");
                        }
                    }
                }
            }
        });
        startb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diecel.setText("Game Started");
                startb.setDisable(true);
                playerOneTurn = true;
                playerOnel.setText("Your Turn " + playerOne1.getName());
                playerOneb.setDisable(false);
                playerOne1.startingPosition();
                playerTwoTurn = false;
                playerTwol.setText("");
                playerTwob.setDisable(true);
                playerTwo2.startingPosition();

            }
        });

        root.getChildren().addAll(board, playerOneb, playerTwob,
                startb, playerOnel, playerTwol, diecel,
                playerOne1.getCoin(), playerTwo2.getCoin());

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch();
    }
}