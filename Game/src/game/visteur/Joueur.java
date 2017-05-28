package game.visteur;

import game.Constantes;
import game.element.bonus.Bonus;
import game.element.malus.Malus;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Joueur extends ImageView implements Visiteur {

    private final String nom;
    private double baseVitesse;
    private double vitesse;
    private final double maxVie;
    private double vie;
    private boolean estEnVie;
    private int position;

    private final double facteurVitesse;
    private final double facteurVie;
    
    private final Image image;

    public Joueur(String nom, double baseVitesse, double maxVie,
            double facteurVitesse, double facteurVie,
            int position, String imageNomFichier) {
        this.nom = nom;
        this.baseVitesse = baseVitesse;
        vitesse = baseVitesse;
        this.maxVie = maxVie;
        vie = maxVie;
        estEnVie = true;
        this.position = position;
        this.facteurVitesse = facteurVitesse;
        this.facteurVie = facteurVie;
        
        image = new Image(getClass().getResource(imageNomFichier).toString(),
                Constantes.CELL_WIDTH, Constantes.CELL_WIDTH, true, true);
        setX(position * Constantes.CELL_WIDTH);
        setY(Constantes.GAME_HEIGHT - Constantes.CELL_WIDTH);
        setImage(image);
    }

    public boolean estEnVie() {
        return estEnVie;
    }

    public void perdVie(double degat) {
        double vieSupposee = vie - degat;
        vie = vieSupposee > 0 ? vieSupposee : 0;
        estEnVie = vie > 0;
    }

    public void gagneVie(double soin) {
        double vieSupposee = vie + soin;
        vie = vieSupposee < maxVie ? vieSupposee : maxVie;
    }

    public void ralenti(double facteur) {
        facteur = facteur < 1 ? 1 : facteur;
        vitesse /= facteur;
    }

    public void accelere(double facteur) {
        facteur = facteur < 1 ? 1 : facteur;
        vitesse *= facteur;
    }
    
    public void moveLeft() {
        int newPosition = position - 1;
        position = newPosition >= 0 ? newPosition : 0;
        setX(position * Constantes.CELL_WIDTH);
    }
    
    public void moveRight() {
        int newPosition = position + 1;
        position = newPosition < Constantes.NUM_COLS ? newPosition : Constantes.NUM_COLS-1;
        setX(position * Constantes.CELL_WIDTH);
    }

    @Override
    public void visite(Malus o) {
        double degats = facteurVie * o.getDegat();
        perdVie(degats);
        
        double ralenti = facteurVitesse * o.getFacteurRalenti();
        ralenti(ralenti);
    }

    @Override
    public void visite(Bonus b) {
        double soin = facteurVie * b.getSoin();
        gagneVie(soin);
        
        double accelere = facteurVitesse * b.getFacteurAccelere();
        accelere(accelere);
    }
}
