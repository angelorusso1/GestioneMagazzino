package controller;

import boundary.*;
import database.GestorePersistenza;
import entity.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.time.format.DateTimeFormatter;

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

			String ruolo = "";
			if (utenteTrovato instanceof Responsabile) {
				ruolo = "Responsabile";
			} else if (utenteTrovato instanceof Operatore) {
				ruolo = "Operatore";
			}

			JFrame framePrincipale = new JFrame("Sistema di Gestione Magazzino");
			MainFrame mainFrame = new MainFrame(utenteTrovato.getNome(), utenteTrovato.getCognome(), ruolo);

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
			boundary.messaggioErrore("ATTENZIONE: Il codice " + codice + " esiste già. Creazione annullata.");
		} else {
			boolean successo = catalogoProdotti.aggiungiProdotto(codice, nome, descrizione, posizione, categoria, soglia, 0);

			if (successo) {
				boundary.messaggioConferma("Prodotto '" + nome + "' inserito correttamente nel catalogo del magazzino.");
			} else {
				boundary.messaggioErrore("ERRORE: Impossibile salvare il prodotto nel database. Riprova.");
			}
		}
	}

	public void richiediRegistrazione(String nome, String cognome, String email, String ruolo) {
		GestioneUtenti gestioneUtenti = new GestioneUtenti();

		// Controlliamo se l'email è già presente nel DB
		if (gestioneUtenti.emailGiaRegistrata(email)) {
			schermataRegistrazione.messaggioErrore("ERRORE: L'indirizzo email '" + email + "' è già associato a un account esistente.");
			return; // Interrompe immediatamente il metodo evitando il duplicato
		}

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
			// Registrazione movimento tramite StoricoMovimenti
			StoricoMovimenti storico = new StoricoMovimenti();
			boolean movimentoRegistrato = storico.registraMovimentoScarico(prodotto, quantitaDaScaricare);

			if (movimentoRegistrato) {
				boundary.messaggioConferma("Scarico effettuato con successo. Nuova disponibilità: " + prodotto.getQuantitaDisponibile());
			} else {
				boundary.messaggioErrore("Errore durante la registrazione dello storico movimenti.");
			}
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

		// 1. Richiede analisi magazzino allo storico movimenti (LOGICA DI DOMINIO - Invariato)
		StoricoMovimenti storico = new StoricoMovimenti();
		DatiReport reportMagazzino = storico.ottieniReportAnalisi(dataInizio, dataFine);

		// 2. DISACCOPPIAMENTO (BCE): Il Controller converte gli oggetti complessi in matrici primitive

		// -- Preparazione Dati Movimenti --
		Object[][] datiMovimenti = new Object[reportMagazzino.getListaMovimenti().size()][3];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (int i = 0; i < reportMagazzino.getListaMovimenti().size(); i++) {
			Movimento m = reportMagazzino.getListaMovimenti().get(i);
			datiMovimenti[i][0] = m.getData().format(formatter); // La data diventa una semplice stringa
			datiMovimenti[i][1] = m.getProdotto().getNome();     // Estraggo il nome senza passare l'intero oggetto Prodotto
			datiMovimenti[i][2] = m.getQuantitaProdotto();
		}

		// -- Preparazione Dati Classifica --
		Object[][] datiClassifica = new Object[reportMagazzino.getListaProdottiPiuMovimentati().size()][3];
		for (int i = 0; i < reportMagazzino.getListaProdottiPiuMovimentati().size(); i++) {
			Prodotto p = reportMagazzino.getListaProdottiPiuMovimentati().get(i);
			datiClassifica[i][0] = (i + 1) + "°";
			datiClassifica[i][1] = p.getId();
			datiClassifica[i][2] = p.getNome();
		}

		// -- Preparazione Dati Sotto Scorta --
		Object[][] datiSottoScorta = new Object[reportMagazzino.getListaProdottiSottoScorta().size()][4];
		for (int i = 0; i < reportMagazzino.getListaProdottiSottoScorta().size(); i++) {
			Prodotto p = reportMagazzino.getListaProdottiSottoScorta().get(i);
			datiSottoScorta[i][0] = p.getId();
			datiSottoScorta[i][1] = p.getNome();
			datiSottoScorta[i][2] = p.getQuantitaDisponibile();
			datiSottoScorta[i][3] = p.getSogliaMinima();
		}

		// 3. Istanziamo la schermata di output passando le tre matrici di dati base
		OutputSchermataAnalisi schermataRisultati = new OutputSchermataAnalisi(datiMovimenti, datiClassifica, datiSottoScorta);

		// Rendiamo visibile la finestra (Invariato)
		schermataRisultati.setVisible(true);
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

	// Questo metodo viene chiamato dal MainFrame quando l'operatore clicca "Effettua Scarico"
	public void apriSchermataOperatore() {
		// cercaPerCampi con mappa vuota restituisce TUTTI i record della tabella Prodotto
		List<Prodotto> elencoProdotti = catalogoProdotti.ottieniCatalogoCompleto();

		// Istanziamo la schermata elenco passando il controller e la lista di prodotti da mostrare nella JTable
		SchermataOperatore schermataElenco = new SchermataOperatore(this, elencoProdotti);

		JFrame frame = new JFrame("Catalogo Prodotti Magazzino");
		frame.setContentPane(schermataElenco.getMainPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void apriSchermataScaricoSelezionato(Prodotto prodottoSelezionato) {
		// Estraiamo i dati primitivi dall'entità PRIMA di passarli alla Boundary
		String codice = prodottoSelezionato.getCodice();
		String nome = prodottoSelezionato.getNome();

		// Istanziamo la SchermataScarico passando questo controller
		SchermataScarico schermataScarico = new SchermataScarico(this, codice, nome);

		// Creazione e configurazione della finestra nativa
		JFrame frameScarico = new JFrame("Effettua Scarico Prodotto - " + nome);

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