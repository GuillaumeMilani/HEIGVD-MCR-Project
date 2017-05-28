package game;

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

    public static enum Bonus {
        Potion(10, 100, "/resources/images/potion.png"),
        Toilette(30, 200, "/resources/images/toilette.png");

        public int soins;
        public int points;
        public String imageNomFichier;

        private Bonus(int soins, int points, String imageNomFichier) {
            this.soins = soins;
            this.points = points;
            this.imageNomFichier = imageNomFichier;
        }
    }

    public static enum Malus {
        Flaque(-10, -80, "/resources/images/flaque.png"),
        Voiture(-40, -150, "/resources/images/voiture.png");

        public int degats;
        public int points;
        public String imageNomFichier;

        private Malus(int degats, int points, String imageNomFichier) {
            this.degats = degats;
            this.points = points;
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
