package game.element.bonus;

import game.element.Element;
import game.visteur.Visiteur;

/**
 *
 * @author Gabriel Luthier
 */
public abstract class Bonus implements Element {

    private double soin;
    private double facteurAccelere;
    
    public Bonus(double soin, double facteurAccelere) {
        this.soin = soin;
        this.facteurAccelere = facteurAccelere;
    }
    
    public double getSoin() {
        return soin;
    }
    
    public double getFacteurAccelere() {
        return facteurAccelere;
    }
        
    @Override
    public void accept(Visiteur v) {
        v.visite(this);
    }
}
