package bowling;

class Main {
	public static void main(String[] args) {
		String[] players={"Pierre","Paul"};
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		System.out.println(partie.demarreNouvellePartie(players));
		System.out.println(partie.enregistreLancer(5));
		System.out.println(partie.enregistreLancer(3));
		System.out.println(partie.enregistreLancer(10));
		System.out.println(partie.enregistreLancer(7));
		System.out.println(partie.enregistreLancer(3));

		System.out.println(partie.scorePour("Pierre"));
		System.out.println(partie.scorePour("Paul"));
	}
}
