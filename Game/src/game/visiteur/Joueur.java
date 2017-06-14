package game.visiteur;

import game.Constantes;
import game.element.Obstacle;
import game.element.bonus.Potion;
import game.element.bonus.Toilette;
import game.element.malus.Flaque;
import game.element.malus.Voiture;
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
 *
 * @author Gabriel Luthier
 */
public abstract class Joueur extends Group implements Visiteur {

    private final String nom;
    private final int maxVie;
    private int vie;
    private boolean estEnVie;
    private int score;
    private int position;

    private final double facteurVie;
    private final double facteurScore;

    private final ImageView imageView;
    private final Image image;

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
        imageView = new ImageView(image);
        imageView.setX(position * Constantes.CELL_SIZE);
        imageView.setY(Constantes.GAME_HEIGHT - Constantes.CELL_SIZE);
        getChildren().add(imageView);
    }

    public String getNom() {
        return nom;
    }

    public int getVie() {
        return vie;
    }

    public int getScore() {
        return score;
    }

    public boolean estEnVie() {
        return estEnVie;
    }

    public void moveLeft() {
        int newPosition = position - 1;
        position = newPosition >= 0 ? newPosition : 0;
        imageView.setX(position * Constantes.CELL_SIZE);
    }

    public void moveRight() {
        int newPosition = position + 1;
        position = newPosition < Constantes.NUM_COLS ? newPosition : Constantes.NUM_COLS - 1;
        imageView.setX(position * Constantes.CELL_SIZE);
    }

    public void modifieVie(double modif) {
        modif *= facteurVie;
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
        texteContenu = texteContenu + (int)modif;
        texte.setText(texteContenu);
        texte.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // Placement du texte
        double textWidth = texte.getBoundsInLocal().getWidth();
        texte.setX(getX() + imageView.getBoundsInLocal().getWidth()/2.0 - textWidth/2.0);
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

    public void modifieScore(double modif) {
        modif *= facteurScore;
        int scoreSuppose = (int) (score + modif);
        score = scoreSuppose > 0
                ? scoreSuppose : 0;
    }

    public double getX() {
        return imageView.getX();
    }

    public double getY() {
        return imageView.getY();
    }
}
