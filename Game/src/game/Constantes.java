package game;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import game.action.Action;
import game.action.Mouvement;
import static javafx.scene.input.KeyCode.A;

/**
 *
 * @author Gabriel Luthier
 */
public class Constantes {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int NUM_COLS = 10;
    public static final double CELL_WIDTH = (double) GAME_WIDTH / (double) NUM_COLS;
    public static final int CLOCK_CYCLE = 50;
    public static final String BACKGROUND_PATH = "/resources/images/field.png";

    public static enum Bonus {
        Potion(1.3, 0),
        Toilette(0, 1.3);

        public double soin;
        public double facteurAccelere;

        private Bonus(double soin, double facteurAccelere) {
            this.soin = soin;
            this.facteurAccelere = facteurAccelere;
        }
    }

    public static enum Malus {
        Flaque(1.2, 0),
        Voiture(0, 1.4);

        public double degat;
        public double facteurRalenti;

        private Malus(double degat, double facteurRalenti) {
            this.degat = degat;
            this.facteurRalenti = facteurRalenti;
        }
    }

    public static enum Joueurs {
        Jacquouille("Jacquouille", 0.7, 100,
                1.2, 0.8,
                2, "/resources/images/jacquouille.png"),
        Godfroy("Godefroy", 0.6, 120,
                0.8, 1.2,
                7, "/resources/images/godefroy.png");

        public String nom;
        public double baseVitesse;
        public double maxVie;
        public double facteurVitesse;
        public double facteurVie;
        public int position;
        public String imageNomFichier;

        private Joueurs(String nom, double baseVitesse, double maxVie,
                double facteurVitesse, double facteurVie,
                int position, String imageNomFichier) {
            this.nom = nom;
            this.baseVitesse = baseVitesse;
            this.maxVie = maxVie;
            this.facteurVitesse = facteurVitesse;
            this.facteurVie = facteurVie;
            this.position = position;
            this.imageNomFichier = imageNomFichier;
        }
    }
}
