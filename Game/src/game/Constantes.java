package game;

import javafx.scene.input.KeyCode;

/**
 * Classe contenatn la plupart des constantes à utiliser dans le programme
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public class Constantes {

    public static final String JEU_TITRE = "Visiteur"; // Nom du jeu
    public static final int JEU_LARGEUR = 800;   // Largeur de la fenêtre de jeu
    public static final int JEU_HAUTEUR = 600;  // Hauteur de la fenêtre de jeu
    public static final int NBR_COLS = 10;      // Nombre de colones pour les obstacles
    // Taille d'une céllule pour un objet
    public static final double CELLULE_TAILLE = (double) JEU_LARGEUR / (double) NBR_COLS;
    // Taille des images des obstacles
    public static final double ICON_TAILLE = CELLULE_TAILLE / 3;
    public static final int TEMPS_PARTIE_SECONDES = 30; // Temps durée de la partie
    public static final int NBR_BONUS = 8;              // Quantité de bonus
    public static final int NBR_MALUS = 12;             // Quantité de malus
    // Quantité totale d'obstacles
    public static final int NBR_OBSTACLES = NBR_BONUS + NBR_MALUS;
    // Chemin vers l'image de fons du jeu
    public static final String ARRIERE_PLAN_CHEMIN = "/resources/images/field.png";
    public static final long JEU_VITESSE = 5; // 1 = lent, 10 = rapide
    public static final KeyCode RESTART_TOUCHE = KeyCode.R;
    public static final String REGLE_CHEMIN = "/resources/images/regles.jpg";

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

        public String imageChemin;  // Chemin vers l'image du fichier

        /**
         * Constructeur de la classe des Bonus
         *
         * @param imageNomFichier Chemin vers l'image fichier du Bonus
         */
        Bonus(String imageNomFichier) {
            this.imageChemin = imageNomFichier;
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
         *
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
