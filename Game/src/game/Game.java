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
 * Classe principale de l'application du jeu
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public class Game extends Application {

    private Image imgArrirePlan;   // Image de fond du jeu
    private Image imgRegle;      // Image d'accueil
    private Image imgIcon1;        // Image du premier joueur
    private Image imgIcone2;        // Image du deuxième joueur
    /*
     * Police de l'affichage du score
     */
    private final Font scorePolice = new Font("Verdana", 16);
    private final Font finalPolice = new Font("Verdana", 40);

    private Joueur joueur1;             // Le joueur (visiteur) numéro 1 du jeu
    private Joueur joueur2;             // Le joueur numéro 2 (visiteur) du jeu

    private List<Obstacle> obstacles;

    private boolean jeuEnCours = true; // Booleéen pour savoir si le jeu est en cours
    private boolean pause = false;      // Dis si le jeu est en pause

    private Text scoreJoueur1;
    private Text scoreJoueur2;
    private Text tempsRestant;

    private Group racine;
    private Group groupeObstacles;

    private Timeline deplacement;
    private Timer creationTimer;

    /**
     * Surcharge de la méthode starte de la classe Application pour débuter la partie
     *
     * @param stage Stage où la fenêtre va s'afficher
     */
    @Override
    public void start(Stage stage) {
        pause = true; // met le jeu en pause au début
        jeuEnCours = true;
        racine = new Group();
        groupeObstacles = new Group();

        Scene scene = new Scene(racine);

        stage.setTitle(Constantes.JEU_TITRE);
        stage.setScene(scene);

        joueur1 = new Jacquouille();
        joueur2 = new Godefroy();

        imgArrirePlan = new Image(
                getClass().getResource(Constantes.ARRIERE_PLAN_CHEMIN).toString(),
                Constantes.JEU_LARGEUR,
                Constantes.JEU_HAUTEUR,
                true, true);

        imgRegle = new Image(
                getClass().getResource(Constantes.REGLE_CHEMIN).toString(),
                Constantes.JEU_LARGEUR,
                Constantes.JEU_HAUTEUR,
                false, true);

        imgIcon1 = new Image(
                getClass().getResource(Constantes.Joueurs.Jacquouille.imageNomFichier).toString(),
                Constantes.ICON_TAILLE,
                Constantes.ICON_TAILLE,
                false, true);

        imgIcone2 = new Image(
                getClass().getResource(Constantes.Joueurs.Godefroy.imageNomFichier).toString(),
                Constantes.ICON_TAILLE,
                Constantes.ICON_TAILLE,
                false, true);

        ImageView backgroundImage = new ImageView(imgArrirePlan);
        backgroundImage.setFocusTraversable(true);
        backgroundImage.setX(0);
        backgroundImage.setY(0);
        backgroundImage.setFitHeight(Constantes.JEU_HAUTEUR);
        backgroundImage.setFitWidth(Constantes.JEU_LARGEUR);

        ImageView icon1view = new ImageView(imgIcon1);
        icon1view.setX(0);
        icon1view.setY(0);
        ImageView icon2view = new ImageView(imgIcone2);
        icon2view.setX(0);
        icon2view.setY(Constantes.ICON_TAILLE);

        scoreJoueur1 = new Text();
        scoreJoueur2 = new Text();

        scoreJoueur1.setFont(scorePolice);
        scoreJoueur2.setFont(scoreJoueur1.getFont());

        scoreJoueur1.setFill(Color.WHITE);
        scoreJoueur2.setFill(scoreJoueur1.getFill());

        scoreJoueur1.setX(Constantes.ICON_TAILLE + 5);
        scoreJoueur1.setY(Constantes.ICON_TAILLE - 10);
        scoreJoueur2.setX(Constantes.ICON_TAILLE + 5);
        scoreJoueur2.setY(2 * Constantes.ICON_TAILLE - 10);

        tempsRestant = new Text();
        tempsRestant.setText(String.valueOf(Constantes.TEMPS_PARTIE_SECONDES));
        tempsRestant.setX(Constantes.JEU_LARGEUR - 50);
        tempsRestant.setY(30);
        tempsRestant.setFill(scoreJoueur1.getFill());
        tempsRestant.setFont(new Font(30));

        afficherScore();

        ImageView welcomeImage = new ImageView(imgRegle);
        welcomeImage.setFocusTraversable(true);
        welcomeImage.setX(0);
        welcomeImage.setY(0);
        welcomeImage.setFitHeight(Constantes.JEU_HAUTEUR);
        welcomeImage.setFitWidth(Constantes.JEU_LARGEUR);

        afficherScore();

        racine.getChildren().addAll(backgroundImage, joueur1, joueur2, groupeObstacles, icon1view, icon2view, scoreJoueur1, scoreJoueur2, tempsRestant, welcomeImage);

        obstacles = new ArrayList<>(Constantes.NBR_OBSTACLES);

        Random random = new Random();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (jeuEnCours) {
                switch (event.getCode()) {
                    case A:
                        joueur1.deplaceGauche();
                        break;
                    case D:
                        joueur1.deplaceDroite();
                        break;
                    case LEFT:
                        joueur2.deplaceGauche();
                        break;
                    case RIGHT:
                        joueur2.deplaceDroite();
                        break;
                    case SPACE:
                        if (pause) {
                            pause = false;
                            racine.getChildren().remove(welcomeImage);
                            final long heureDebut = System.currentTimeMillis();

                            deplacement = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                                long secondesEcoulees = (System.currentTimeMillis() - heureDebut) / 1000;
                                tempsRestant.setText(String.valueOf(Constantes.TEMPS_PARTIE_SECONDES - secondesEcoulees));
                                if (secondesEcoulees >= Constantes.TEMPS_PARTIE_SECONDES) {
                                    arreterJeu();
                                } else if (!pause) {
                                    double t = Constantes.JEU_VITESSE - 2 + Math.log(1 + (secondesEcoulees * 10));

                                    Iterator<Obstacle> it = obstacles.iterator();

                                    while (it.hasNext()) {
                                        Obstacle o = it.next();
                                        o.setY(o.getY() + t);
                                        if (o.getY() > Constantes.JEU_HAUTEUR) {
                                            it.remove();
                                            Platform.runLater(() -> groupeObstacles.getChildren().remove(o));
                                        }
                                    }

                                    testCollisions();
                                }
                            }));
                            deplacement.setCycleCount(Timeline.INDEFINITE);
                            creationTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Platform.runLater(() -> {
                                        int nbObstacles = random.nextInt(10);
                                        for (int i = 0; i < nbObstacles; i++) {
                                            createRandomObstacle(random);
                                        }
                                    });
                                }
                            }, 0, 1000);
                            deplacement.play();
                        }
                    default:
                        break;
                }

                testCollisions();
            } else {
                if (event.getCode() == Constantes.RESTART_TOUCHE) {
                    relancer(stage);
                }
            }
        });

        creationTimer = new Timer();

        stage.show();
    }

    private void createRandomObstacle(Random random) {
        Obstacle o;
        switch (random.nextInt(10)) {
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
            case 7:
                o = new Flaque();
                break;
            case 9:
                o = new Voiture();
                break;
            default:
                o = new Toilette();
                break;
        }
        obstacles.add(o);
        groupeObstacles.getChildren().add(o);
    }

    private void relancer(Stage stage) {
        stage.close();
        start(stage);
    }

    /**
     * Méthode pour intérrompre le jeu
     */
    private void arreterJeu() {
        deplacement.stop();
        creationTimer.cancel();
        jeuEnCours = false;
        afficherScores();
    }

    /**
     * Méthode qui s'occupe d'afficher le score
     */
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
        Text texteRecommencer = new Text();
        textScore.setText(gagnant + " a gagné !");
        texteRecommencer.setText("Presser R pour recommencer");
        textScore.setFill(Color.WHITE);
        textScore.setFont(finalPolice);
        textScore.setX((Constantes.JEU_LARGEUR / 2) - textScore.getBoundsInLocal().getWidth()/2);
        textScore.setY((Constantes.JEU_HAUTEUR / 2) - 20);
        texteRecommencer.setFill(Color.WHITE);
        texteRecommencer.setFont(finalPolice);
        texteRecommencer.setX(Constantes.JEU_LARGEUR/2 - texteRecommencer.getBoundsInLocal().getWidth()/2);
        texteRecommencer.setY((Constantes.JEU_HAUTEUR / 2) + 20);

        racine.getChildren().addAll(textScore, texteRecommencer);
    }

    /**
     * Méthode pour afficher le score du jeu
     */
    private void afficherScore() {
        String joueur1Infos = "Vies: " + joueur1.getVie() + ", score: " + joueur1.getScore();
        String joueur2Infos = "Vies: " + joueur2.getVie() + ", score: " + joueur2.getScore();

        scoreJoueur1.setText(joueur1Infos);
        scoreJoueur2.setText(joueur2Infos);
    }

    private void initialiseObstacle(Obstacle o) {
        o.nouvelleRandomPosition();
    }

    /**
     * Vérifie la "collision" du joueur avec un des obstacle
     */
    private void testCollisions() {
        obstacles.forEach((ob) -> {
            testCollisions(ob, joueur1);
            testCollisions(ob, joueur2);
        });

        if (!joueur1.estEnVie() || !joueur2.estEnVie()) {
            arreterJeu();
        }
    }

    private void testCollisions(Obstacle obstacle, Joueur joueur) {
        if (joueur.getX() == obstacle.getX()
                && joueur.getY() >= obstacle.getY()
                && joueur.getY() < obstacle.getY() + Constantes.CELLULE_TAILLE && !obstacle.aEteVisitePar(joueur)) {
            obstacle.ajouteVisite(joueur);
            obstacle.accepte(joueur);
            FadeTransition ft = new FadeTransition();
            ft.setNode(obstacle);
            ft.setDuration(new Duration(200));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.play();
            afficherScore();
        }
    }

    /**
     * Fonction main du programme
     *
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
