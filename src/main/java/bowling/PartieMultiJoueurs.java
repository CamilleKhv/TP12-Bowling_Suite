package bowling;

import java.util.HashMap;
import java.util.Map;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {

	private String[] nomsDesJoueurs;
	private Map<String, Joueur> joueurs;
	private int joueurCourantIndex;
	private int tourCourant;
	private int bouleCourante;

	public PartieMultiJoueurs() {
		joueurs = new HashMap<>();
	}
	
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalArgumentException("Le tableau des noms des joueurs ne peut pas être vide ou null.");
		}
		this.nomsDesJoueurs = nomsDesJoueurs;
		this.joueurCourantIndex = 0;
		this.tourCourant = 1;
		this.bouleCourante = 1;

		for (String nom : nomsDesJoueurs) {
			joueurs.put(nom, new Joueur());
		}

		return "Prochain tir : joueur " + nomsDesJoueurs[joueurCourantIndex] +
			", tour n° " + tourCourant + ", boule n° " + bouleCourante;
	}

	@Override
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalStateException("La partie n'est pas démarrée.");
		}

		Joueur joueurCourant = joueurs.get(nomsDesJoueurs[joueurCourantIndex]);
		joueurCourant.enregistreLancer(tourCourant, bouleCourante, nombreDeQuillesAbattues);

		// Logique pour déterminer le prochain joueur, tour et boule
		// ...

		return "Prochain tir : joueur " + nomsDesJoueurs[joueurCourantIndex] +
			", tour n° " + tourCourant + ", boule n° " + bouleCourante;
	}

	@Override
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		if (!joueurs.containsKey(nomDuJoueur)) {
			throw new IllegalArgumentException("Le joueur " + nomDuJoueur + " ne joue pas dans cette partie.");
		}

		return joueurs.get(nomDuJoueur).calculeScore();
	}
}