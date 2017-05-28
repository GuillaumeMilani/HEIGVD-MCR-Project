package game;

import game.visteur.Godefroy;
import game.visteur.Jacquouille;
import game.visteur.Joueur;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
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

    private Image background;
    private Joueur joueur1;
    private Joueur joueur2;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setTitle("Visiteur");
        stage.setScene(scene);

        Canvas canvas = new Canvas(Constantes.GAME_WIDTH, Constantes.GAME_HEIGHT);
        canvas.setFocusTraversable(true);

        background = new Image(
                getClass().getResource(Constantes.BACKGROUND_PATH).toString(),
                Constantes.GAME_WIDTH,
                Constantes.GAME_HEIGHT,
                false, true);

        joueur1 = new Jacquouille();
        joueur2 = new Godefroy();
        
        root.getChildren().addAll(canvas, joueur1, joueur2);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawCanvas(gc);

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
            drawCanvas(gc);
        });

        stage.setOnCloseRequest((WindowEvent event) -> {
            // Stop Clock Thread
            Clock.getInstance().stop();
        });

        stage.show();
    }

    public void drawCanvas(GraphicsContext gc) {
        gc.drawImage(background, 0, 0);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);

        for (int i = 0; i < Constantes.NUM_COLS; i++) {
            gc.strokeLine(i * Constantes.CELL_WIDTH, 0, i * Constantes.CELL_WIDTH, Constantes.GAME_HEIGHT);
        }
        
        gc.drawImage(joueur1.getImage(), joueur1.getX(), joueur1.getY());
        gc.drawImage(joueur2.getImage(), joueur2.getX(), joueur2.getY());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
