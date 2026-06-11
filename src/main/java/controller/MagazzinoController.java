package controller;

import boundary.*;
import entity.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;


public class MagazzinoController {

	private SchermataRegistrazione schermataRegistrazione;
	private SchermataLogin schermataLogin;
	private SchermataNotifiche schermataNotifiche;

	private CatalogoProdotti catalogoProdotti = new CatalogoProdotti();

	public MagazzinoController(SchermataRegistrazione schermata) {
		this.schermataRegistrazione = schermata;
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
			boundary.messaggioConferma("ATTENZIONE: Il codice " + codice + " esiste già. Creazione annullata.");
		} else {
			boolean successo = catalogoProdotti.aggiungiProdotto(codice, nome, descrizione, posizione, categoria, soglia, 0);

			if (successo) {
				boundary.messaggioConferma("Prodotto '" + nome + "' inserito correttamente nel catalogo del magazzino.");
			} else {
				boundary.messaggioConferma("ERRORE: Impossibile salvare il prodotto nel database. Riprova.");
			}
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
			schermataRegistrazione.messaggioConferma("Utente registrato con successo nel database!");
		} else {
			schermataRegistrazione.messaggioErrore("Errore durante la registrazione dell'utente.");
		}
	}

	public void richiediAnalisiMagazzino(LocalDate dataInzio, LocalDate dataFine) {
		throw new UnsupportedOperationException();
	}


	public void richiediScarico(String codiceProdotto, int quantitaDaScaricare, SchermataScarico boundary) {
		List<Prodotto> risultati = catalogoProdotti.cercaCodice(codiceProdotto);

		if (risultati == null || risultati.isEmpty()) {
			boundary.messaggioErrore("Prodotto con codice " + codiceProdotto + " non trovato.");
			return;
		}

		Prodotto prodotto = risultati.get(0);

		if (!prodotto.verificaOperazione(quantitaDaScaricare)) {
			boundary.messaggioErrore("Quantità insufficiente! Quantità disponibile: " + prodotto.getQuantitaDisponibile());
			return;
		}

		boolean successo = catalogoProdotti.sottraiProdotto(prodotto, quantitaDaScaricare);

		if (successo) {
			boundary.messaggioConferma("Scarico effettuato con successo. Nuova disponibilità: " + prodotto.getQuantitaDisponibile());
		} else {
			boundary.messaggioErrore("Errore durante il salvataggio sul database.");
		}
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

	public void apriSchermataCreaProdotto() {
		SchermataCatalogoProdotti schermataCatalogo = new SchermataCatalogoProdotti(this);

		// Creazione e configurazione della finestra
		JFrame frameCatalogo = new JFrame("Creazione Nuovo Prodotto");
		frameCatalogo.setContentPane(schermataCatalogo.getMainPanel());
		frameCatalogo.pack();
		frameCatalogo.setSize(500, 600);

		// Impostando null, la finestra apparirà perfettamente al centro dello schermo
		frameCatalogo.setLocationRelativeTo(null);
		frameCatalogo.setVisible(true);
	}

	public void apriSchermataNotifiche() {
		GestioneNotifiche gestioneNotifiche = new GestioneNotifiche();

		//Chiediamo le notifiche al database
		List<Notifica> notifiche = gestioneNotifiche.getNotificheInOrdineCrescente();

		//Creiamo la schermata
		SchermataNotifiche schermata = new SchermataNotifiche();

		//Riempiamo la tabella
		schermata.popolaTabellaNotifiche(notifiche);

		//Apriamo la finestra
		JFrame frame = new JFrame("Notifiche Sotto Scorta");
		frame.setContentPane(schermata.getMainPanel());
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}