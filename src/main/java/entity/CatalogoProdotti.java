package entity;

import database.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogoProdotti {

	private GestorePersistenza gestorePersistenza;
	//Lista interna in-memory per tracciare i prodotti e consentire la simulazione dei metodi
	private List<Prodotto> listaProdotti;

	//Costruttore inserito per inizializzare la struttura di contenimento ed evitare NullPointerException
	public CatalogoProdotti() {
		this.listaProdotti = new ArrayList<>();
		this.gestorePersistenza = new GestorePersistenza();
	}


	public boolean verificaUnivocitaCodice(String codice) {
		for (Prodotto p : this.listaProdotti) {
			if (p.getCodice() != null && p.getCodice().equals(codice)) {
				return false; //Trovato un duplicato, il codice non è univoco
			}
		}
		return true; //Nessun duplicato trovato, il codice può essere utilizzato
	}

	/**
	 * Implementazione del Caso d'Uso: Creazione Prodotti.
	 * Riceve i parametri atomici inviati dal MagazzinoController, istanzia internamente
	 * l'oggetto entità Prodotto con quantità iniziale configurata e lo memorizza nel sistema.
	 * * @param codice
	 * @param nome
	 * @param Descrizione
	 * @param posizione
	 * @param categoria
	 * @param soglia
	 * @param quantita
	 */
	public boolean aggiungiProdotto(String codice, String nome, String Descrizione, Posizione posizione, Categoria categoria, int soglia, int quantita) {
		//Creazione dell'istanza dell'entità Prodotto sfruttando il suo costruttore
		Prodotto nuovoProdotto = new Prodotto(codice, nome, Descrizione, soglia, categoria, posizione);

		//Se nel flusso la quantità iniziale venisse forzata a un valore diverso da 0
		if (quantita > 0) {
			nuovoProdotto.setQuantitaDisponibile(quantita);
		}

		//Salva il nuovo prodotto sul DB
		boolean salvatoSuDb = gestorePersistenza.salva(nuovoProdotto);

		if (salvatoSuDb) {
			// Aggiunta alla lista in memoria
			this.listaProdotti.add(nuovoProdotto);
			System.out.println("[DB] Nuovo oggetto Prodotto salvato con successo.");
			return true; // Diciamo al Controller che è andato tutto bene
		} else {
			System.out.println("[ERRORE] Fallimento nel salvataggio.");
			return false; // Diciamo al Controller che c'è stato un problema
		}
	}

	/**
	 * * @param prodotto
	 * @param quantita
	 */
	public void aggiungiQuantitaProdotto(Prodotto prodotto, int quantita) {
		// TODO - implement CatalogoProdotti.aggiungiQuantitaProdotto
		throw new UnsupportedOperationException();
	}

	public List<String> getNomiProdotti() {
		// TODO - implement CatalogoProdotti.getNomiProdotti
		throw new UnsupportedOperationException();
	}

	public List<Prodotto> getCatalogoCompleto() {
		//Ritorniamo la lista interna per consentire eventuali operazioni di lettura globali
		return this.listaProdotti;
	}

	public List<Prodotto> getCatalogoSottoScorta() {
		// TODO - implement CatalogoProdotti.getCatalogoSottoScorta
		throw new UnsupportedOperationException();
	}

	/**
	 * * @param nuovoNome
	 * @param nuovaDescrizione
	 * @param nuovaPosizione
	 * @param nuovaSoglia
	 * @param nuovaQuantita
	 * @param nuovaCategoria
	 * @param prodotto
	 */
	public void modificaProdotto(String nuovoNome, String nuovaDescrizione, Posizione nuovaPosizione, int nuovaSoglia, int nuovaQuantita, Categoria nuovaCategoria, Prodotto prodotto) {
		// TODO - implement CatalogoProdotti.modificaProdotto
		throw new UnsupportedOperationException();
	}

	/**
	 * * @param prodotto
	 * @param quantita
	 */
	public void sottraiProdotto(Prodotto prodotto, int quantita) {
		// TODO - implement CatalogoProdotti.sottraiProdotto
		throw new UnsupportedOperationException();
	}

	/**
	 * * @param codice
	 */
	public List<Prodotto> cercaCodice(String codice) {
		// TODO - implement CatalogoProdotti.cercaCodice
		throw new UnsupportedOperationException();
	}

	/**
	 * * @param nome
	 */
	public List<Prodotto> cercaNome(String nome) {
		// TODO - implement CatalogoProdotti.cercaNome
		throw new UnsupportedOperationException();
	}

	/**
	 * * @param categoria
	 */
	public List<Prodotto> cercaCategoria(Categoria categoria) {
		// TODO - implement CatalogoProdotti.cercaCategoria
		throw new UnsupportedOperationException();
	}

	/**
	 * * @param posizione
	 */
	public List<Prodotto> cercaPosizione(Posizione posizione) {
		// TODO - implement CatalogoProdotti.cercaPosizione
		throw new UnsupportedOperationException();
	}
}