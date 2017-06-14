package game;

import javafx.scene.input.KeyCode;

/**
 * Classe contenatn la plupart des constantes à utiliser dans le programme
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public class Constantes {
    
    public static final String GAME_TITLE = "Visiteur"; // Nom du jeu
    public static final int GAME_WIDTH = 800;   // Largeur de la fenêtre de jeu
    public static final int GAME_HEIGHT = 600;  // Hauteur de la fenêtre de jeu
    public static final int NUM_COLS = 10;      // Nombre de colones pour les obstacles
    // Taille d'une céllule pour un objet
    public static final double CELL_SIZE = (double) GAME_WIDTH / (double) NUM_COLS;
    // Taille des images des obstacles
    public static final double ICON_SIZE = CELL_SIZE / 3;
    public static final int TEMPS_PARTIE_SECONDES = 30; // Temps durée de la partie
    public static final int NUM_BONUS = 8;              // Quantité de bonus
    public static final int NUM_MALUS = 12;             // Quantité de malus
    // Quantité totale d'obstacles
    public static final int NUM_OBSTACLES = NUM_BONUS + NUM_MALUS;
    // Chemin vers l'image de fons du jeu
    public static final String BACKGROUND_PATH = "/resources/images/field.png";
    public static final long GAME_SPEED = 5; // 1 = slow, 10 = fast
    public static final KeyCode KEY_RESTART = KeyCode.R;
    public static final String WELCOME_PATH = "/resources/images/regles.jpg";


    /**
     * Enum qui possède les Bonus du jeu
     */
    public static enum Bonus {
        Potion("/resources/images/potion.png"),
        Toilette("/resources/images/toilette.png"),
        Sandwich("/resources/images/sandwich.png"),
        Pain("/resources/images/pain.png"),
        Salade("/resources/images/salade.png"),
        Tomate("/resources/images/tomate.png"),
        Viande("/resources/images/viande.png");

        public String imageNomFichier;  // Chemin vers l'image du fichier

        /**
         * Constructeur de la classe des Bonus
         * @param imageNomFichier Chemin vers l'image fichier du Bonus
         */
        Bonus(String imageNomFichier) {
            this.imageNomFichier = imageNomFichier;
        }
    }

    /**
     * Enum qui possède les Malus du jeu
     */
    public static enum Malus {
        Flaque("/resources/images/flaque.png"),
        Voiture("/resources/images/voiture.png");

        public String imageNomFichier;  // Chemin vers l'image du fichier

        /**
         * Constructeur de la classe des Malus
         * @param imageNomFichier Chemin vers l'image fichier du Malus
         */
        Malus(String imageNomFichier) {
            this.imageNomFichier = imageNomFichier;
        }
    }

    /**
     * Enum qui possède les Joueurs du jeu
     */
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
