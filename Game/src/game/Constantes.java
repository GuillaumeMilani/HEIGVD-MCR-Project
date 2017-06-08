package game;

import javafx.scene.input.KeyCode;

/**
 *
 * @author Gabriel Luthier
 */
public class Constantes {

    public static final String GAME_TITLE = "Visiteur";
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int NUM_COLS = 10;
    public static final double CELL_SIZE = (double) GAME_WIDTH / (double) NUM_COLS;
    public static final double ICON_SIZE = CELL_SIZE / 3;
    public static final int TEMPS_PARTIE_SECONDES = 30;
    public static final int NUM_BONUS = 8;
    public static final int NUM_MALUS = 12;
    public static final int NUM_OBSTACLES = NUM_BONUS + NUM_MALUS;
    public static final String BACKGROUND_PATH = "/resources/images/field.png";
    public static final long GAME_SPEED = 4; // 1 = slow, 10 = fast
    public static final KeyCode KEY_RESTART = KeyCode.R;

    public static enum Bonus {
        Potion("/resources/images/potion.png"),
        Toilette("/resources/images/toilette.png");

        public String imageNomFichier;

        Bonus(String imageNomFichier) {
            this.imageNomFichier = imageNomFichier;
        }
    }

    public static enum Malus {
        Flaque("/resources/images/flaque.png"),
        Voiture("/resources/images/voiture.png");

        public String imageNomFichier;

        Malus(String imageNomFichier) {
            this.imageNomFichier = imageNomFichier;
        }
    }

    public static enum Joueurs {
        Jacquouille("Jacquouille", 100,
                1, 1.5,
                2, "/resources/images/jacquouille.png"),
        Godefroy("Godefroy", 120,
                1.5, 1,
                7, "/resources/images/godefroy.png");

        public String nom;
        public int maxVie;
        public double facteurVie;
        public double facteurScore;
        public int position;
        public String imageNomFichier;

        private Joueurs(String nom, int maxVie,
                double facteurVie, double facteurScore,
                int position, String imageNomFichier) {
            this.nom = nom;
            this.maxVie = maxVie;
            this.facteurVie = facteurVie;
            this.facteurScore = facteurScore;
            this.position = position;
            this.imageNomFichier = imageNomFichier;
        }
    }
}
