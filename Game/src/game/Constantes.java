package game;

import javafx.scene.input.KeyCode;

/**
 * Classe contenatn la plupart des constantes à utiliser dans le programme
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public class Constantes {

    public static final String JEU_TITRE = "Visiteur"; // Nom du jeu

    public static final int JEU_LARGEUR = 800;  // Largeur de la fenêtre de jeu
    public static final int JEU_HAUTEUR = 600;  // Hauteur de la fenêtre de jeu
    public static final long JEU_VITESSE = 5;   // 1 = lent, 10 = rapide
    public static final int NBR_COLS = 10;      // Nombre de colones pour les obstacles
    // Taille d'une céllule pour un objet
    public static final double CELLULE_TAILLE = (double) JEU_LARGEUR / (double) NBR_COLS;
    // Taille des images des obstacles
    public static final double ICON_TAILLE = CELLULE_TAILLE / 3;

    public static final int TEMPS_PARTIE_SECONDES = 30;             // Temps durée de la partie
    public static final int NBR_BONUS = 8;                          // Quantité de bonus
    public static final int NBR_MALUS = 12;                         // Quantité de malus
    public static final int NBR_OBSTACLES = NBR_BONUS + NBR_MALUS;  // Quantité totale d'obstacles

    public static final KeyCode RESTART_TOUCHE = KeyCode.R;

    // Chemin vers l'image du jeu
    public static final String ARRIERE_PLAN_CHEMIN = "/resources/images/field.png";
    public static final String REGLE_CHEMIN = "/resources/images/regles.jpg";
    public static final String POTION_CHEMIN = "/resources/images/potion.png";
    public static final String TOILETTE_CHEMIN = "/resources/images/toilette.png";
    public static final String SANDWICH_CHEMIN = "/resources/images/sandwich.png";
    public static final String PAIN_CHEMIN = "/resources/images/pain.png";
    public static final String SALADE_CHEMIN = "/resources/images/salade.png";
    public static final String TOMATE_CHEMIN = "/resources/images/tomate.png";
    public static final String VIANDE_CHEMIN = "/resources/images/viande.png";
    public static final String FLAQUE_CHEMIN = "/resources/images/flaque.png";
    public static final String VOITURE_CHEMIN = "/resources/images/voiture.png";
    public static final String JACQUOUILLE_CHEMIN = "/resources/images/jacquouille.png";
    public static final String GODEFROY_CHEMIN = "/resources/images/godefroy.png";

    /**
     * Enum qui possède les Bonus du jeu
     */
    public static enum Bonus {
        Potion(POTION_CHEMIN),
        Toilette(TOILETTE_CHEMIN),
        Sandwich(SANDWICH_CHEMIN),
        Pain(PAIN_CHEMIN),
        Salade(SALADE_CHEMIN),
        Tomate(TOMATE_CHEMIN),
        Viande(VIANDE_CHEMIN);

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
        Flaque(FLAQUE_CHEMIN),
        Voiture(VOITURE_CHEMIN);

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
                2, JACQUOUILLE_CHEMIN),
        Godefroy("Godefroy", 120,
                7, GODEFROY_CHEMIN);

        public String nom;
        public int maxVie;
        public double facteurVie;
        public double facteurScore;
        public int position;
        public String imageNomFichier;

        private Joueurs(String nom, int maxVie,
                int position, String imageNomFichier) {
            this.nom = nom;
            this.maxVie = maxVie;
            this.position = position;
            this.imageNomFichier = imageNomFichier;
        }
    }
}
