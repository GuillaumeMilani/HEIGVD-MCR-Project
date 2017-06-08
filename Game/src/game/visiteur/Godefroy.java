package game.visiteur;

import game.Constantes;

/**
 *
 * @author Gabriel Luthier
 */
public class Godefroy extends Joueur {

    public Godefroy() {
        super(Constantes.Joueurs.Godefroy.nom,
                Constantes.Joueurs.Godefroy.maxVie,
                Constantes.Joueurs.Godefroy.facteurVie,
                Constantes.Joueurs.Godefroy.facteurScore,
                Constantes.Joueurs.Godefroy.position,
                Constantes.Joueurs.Godefroy.imageNomFichier);
    }
}
