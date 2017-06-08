package game;

import game.element.Obstacle;
import game.element.bonus.Potion;
import game.element.bonus.Toilette;
import game.element.malus.Flaque;
import game.element.malus.Voiture;
import game.visiteur.Godefroy;
import game.visiteur.Jacquouille;
import game.visiteur.Joueur;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel Luthier
 */
public class Game extends Application {

    private Image background;
    private Image icon1;
    private Image icon2;

    private final Font scoreFont = new Font("Verdana", 16);
    private final Font finalFont = new Font("Verdana", 40);

    private Joueur joueur1;
    private Joueur joueur2;
    private List<Obstacle> obstacles;

    private GraphicsContext gc;
    private AnimationTimer timer;
    private boolean gameEnCours = true;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setTitle(Constantes.GAME_TITLE);
        stage.setScene(scene);

        Canvas canvas = new Canvas(Constantes.GAME_WIDTH, Constantes.GAME_HEIGHT);
        canvas.setFocusTraversable(true);

        background = new Image(
                getClass().getResource(Constantes.BACKGROUND_PATH).toString(),
                Constantes.GAME_WIDTH,
                Constantes.GAME_HEIGHT,
                false, true);

        icon1 = new Image(
                getClass().getResource(Constantes.Joueurs.Jacquouille.imageNomFichier).toString(),
                Constantes.ICON_SIZE,
                Constantes.ICON_SIZE,
                false, true);

        icon2 = new Image(
                getClass().getResource(Constantes.Joueurs.Godefroy.imageNomFichier).toString(),
                Constantes.ICON_SIZE,
                Constantes.ICON_SIZE,
                false, true);

        joueur1 = new Jacquouille();
        joueur2 = new Godefroy();

        root.getChildren().addAll(canvas, joueur1, joueur2);

        obstacles = new ArrayList<>(Constantes.NUM_OBSTACLES);

        for (int i = 0; i < Constantes.NUM_BONUS; i++) {
            Obstacle o;
            if (Math.random() < 0.5) {
                o = initialiseObstacle(new Potion());
            } else {
                o = initialiseObstacle(new Toilette());
            }
            obstacles.add(o);
            root.getChildren().add(o);
        }

        for (int i = 0; i < Constantes.NUM_MALUS; i++) {
            Obstacle o;
            if (Math.random() < 0.5) {
                o = initialiseObstacle(new Flaque());
            } else {
                o = initialiseObstacle(new Voiture());
            }
            obstacles.add(o);
            root.getChildren().add(o);
        }

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        drawCanvas();

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (gameEnCours) {
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

                drawCanvas();
                checkCollision();
            }
        });

        final long startNanoTime = System.nanoTime();

        timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                long secondesEcoule = (currentNanoTime - startNanoTime) / 1000000000;

                if (secondesEcoule >= Constantes.TEMPS_PARTIE_SECONDES) {
                    arreterJeu();
                } else {
                    double t = Math.log(1 + (secondesEcoule * 10));

                    obstacles.forEach((o) -> {
                        o.setY(o.getY() + t);

                        if (o.getY() > Constantes.GAME_HEIGHT) {
                            do {
                                o.nouvelleRandomPosition();
                            } while (checkObstacleDejaPresent(o));
                        }
                    });

                    drawCanvas();
                    checkCollision();
                }
            }
        };
        timer.start();

        stage.show();
    }

    public void arreterJeu() {
        timer.stop();
        gameEnCours = false;
        drawCanvas();
        afficherScores();
    }

    public void afficherScores() {
        String gagnant;
        if (!joueur1.estEnVie()) {
            gagnant = joueur2.getNom();
        } else if (!joueur2.estEnVie()) {
            gagnant = joueur1.getNom();
        } else {
            gagnant = joueur1.getScore() >= joueur2.getScore()
                    ? joueur1.getNom() : joueur2.getNom();
        }
        
        gc.setFont(finalFont);
        gc.fillText(gagnant + " a gagné !", (Constantes.GAME_WIDTH / 2) - 200, (Constantes.GAME_HEIGHT / 2) - 20);
        gc.strokeText(gagnant + " a gagné !", (Constantes.GAME_WIDTH / 2) - 200, (Constantes.GAME_HEIGHT / 2) - 20);
    }

    public void drawCanvas() {
        gc.drawImage(background, 0, 0);
        gc.drawImage(icon1, 0, 0);
        gc.drawImage(icon2, 0, Constantes.ICON_SIZE);

        String joueur1Infos = "Vies: " + joueur1.getVie() + ", score: " + joueur1.getScore();
        String joueur2Infos = "Vies: " + joueur2.getVie() + ", score: " + joueur2.getScore();

        gc.setFont(scoreFont);
        gc.fillText(joueur1Infos, Constantes.ICON_SIZE + 5, Constantes.ICON_SIZE - 10);
        gc.fillText(joueur2Infos, Constantes.ICON_SIZE + 5, 2 * Constantes.ICON_SIZE - 10);

        gc.drawImage(joueur1.getImage(), joueur1.getX(), joueur1.getY());
        gc.drawImage(joueur2.getImage(), joueur2.getX(), joueur2.getY());
    }

    public Obstacle initialiseObstacle(Obstacle o) {
        while (checkObstacleDejaPresent(o)) {
            o.nouvelleRandomPosition();
        }

        return o;
    }

    public boolean checkObstacleDejaPresent(Obstacle newO) {
        for (Obstacle o : obstacles) {
            if (!o.equals(newO)
                    && o.getX() == newO.getX()
                    && newO.getY() >= o.getY()
                    && newO.getY() < o.getY() + Constantes.CELL_SIZE) {
                return true;
            }
        }
        return false;
    }

    public void checkCollision() {
        obstacles.forEach((o) -> {
            if (joueur1.getX() == o.getX()
                    && joueur1.getY() >= o.getY()
                    && joueur1.getY() < o.getY() + Constantes.CELL_SIZE) {
                if (!o.aEteVisitePar(joueur1)) {
                    o.setVisite(joueur1);
                    joueur1.visite(o);
                }
            }

            if (joueur2.getX() == o.getX()
                    && joueur2.getY() >= o.getY()
                    && joueur2.getY() < o.getY() + Constantes.CELL_SIZE) {
                if (!o.aEteVisitePar(joueur2)) {
                    o.setVisite(joueur2);
                    joueur2.visite(o);
                }
            }
        });

        if (!joueur1.estEnVie() || !joueur2.estEnVie()) {
            arreterJeu();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
