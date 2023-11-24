package bowling;

import java.util.HashMap;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {

	private HashMap<String, PartieMonoJoueur> laPartie;
	private String[] noms;
	private int nombreDeJoueurs;
	private int numeroJoueurAct = -1;

	public String getPhraseRetour() {
		return "Prochain tir : joueur " + noms[numeroJoueurAct] +
			", tour n° " + laPartie.get(noms[numeroJoueurAct]).numeroTourCourant() +
			", boule n° " + laPartie.get(noms[numeroJoueurAct]).numeroProchainLancer();
	}
	
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalArgumentException("Le tableau de noms est vide");
		}

		// Initialisation
		laPartie = new HashMap<>();
		this.noms = nomsDesJoueurs;
		nombreDeJoueurs = nomsDesJoueurs.length;
		numeroJoueurAct = 0;

		for (String nom : nomsDesJoueurs) {
			laPartie.put(nom, new PartieMonoJoueur());
		}

		return getPhraseRetour();
	}
	
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		if (numeroJoueurAct == -1) {
			throw new IllegalStateException("La partie n'a pas commencé");
		}

		if (laPartie.get(noms[0]).numeroTourCourant() == 0) {
			return "Partie terminée";
		}

		PartieMonoJoueur joueurPartie = laPartie.get(noms[numeroJoueurAct]);
		joueurPartie.enregistreLancer(nombreDeQuillesAbattues);

		if (joueurPartie.numeroProchainLancer() == 1 || joueurPartie.estTerminee()) {
			numeroJoueurAct = (numeroJoueurAct + 1) % nombreDeJoueurs;
		}
		return getPhraseRetour();
	}


	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		if (laPartie.get(nomDuJoueur) == null) {
			throw new IllegalArgumentException(nomDuJoueur + " ne joue pas dans cette partie");
		}

		return laPartie.get(nomDuJoueur).score();
	}
}
