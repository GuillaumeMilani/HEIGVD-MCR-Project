package game.visteur;

import game.Constantes;
import game.element.Obstacle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Joueur extends ImageView implements Visiteur {

    private final String nom;
    private final int maxVie;
    private int vie;
    private boolean estEnVie;
    private int score;
    private int position;

    private final double facteurVie;
    private final double facteurScore;

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
        setX(position * Constantes.CELL_SIZE);
        setY(Constantes.GAME_HEIGHT - Constantes.CELL_SIZE);
        setImage(image);
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
        setX(position * Constantes.CELL_SIZE);
    }

    public void moveRight() {
        int newPosition = position + 1;
        position = newPosition < Constantes.NUM_COLS ? newPosition : Constantes.NUM_COLS - 1;
        setX(position * Constantes.CELL_SIZE);
    }

    public void modifieVie(double modif) {
        int vieSupposee = (int) (vie + modif);
        vie = vieSupposee > 0
                ? (vieSupposee < maxVie
                        ? vieSupposee : maxVie)
                : 0;
        estEnVie = vie > 0;
    }

    public void modifieScore(double modif) {
        int scoreSuppose = (int) (score + modif);
        score = scoreSuppose > 0
                ? scoreSuppose : 0;
    }

    @Override
    public void visite(Obstacle o) {
        modifieVie(facteurVie * o.getModifVie());
        modifieScore(facteurScore * o.getPoints());
    }
}
