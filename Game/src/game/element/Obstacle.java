package game.element;

import game.Constantes;
import game.visiteur.Joueur;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe pour les éléments (obstacles) qui vont être visités par les joueurs, et qui , selon l'obstacle, vas donner à
 * ceux-ci une bonification ou une pénalisation
 *
 * @author Gabriel Luthier, Guillaume Milani, Tony Clavien, Maxime Guillod, Nathan Gonzalez Montes
 */
public abstract class Obstacle extends ImageView implements Element {

    private final Image image;  // Image de l'obstacle
    private final List<Joueur> joueursVisite;   // Liste des joueurs visiteurs

    /**
     * Constructeur de la classe Obstacle
     *
     * @param imageNomFichier Nom du fichier de l'obstacle
     */
    public Obstacle(String imageNomFichier) {
        image = new Image(getClass().getResource(imageNomFichier).toString(),
                Constantes.CELLULE_TAILLE, Constantes.CELLULE_TAILLE, true, true);
        joueursVisite = new ArrayList<>();
        nouvelleRandomPosition();
        setImage(image);
    }

    /**
     * Obstacle visité par un joueur après avoir eu un contact entre les deux
     *
     * @param joueur Le joueur qui visite l'obstacle
     * @return Vrai si le joueur est dans la luste, faux sinon
     */
    public boolean aEteVisitePar(Joueur joueur) {
        return joueursVisite.contains(joueur);
    }

    /**
     * Rajoute un joueur à la liste de joueurs qui font une visite
     *
     * @param joueur Le joueur qui fait une visite
     */
    public void ajouteVisite(Joueur joueur) {
        joueursVisite.add(joueur);
    }

    /**
     * Méthode pour remettre un obstacle dans le jeu à une position random
     */
    public final void nouvelleRandomPosition() {
        int position = (int) (Math.random() * Constantes.NBR_COLS);
        setX(position * Constantes.CELLULE_TAILLE);
        setY(-(Math.random() * Constantes.JEU_HAUTEUR));
    }
}
