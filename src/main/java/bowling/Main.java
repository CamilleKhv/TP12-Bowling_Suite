package bowling;

class Main {
	public static void main(String[] args) {
		String[] players={"Pierre","Paul"};
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		partie.demarreNouvellePartie(players);
		partie.enregistreLancer(5);
		partie.enregistreLancer(3);
		partie.enregistreLancer(10);
		partie.enregistreLancer(7);
		partie.enregistreLancer(3);
	
	}
}
