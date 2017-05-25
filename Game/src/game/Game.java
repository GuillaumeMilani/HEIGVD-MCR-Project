package game;

import game.visteur.Godefroy;
import game.visteur.Jacquouille;
import game.visteur.Joueur;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Gabriel Luthier
 */
public class Game extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Scene scene = new Scene(root, Constantes.GAME_WIDTH, Constantes.GAME_HEIGHT);

        Canvas canvas = new Canvas(Constantes.GAME_WIDTH, Constantes.GAME_HEIGHT);

        Image background = new Image(
                getClass().getResource(Constantes.BACKGROUND_PATH).toString(),
                Constantes.GAME_WIDTH,
                Constantes.GAME_HEIGHT,
                false, true);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background, 0, 0);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);

        for (int i = 0; i < Constantes.NUM_COLS; i++) {
            gc.strokeLine(i * Constantes.CELL_WIDTH, 0, i * Constantes.CELL_WIDTH, Constantes.GAME_HEIGHT);
        }

        canvas.setFocusTraversable(true);

        Joueur joueur1 = new Jacquouille();
        Joueur joueur2 = new Godefroy();
        
        canvas.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            switch (event.getCode()) {
                case A:
                    joueur1.moveLeft();
                    break;
                case D:
                    joueur1.moveRight();
                    break;
                case LEFT:
                    joueur2.moveLeft();
                    break;
                case RIGHT:
                    joueur2.moveRight();
                    break;
                default:
                    break;
            }
        });
        
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, 200, 300);
        
        root.getChildren().addAll(canvas, joueur1, joueur2);

        primaryStage.setTitle("Visiteur");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            // Stop Clock Thread
            Clock.getInstance().stop();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
