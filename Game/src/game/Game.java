package game;

import game.element.Obstacle;
import game.element.bonus.Pain;
import game.element.bonus.Potion;
import game.element.bonus.Salade;
import game.element.bonus.Sandwich;
import game.element.bonus.Toilette;
import game.element.bonus.Tomate;
import game.element.bonus.Viande;
import game.element.malus.Flaque;
import game.element.malus.Voiture;
import game.visiteur.Godefroy;
import game.visiteur.Jacquouille;
import game.visiteur.Joueur;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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


    private AnimationTimer timer;
    private boolean gameEnCours = true;

    private Text scoreJoueur1;
    private Text scoreJoueur2;
    private Text tempsRestant;

    private Group root;
    private Group groupeObstacles;

    private Timeline deplacement;
    Timer creationTimer;

    @Override
    public void start(Stage stage) {
        root = new Group();
        groupeObstacles = new Group();

        Scene scene = new Scene(root);

        stage.setTitle(Constantes.GAME_TITLE);
        stage.setScene(scene);

        joueur1 = new Jacquouille();
        joueur2 = new Godefroy();

        background = new Image(
                getClass().getResource(Constantes.BACKGROUND_PATH).toString(),
                Constantes.GAME_WIDTH,
                Constantes.GAME_HEIGHT,
                true, true);

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

        ImageView backgroundImage = new ImageView(background);
        backgroundImage.setFocusTraversable(true);
        backgroundImage.setX(0);
        backgroundImage.setY(0);
        backgroundImage.setFitHeight(Constantes.GAME_HEIGHT);
        backgroundImage.setFitWidth(Constantes.GAME_WIDTH);

        ImageView icon1view = new ImageView(icon1);
        icon1view.setX(0);
        icon1view.setY(0);
        ImageView icon2view = new ImageView(icon2);
        icon2view.setX(0);
        icon2view.setY(Constantes.ICON_SIZE);

        scoreJoueur1 = new Text();
        scoreJoueur2 = new Text();

        scoreJoueur1.setFont(scoreFont);
        scoreJoueur2.setFont(scoreJoueur1.getFont());

        scoreJoueur1.setFill(Color.WHITE);
        scoreJoueur2.setFill(scoreJoueur1.getFill());

        scoreJoueur1.setX(Constantes.ICON_SIZE + 5);
        scoreJoueur1.setY(Constantes.ICON_SIZE - 10);
        scoreJoueur2.setX(Constantes.ICON_SIZE + 5);
        scoreJoueur2.setY(2 * Constantes.ICON_SIZE - 10);

        tempsRestant = new Text();
        tempsRestant.setText(String.valueOf(Constantes.TEMPS_PARTIE_SECONDES));
        tempsRestant.setX(Constantes.GAME_WIDTH - 50);
        tempsRestant.setY(30);
        tempsRestant.setFill(scoreJoueur1.getFill());
        tempsRestant.setFont(new Font(30));

        drawScore();

        root.getChildren().addAll(backgroundImage, joueur1, joueur2, groupeObstacles, icon1view, icon2view, scoreJoueur1, scoreJoueur2, tempsRestant);

        obstacles = new ArrayList<>(Constantes.NUM_OBSTACLES);

        Random random = new Random();

        for (int i = 0; i < Constantes.NUM_BONUS; i++) {
            createRandomBonus(random);
        }

        for (int i = 0; i < Constantes.NUM_MALUS; i++) {
            createRandomMalus(random);
        }

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
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

                checkCollision();
            } else {
                if (event.getCode() == Constantes.KEY_RESTART) {
                    restart(stage);
                }
            }
        });

        final long heureDebut = System.currentTimeMillis();

        deplacement = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            long secondesEcoulees = (System.currentTimeMillis() - heureDebut) / 1000;
            tempsRestant.setText(String.valueOf(Constantes.TEMPS_PARTIE_SECONDES - secondesEcoulees));
            if (secondesEcoulees >= Constantes.TEMPS_PARTIE_SECONDES) {
                arreterJeu();
            } else {
                double t = 5 + Math.log(1 + (secondesEcoulees * 10));

                Iterator<Obstacle> it = obstacles.iterator();

                while (it.hasNext()) {
                    Obstacle o = it.next();
                    o.setY(o.getY() + t);
                    if (o.getY() > Constantes.GAME_HEIGHT) {
                        it.remove();
                        Platform.runLater(() -> groupeObstacles.getChildren().remove(o));
                    }
                }

                checkCollision();
            }
        }));
        deplacement.setCycleCount(Timeline.INDEFINITE);
        deplacement.play();

        creationTimer = new Timer();
        creationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    int nbObstacles = random.nextInt(10);
                    for (int i = 0; i < nbObstacles; i++) {
                        if (random.nextBoolean()) {
                            createRandomBonus(random);
                        } else {
                            createRandomMalus(random);
                        }
                    }
                });
            }
        }, 0, 1000);

        stage.show();
    }

    public void createRandomBonus(Random random) {
        Obstacle o;
        switch (random.nextInt(7)) {
            case 0:
                o = new Potion();
                break;
            case 1:
                o = new Toilette();
                break;
            case 2:
                o = new Sandwich();
                break;
            case 3:
                o = new Pain();
                break;
            case 4:
                o = new Salade();
                break;
            case 5:
                o = new Tomate();
                break;
            case 6:
                o = new Viande();
                break;
            default:
                o = new Toilette();
                break;
        }
        obstacles.add(o);
        groupeObstacles.getChildren().add(o);
    }

    private void createRandomMalus(Random random) {
        Obstacle o;
        if (Math.random() < 0.5) {
            o = initialiseObstacle(new Flaque());
        } else {
            o = initialiseObstacle(new Voiture());
        }
        obstacles.add(o);
        groupeObstacles.getChildren().add(o);
    }

    public void restart(Stage stage) {
        stage.close();
        gameEnCours = true;
        start(stage);
    }

    public void arreterJeu() {
        deplacement.stop();
        creationTimer.cancel();
        gameEnCours = false;
        afficherScores();
    }

    private void afficherScores() {
        String gagnant;
        if (!joueur1.estEnVie()) {
            gagnant = joueur2.getNom();
        } else if (!joueur2.estEnVie()) {
            gagnant = joueur1.getNom();
        } else {
            gagnant = joueur1.getScore() >= joueur2.getScore()
                    ? joueur1.getNom() : joueur2.getNom();
        }
        Text textScore = new Text();
        textScore.setText(gagnant + " a gagné !");
        textScore.setFill(Color.WHITE);
        textScore.setFont(finalFont);
        textScore.setX((Constantes.GAME_WIDTH / 2) - 200);
        textScore.setY((Constantes.GAME_HEIGHT / 2) - 20);
        root.getChildren().add(textScore);
    }

    private void drawScore() {
        String joueur1Infos = "Vies: " + joueur1.getVie() + ", score: " + joueur1.getScore();
        String joueur2Infos = "Vies: " + joueur2.getVie() + ", score: " + joueur2.getScore();

        scoreJoueur1.setText(joueur1Infos);
        scoreJoueur2.setText(joueur2Infos);
    }

    private Obstacle initialiseObstacle(Obstacle o) {
        while (checkObstacleDejaPresent(o)) {
            o.nouvelleRandomPosition();
        }

        return o;
    }

    private boolean checkObstacleDejaPresent(Obstacle newO) {
        for (Obstacle ob : obstacles) {
            if (!ob.equals(newO)
                    && ob.getX() == newO.getX()
                    && newO.getY() >= ob.getY()
                    && newO.getY() < ob.getY() + Constantes.CELL_SIZE) {
                return true;
            }
        }
        return false;
    }

    private void checkCollision() {
        obstacles.forEach((ob) -> {
            checkCollision(ob, joueur1);
            checkCollision(ob, joueur2);
        });

        if (!joueur1.estEnVie() || !joueur2.estEnVie()) {
            arreterJeu();
        }
    }

    private void checkCollision(Obstacle obstacle, Joueur joueur) {
        if (joueur.getX() == obstacle.getX()
                && joueur.getY() >= obstacle.getY()
                && joueur.getY() < obstacle.getY() + Constantes.CELL_SIZE && !obstacle.aEteVisitePar(joueur)) {
            obstacle.setVisite(joueur);
            obstacle.accepte(joueur);
            FadeTransition ft = new FadeTransition();
            ft.setNode(obstacle);
            ft.setDuration(new Duration(200));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.play();
            drawScore();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        deplacement.stop();
        creationTimer.cancel();
    }
}
