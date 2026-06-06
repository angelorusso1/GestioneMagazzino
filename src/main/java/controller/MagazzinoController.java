package controller;

import boundary.*;
import entity.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;

public class MagazzinoController {

	private SchermataRegistrazione schermata;
	private SchermataLogin schermataLogin;
	private SchermataNotifiche schermataNotifiche;

	private CatalogoProdotti catalogoProdotti = new CatalogoProdotti();

	public MagazzinoController(SchermataRegistrazione schermata) {
		this.schermata = schermata;
	}

	public MagazzinoController(SchermataLogin schermataLogin) {
		this.schermataLogin = schermataLogin;
	}

	public MagazzinoController(SchermataNotifiche schermataNotifiche) {
		this.schermataNotifiche = schermataNotifiche;
	}

	public void richiediAccesso(String nome, String cognome, String email) {
		GestioneUtenti gestioneUtenti = new GestioneUtenti();
		Utente utenteTrovato = gestioneUtenti.verificaCredenziali(nome, cognome, email);

		if (utenteTrovato == null) {
			schermataLogin.messaggioErrore("Utente non esistente. Verifica i dati inseriti.");
		} else {
			schermataLogin.messaggioConferma("Benvenuto " + utenteTrovato.getNome() + "!");

			JFrame framePrincipale = new JFrame("Sistema di Gestione Magazzino");
			MainFrame mainFrame = new MainFrame(utenteTrovato);

			framePrincipale.setContentPane(mainFrame.getMainPanel());
			framePrincipale.setSize(600, 450);
			framePrincipale.setLocationRelativeTo(null);
			framePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			framePrincipale.setVisible(true);

			schermataLogin.chiudiFinestra();
		}
	}

	/**
	 * Implementazione del Caso d'Uso: Creazione Prodotti
	 */
	public void richiediCreazioneProdotto(String codice, String nome, String descrizione, Categoria categoria, int soglia, Posizione posizione, SchermataCatalogoProdotti boundary) {

		boolean isUnivoco = catalogoProdotti.verificaUnivocitaCodice(codice);

		if (!isUnivoco) {
			// RISOLTO: Usiamo messaggioConferma per notificare l'errore a schermo senza creare nuovi metodi
			boundary.messaggioConferma("ATTENZIONE: Il codice " + codice + " esiste già. Creazione annullata.");
		} else {
			catalogoProdotti.aggiungiProdotto(codice, nome, descrizione, posizione, categoria, soglia, 0);
			boundary.messaggioConferma("Prodotto '" + nome + "' inserito correttamente nel catalogo del magazzino.");
		}
	}

	public void richiediCatalogoCompleto() {
		throw new UnsupportedOperationException();
	}

	public void richiediCarico(Prodotto prodotto, int quantita) {
		throw new UnsupportedOperationException();
	}

	public void richiediModificaProdotto(String nuovoNome, String nuovaDescrizione, Posizione nuovaPosizione, int nuovaSoglia, int nuovaQuantita, Categoria nuovaCategoria, Prodotto prodotto) {
		throw new UnsupportedOperationException();
	}

	public void richiediElencoProdotti() {
		throw new UnsupportedOperationException();
	}

	public void richiediStoricoPersonale(String email) {
		throw new UnsupportedOperationException();
	}

	public void richiediCatalogoSottoScorta() {
		throw new UnsupportedOperationException();
	}

	public void richiediRegistrazione(String nome, String cognome, String email, Ruolo ruolo) {
		GestioneUtenti gestioneUtenti = new GestioneUtenti();
		boolean esito = gestioneUtenti.registraDati(nome, cognome, email, ruolo);

		if (esito) {
			schermata.messaggioConferma("Utente registrato con successo nel database!");
		} else {
			schermata.messaggioErrore("Errore durante la registrazione dell'utente.");
		}
	}

	public void richiediAnalisiMagazzino(LocalDate dataInzio, LocalDate dataFine) {
		throw new UnsupportedOperationException();
	}

	public void richiediScarico(Prodotto prodotto, int quantita) {
		throw new UnsupportedOperationException();
	}

	public void Clayton_richiediRicercaCodice(String codice) {
		throw new UnsupportedOperationException();
	}

	public void richiediRicercaCodice(String codice) {
		throw new UnsupportedOperationException();
	}

	public void richiediRicercaNome(String nome) {
		throw new UnsupportedOperationException();
	}

	public void richiediRicercaCategoria(Categoria categoria) {
		throw new UnsupportedOperationException();
	}

	public void richiediRicercaPoszione(Posizione posizione) {
		throw new UnsupportedOperationException();
	}

	public void apriSchermataNotifiche() {
		GestioneNotifiche gestioneNotifiche = new GestioneNotifiche();

		// 1. Chiediamo le notifiche al database
		List<Notifica> notifiche = gestioneNotifiche.getNotificheInOrdineCrescente();

		// 2. Creiamo la schermata
		SchermataNotifiche schermata = new SchermataNotifiche();

		// 3. Riempiamo la tabella
		schermata.popolaTabellaNotifiche(notifiche);

		// 4. Apriamo la finestra
		JFrame frame = new JFrame("Notifiche Sotto Scorta");
		frame.setContentPane(schermata.getMainPanel());
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}