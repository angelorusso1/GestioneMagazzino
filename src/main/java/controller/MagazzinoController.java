package controller;

import boundary.MainFrame;
import boundary.OutputSchermataAnalisi;
import boundary.SchermataLogin;
import database.GestorePersistenza;
import entity.*;

import java.time.LocalDate;

import boundary.SchermataRegistrazione;
import entity.Ruolo;

import javax.swing.*;

public class MagazzinoController {

	private SchermataRegistrazione schermata;
	private SchermataLogin schermataLogin;

	public MagazzinoController(SchermataRegistrazione schermata) {
		this.schermata = schermata;
	}

	public MagazzinoController(SchermataLogin schermataLogin) {
		this.schermataLogin = schermataLogin;
	}

	public void richiediAccesso(String nome, String cognome, String email) {
		GestioneUtenti gestioneUtenti = new GestioneUtenti();

		// Cerchiamo l'utente sul DB
		Utente utenteTrovato = gestioneUtenti.verificaCredenziali(nome, cognome, email);

		if (utenteTrovato == null) {
			//UTENTE NON ESISTE
			schermataLogin.messaggioErrore("Utente non esistente. Verifica i dati inseriti.");
		} else {
			//UTENTE TROVATO! Verifichiamo il ruolo usando "instanceof"
			schermataLogin.messaggioConferma("Benvenuto " + utenteTrovato.getNome() + "!");

			// APRIAMO IL MAIN FRAME E GLI PASSIAMO L'UTENTE TROVATO NEL DATABASE
			JFrame framePrincipale = new JFrame("Sistema di Gestione Magazzino");
			MainFrame mainFrame = new MainFrame(utenteTrovato);

			framePrincipale.setContentPane(mainFrame.getMainPanel());
			framePrincipale.setSize(600, 450); // Finestra un po' più grande per la dashboard
			framePrincipale.setLocationRelativeTo(null);
			framePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			framePrincipale.setVisible(true);

			// Chiudiamo la schermata di login
			schermataLogin.chiudiFinestra();
		}
	}

	public void richiediCatalogoCompleto() {
		// TODO - implement MagazzinoController.richiediCatalogoCompleto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param prodotto
	 * @param quantita
	 */
	public void richiediCarico(Prodotto prodotto, int quantita) {
		// TODO - implement MagazzinoController.richiediCarico
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param codice
	 * @param nome
	 * @param descrizione
	 * @param categoria
	 * @param soglia
	 * @param posizione
	 * @param quantita
	 */
	public void richiediCreazioneProdotto(String codice, String nome, String descrizione, Categoria categoria, int soglia, Posizione posizione, int quantita) {
		// TODO - implement MagazzinoController.richiediCreazioneProdotto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nuovoNome
	 * @param nuovaDescrizione
	 * @param nuovaPosizione
	 * @param nuovaSoglia
	 * @param nuovaQuantita
	 * @param nuovaCategoria
	 * @param prodotto
	 */
	public void richiediModificaProdotto(String nuovoNome, String nuovaDescrizione, Posizione nuovaPosizione, int nuovaSoglia, int nuovaQuantita, Categoria nuovaCategoria, Prodotto prodotto) {
		// TODO - implement MagazzinoController.richiediModificaProdotto
		throw new UnsupportedOperationException();
	}

	public void richiediElencoProdotti() {
		// TODO - implement MagazzinoController.richiediElencoProdotti
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param email
	 */
	public void richiediStoricoPersonale(String email) {
		// TODO - implement MagazzinoController.richiediStoricoPersonale
		throw new UnsupportedOperationException();
	}

	public void richiediCatalogoSottoScorta() {
		// TODO - implement MagazzinoController.richiediCatalogoSottoScorta
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param ruolo
	 */
	public void richiediRegistrazione(String nome, String cognome, String email, Ruolo ruolo) {
		GestioneUtenti gestioneUtenti = new GestioneUtenti();

		// Passiamo la enum al gestore
		boolean esito = gestioneUtenti.registraDati(nome, cognome, email, ruolo);

		if (esito) {
			schermata.messaggioConferma("Utente registrato con successo nel database!");
		} else {
			schermata.messaggioErrore("Errore durante la registrazione dell'utente.");
		}
	}

	/**
	 * 
	 * @param dataInizio
	 * @param dataFine
	 */
	public void richiediAnalisiMagazzino(LocalDate dataInizio, LocalDate dataFine) {
		GestorePersistenza gestore = new GestorePersistenza(); // o la tua istanza/singleton

		// 1. Otteniamo l'oggetto report completo dal layer di persistenza
		// passando l'intervallo temporale richiesto
		DatiReport reportMagazzino = gestore.generaReportAnalisi(dataInizio, dataFine);

		// 2. Istanziamo la schermata di output passando un unico oggetto
		OutputSchermataAnalisi schermataRisultati = new OutputSchermataAnalisi(reportMagazzino);

		// 3. Rendiamo visibile la finestra
		schermataRisultati.setVisible(true);
	}

	/**
	 * 
	 * @param prodotto
	 * @param quantita
	 */
	public void richiediScarico(Prodotto prodotto, int quantita) {
		// TODO - implement MagazzinoController.richiediScarico
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param codice
	 */
	public void richiediRicercaCodice(String codice) {
		// TODO - implement MagazzinoController.richiediRicercaCodice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 */
	public void richiediRicercaNome(String nome) {
		// TODO - implement MagazzinoController.richiediRicercaNome
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param categoria
	 */
	public void richiediRicercaCategoria(Categoria categoria) {
		// TODO - implement MagazzinoController.richiediRicercaCategoria
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param posizione
	 */
	public void richiediRicercaPoszione(Posizione posizione) {
		// TODO - implement MagazzinoController.richiediRicercaPoszione
		throw new UnsupportedOperationException();
	}

}