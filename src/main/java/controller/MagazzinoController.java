package controller;

import boundary.*;
import database.GestorePersistenza;
import entity.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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


	public void richiediScarico(String codiceProdotto, int quantitaDaScaricare, SchermataScarico boundary) {

		List<Prodotto> risultati = catalogoProdotti.cercaCodice(codiceProdotto);

		if (risultati == null || risultati.isEmpty()) {
			boundary.messaggioErrore("Prodotto con codice " + codiceProdotto + " non trovato.");
			return;
		}

		Prodotto prodotto = risultati.get(0);

		if (!prodotto.verificaOperazione(quantitaDaScaricare)) {
			boundary.messaggioErrore("Quantità insufficiente! Disponibile: " + prodotto.getQuantitaDisponibile());
			return;
		}

		boolean successo = catalogoProdotti.sottraiProdotto(prodotto, quantitaDaScaricare);

		if (successo) {
			boundary.messaggioConferma("Scarico effettuato con successo. Nuova disponibilità: " + prodotto.getQuantitaDisponibile());

			if (prodotto.isSottoScorta()) {
				GestioneNotifiche gestioneNotifiche = new GestioneNotifiche();
				// Generiamo e salviamo la notifica nel DB
				gestioneNotifiche.creaNotifica(prodotto);
			}

		} else {
			boundary.messaggioErrore("Errore durante il salvataggio sul database.");
		}
	}

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

	// 1. Questo metodo viene chiamato dal MainFrame quando l'operatore clicca "Effettua Scarico"
	public void apriSchermataOperatore() {
		// Recuperiamo la lista di tutti i prodotti dal database tramite il gestore persistenza
		GestorePersistenza gp = new GestorePersistenza();
		// cercaPerCampi con mappa vuota restituisce TUTTI i record della tabella Prodotto
		List<Prodotto> elencoProdotti = gp.cercaPerCampi(Prodotto.class, Map.of());

		// Istanziamo la schermata elenco passando il controller e la lista di prodotti da mostrare nella JTable
		SchermataOperatore schermataElenco = new SchermataOperatore(this, elencoProdotti);

		JFrame frame = new JFrame("Catalogo Prodotti Magazzino");
		frame.setContentPane(schermataElenco.getMainPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void apriSchermataScaricoSelezionato(Prodotto prodottoSelezionato) {
		// Istanziamo la SchermataScarico passando questo controller
		SchermataScarico schermataScarico = new SchermataScarico(this, prodottoSelezionato);

		// Creazione e configurazione della finestra nativa
		JFrame frameScarico = new JFrame("Effettua Scarico Prodotto - " + prodottoSelezionato.getNome());

		// Recuperiamo il pannello principale della schermata (JPanel)
		frameScarico.setContentPane(schermataScarico.getMainPanel());
		frameScarico.pack();
		frameScarico.setSize(450, 300); // Dimensioni adatte a un form di scarico

		// Centra la finestra sullo schermo
		frameScarico.setLocationRelativeTo(null);
		frameScarico.setVisible(true);
	}

	public void apriSchermataAnalisi() {
		// Istanziamo la schermata di inserimento date passando questo controller
		SchermataAnalisiMagazzino schermataInput = new SchermataAnalisiMagazzino(this);

		// Creiamo la finestra nativa
		JFrame frameInput = new JFrame("Seleziona Periodo Analisi");
		frameInput.setContentPane(schermataInput.getMainPanel());
		frameInput.pack();

		// Centriamo la finestra nello schermo e la rendiamo visibile
		frameInput.setLocationRelativeTo(null);
		frameInput.setVisible(true);
	}

}