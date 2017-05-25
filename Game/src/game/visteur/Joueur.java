package game.visteur;

import game.element.bonus.Bonus;
import game.element.obstacle.Obstacle;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Joueur implements Visiteur {

    private final String nom;
    private double vitesse;
    private final double maxVie;
    private double vie;
    private boolean estEnVie;

    private final double facteurVitesse;
    private final double facteurVie;

    private final String imageNomFichier;

    public Joueur(String nom, double vitesse, double maxVie,
            double facteurVitesse, double facteurVie,
            String imageNomFichier) {
        this.nom = nom;
        this.vitesse = vitesse;
        this.maxVie = maxVie;
        vie = maxVie;
        estEnVie = true;
        this.facteurVitesse = facteurVitesse;
        this.facteurVie = facteurVie;
        this.imageNomFichier = imageNomFichier;
    }

    public boolean estEnVie() {
        return estEnVie;
    }

    public void perdVie(double degat) {
        vie -= degat;
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

    @Override
    public void visite(Obstacle o) {
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
