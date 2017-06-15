package game.visiteur;

import game.Constantes;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Classe abstraite pour représenter le visiteur en tant que joueur de la partie
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public abstract class Joueur extends Group implements Visiteur {

    private final String nom;   // Nom qui représente le joueur
    private final int maxVie;   // La vie maximale que possède le joueur
    private int vie;            // La vie du joueur
    private boolean estEnVie;   // Booléen pour savoir si le joueur est en vie ou pas
    private int score;          // Le score des joueurs pendant la partie
    private int position;       // La position dans laquelle se trouve le joueur

    private final ImageView imageView;
    private final Image image;  // Image qui représente le joueur

    /**
     * Constructeur de la classe Joueur
     *
     * @param nom Représente le nom du joueur
     * @param maxVie La vie maximale d'un joueur
     * @param position La position du joueur
     * @param imageNomFichier Le chemin vers l'image du fichier
     */
    public Joueur(String nom, int maxVie,
            int position, String imageNomFichier) {
        this.nom = nom;
        this.maxVie = maxVie;
        vie = maxVie;
        estEnVie = true;
        score = 0;
        this.position = position;

        image = new Image(getClass().getResource(imageNomFichier).toString(),
                Constantes.CELLULE_TAILLE, Constantes.CELLULE_TAILLE, true, true);
        imageView = new ImageView(image);
        imageView.setX(position * Constantes.CELLULE_TAILLE);
        imageView.setY(Constantes.JEU_HAUTEUR - Constantes.CELLULE_TAILLE);
        getChildren().add(imageView);
    }

    /**
     * Méthode pour obtenir le nom du joueur
     *
     * @return Le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir la vie du joueur
     *
     * @return La vie du joueur
     */
    public int getVie() {
        return vie;
    }

    /**
     * Méthode pour obtenir le score du joueur
     *
     * @return Le score du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Méthode pour savoir si le joueur est en vie
     *
     * @return Vrai si le joueur est en vie, faux sinon
     */
    public boolean estEnVie() {
        return estEnVie;
    }

    /**
     * Déplacement de la position du joueur vers la gauche
     */
    public void deplaceGauche() {
        int newPosition = position - 1;
        position = newPosition >= 0 ? newPosition : 0;
        imageView.setX(position * Constantes.CELLULE_TAILLE);
    }

    /**
     * Déplacement de la position du joueur vers la droite
     */
    public void deplaceDroite() {
        int newPosition = position + 1;
        position = newPosition < Constantes.NBR_COLS ? newPosition : Constantes.NBR_COLS - 1;
        imageView.setX(position * Constantes.CELLULE_TAILLE);
    }

    /**
     * Méthode pour modifier la vie du joueur
     *
     * @param modif La modification apportée
     */
    public void modifieVie(double modif) {
        int vieSupposee = (int) (vie + modif);
        vie = vieSupposee > 0
                ? (vieSupposee < maxVie
                        ? vieSupposee : maxVie)
                : 0;
        estEnVie = vie > 0;

        afficheTexte(modif);
    }

    private void afficheTexte(double modif) {
        Text texte = new Text();
        String texteContenu = "";

        // Couleur du texte en fonction du signe de la modification
        if (modif > 0.) {
            texte.setFill(Color.YELLOWGREEN);
            texteContenu += "+";
        } else {
            texte.setFill(Color.RED);
        }

        // Definir le texte
        texteContenu = texteContenu + (int) modif;
        texte.setText(texteContenu);
        texte.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // Placement du texte
        double textWidth = texte.getBoundsInLocal().getWidth();
        texte.setX(getX() + imageView.getBoundsInLocal().getWidth() / 2.0 - textWidth / 2.0);
        texte.setY(getY() - 10);

        // Effet de disparition du texte
        FadeTransition ft = new FadeTransition(Duration.millis(2000), texte);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> getChildren().remove(texte));

        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), texte);
        tt.setByY(-100);
        tt.play();
        ft.play();

        getChildren().add(texte);
    }

    /**
     * Méthode pour modifier le score du joueur
     *
     * @param modif La modification apportée
     */
    public void modifieScore(double modif) {
        score = (int) Math.max(0, (score + modif));
    }

    public double getX() {
        return imageView.getX();
    }

    public double getY() {
        return imageView.getY();
    }
}
