package game.visiteur;

import game.Constantes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe abstraite pour représenter le visiteur en tant que joueur de la partie
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, 
 * Nathan Gonzalez Montes
 */
public abstract class Joueur extends ImageView implements Visiteur {

    private final String nom;   // Nom qui représente le joueur
    private final int maxVie;   // La vie maximale que possède le joueur
    private int vie;            // La vie du joueur
    private boolean estEnVie;   // Booléen pour savoir si le joueur est en vie ou pas
    private int score;          // Le score des joueurs pendant la partie
    private int position;       // La position dans laquelle se trouve le joueur

    private final double facteurVie;    // Facteur pour mmoidifer la vie
    private final double facteurScore;  // Facteur qui modifie le score du joueur

    private final Image image;  // Image qui représente le joueur

    /**
     * Constructeur de la classe Joueur
     * @param nom Représente le nom du joueur
     * @param maxVie La vie maximale d'un joueur
     * @param facteurVie Le facteur pour modifier la vie
     * @param facteurScore Le facteur pour modifier le score
     * @param position La position du joueur
     * @param imageNomFichier Le chemin vers l'image du fichier
     */
    public Joueur(String nom, int maxVie,
            double facteurVie, double facteurScore,
            int position, String imageNomFichier) {
        this.nom = nom;
        this.maxVie = maxVie;
        vie = maxVie;
        estEnVie = true;
        score = 0;
        this.position = position;
        this.facteurVie = facteurVie;
        this.facteurScore = facteurScore;

        image = new Image(getClass().getResource(imageNomFichier).toString(),
                Constantes.CELL_SIZE, Constantes.CELL_SIZE, true, true);
        setX(position * Constantes.CELL_SIZE);
        setY(Constantes.GAME_HEIGHT - Constantes.CELL_SIZE);
        setImage(image);
    }

    /**
     * Méthode pour obtenir le nom du joueur
     * @return Le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir la vie du joueur
     * @return La vie du joueur
     */
    public int getVie() {
        return vie;
    }

    /**
     * Méthode pour obtenir le score du joueur
     * @return Le score du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Méthode pour savoir si le  joueur est en vie
     * @return Vrai si le joueur est en vie, faux sinon
     */
    public boolean estEnVie() {
        return estEnVie;
    }

    /**
     * Déplacement de la position du joueur vers la gauche
     */
    public void moveLeft() {
        int newPosition = position - 1;
        position = newPosition >= 0 ? newPosition : 0;
        setX(position * Constantes.CELL_SIZE);
    }

    /**
     * Déplacement de la position du joueur vers la droite
     */
    public void moveRight() {
        int newPosition = position + 1;
        position = newPosition < Constantes.NUM_COLS ? newPosition : Constantes.NUM_COLS - 1;
        setX(position * Constantes.CELL_SIZE);
    }

    /**
     * Méthode pour modifier la vie du joueur
     * @param modif La modification apportée
     */
    public void modifieVie(double modif) {
        modif *= facteurVie;
        int vieSupposee = (int) (vie + modif);
        vie = vieSupposee > 0
                ? (vieSupposee < maxVie
                        ? vieSupposee : maxVie)
                : 0;
        estEnVie = vie > 0;
    }

    /**
     * Méthode pour modifier le score du joueur
     * @param modif La modification apportée
     */
    public void modifieScore(double modif) {
        modif *= facteurScore;
        int scoreSuppose = (int) (score + modif);
        score = scoreSuppose > 0
                ? scoreSuppose : 0;
    }
}
