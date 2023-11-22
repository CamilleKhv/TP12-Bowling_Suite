package bowling;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

	private List<Integer> lancers;

	public Joueur() {
		lancers = new ArrayList<>();
	}

	public void enregistreLancer(int tour, int boule, int nombreDeQuillesAbattues) {
		lancers.add(nombreDeQuillesAbattues);
		
	}

	public int calculeScore() {
		int score = 0;
		int lancerCourant = 0;

		for (int tour = 1; tour <= 10; tour++) {
			if (lancerCourant < lancers.size()) {
				int lancer1 = lancers.get(lancerCourant);
				score += lancer1;

				if (lancer1 == 10) {  // Strike
					// Ajoute les points des deux lancers suivants
					score += lancers.get(lancerCourant + 1);
					score += lancers.get(lancerCourant + 2);
				} else {
					lancerCourant++;

					if (lancerCourant < lancers.size()) {
						int lancer2 = lancers.get(lancerCourant);
						score += lancer2;

						if (lancer1 + lancer2 == 10) {  // Spare
							// Ajoute le point du lancer suivant
							score += lancers.get(lancerCourant + 1);
						}
					}
				}
				lancerCourant++;
			}
		}

		return score;
	}
}