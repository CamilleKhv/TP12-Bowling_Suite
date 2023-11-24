package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultiPlayerTest {

	private PartieMultiJoueurs partie;
	private String[] nomsJoueurs;

	@BeforeEach
	public void setUp() {
		nomsJoueurs = new String[]{"Pierre", "Paul"};
		partie = new PartieMultiJoueurs();
	}

	@Test
	public void testDemarrerUnePartie() {
		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 1", partie.demarreNouvellePartie(nomsJoueurs), "La partie est mal initialisée");
	}

	@Test
	public void testPasDeJoueurs() {
		assertThrows(IllegalArgumentException.class, () -> partie.demarreNouvellePartie(new String[]{}), "Il n'y a pas de joueurs, la partie ne peut pas démarrer");
		assertThrows(IllegalArgumentException.class, () -> partie.demarreNouvellePartie(null), "Il n'y a pas de joueurs, la partie ne peut pas démarrer");
	}

	@Test
	public void testPartieNonDemarree() {
		assertThrows(IllegalStateException.class, () -> partie.enregistreLancer(1), "La partie n'a pas démarré");
	}

	@Test
	public void testLanceEnregistre() {
		partie.demarreNouvellePartie(nomsJoueurs);
		assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1", partie.enregistreLancer(10), "Pas le bon prochain tir pour le strike");
		assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 2", partie.enregistreLancer(4), "Pas le bon prochain tir");
		assertEquals("Prochain tir : joueur Pierre, tour n° 2, boule n° 1", partie.enregistreLancer(0), "Pas le bon prochain tir");
	}

	@Test
	public void testPartieTerminee() {
		partie.demarreNouvellePartie(nomsJoueurs);
		for (int i = 0; i < 41; i++) {
			partie.enregistreLancer(5);
		}
		assertEquals("Partie terminée", partie.enregistreLancer(4), "La partie ne se termine pas");
	}

	@Test
	public void testScoreStrikeSpare() {
		partie.demarreNouvellePartie(nomsJoueurs);
		partie.enregistreLancer(10);
		partie.enregistreLancer(5);
		partie.enregistreLancer(5);
		partie.enregistreLancer(2);
		partie.enregistreLancer(3);
		partie.enregistreLancer(1);
		partie.enregistreLancer(0);
		assertEquals(20, partie.scorePour("Pierre"), "Pas le bon score lors d'un strike");
		assertEquals(12, partie.scorePour("Paul"), "Pas le bon score lors d'un spare");
	}
}
