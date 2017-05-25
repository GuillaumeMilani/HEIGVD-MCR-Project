package game.action;

/**
 *
 * @author Gabriel Luthier
 */
public class Mouvement implements Action {

    public static enum MouvementType {
        GAUCHE,
        DROITE;
    }

    private final MouvementType mouvement;
    
    public Mouvement(MouvementType mouvement) {
        this.mouvement = mouvement;
    }
    

    @Override
    public void act() {
        switch (mouvement) {
            case GAUCHE:

                break;
            case DROITE:

                break;
            default:
                break;
        }
    }

}
