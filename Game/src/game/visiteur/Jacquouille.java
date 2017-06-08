package game.visiteur;

import game.Constantes;

/**
 *
 * @author Gabriel Luthier
 */
public class Jacquouille extends Joueur {

    public Jacquouille() {
        super(Constantes.Joueurs.Jacquouille.nom,
                Constantes.Joueurs.Jacquouille.maxVie,
                Constantes.Joueurs.Jacquouille.facteurVie,
                Constantes.Joueurs.Jacquouille.facteurScore,
                Constantes.Joueurs.Jacquouille.position,
                Constantes.Joueurs.Jacquouille.imageNomFichier);
    }
}
