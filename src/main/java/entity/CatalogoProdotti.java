package entity;

import database.*;

import java.util.List;

public class CatalogoProdotti {

	private GestorePersistenza gestorePersistenza;

	/**
	 * 
	 * @param prodotto
	 * @param quantita
	 */
	public void aggiungiQuantitaProdotto(Prodotto prodotto, int quantita) {
		// TODO - implement CatalogoProdotti.aggiungiQuantitaProdotto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param codice
	 */
	public boolean verificaUnivocitaCodice(String codice) {
		// TODO - implement CatalogoProdotti.verificaUnivocitaCodice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param codice
	 * @param nome
	 * @param Descrizione
	 * @param posizione
	 * @param categoria
	 * @param soglia
	 * @param quantita
	 */
	public void aggiungiProdotto(String codice, String nome, String Descrizione, Posizione posizione, Categoria categoria, int soglia, int quantita) {
		// TODO - implement CatalogoProdotti.aggiungiProdotto
		throw new UnsupportedOperationException();
	}

	public List<String> getNomiProdotti() {
		// TODO - implement CatalogoProdotti.getNomiProdotti
		throw new UnsupportedOperationException();
	}

	public List<Prodotto> getCatalogoCompleto() {
		// TODO - implement CatalogoProdotti.getCatalogoCompleto
		throw new UnsupportedOperationException();
	}

	public List<Prodotto> getCatalogoSottoScorta() {
		// TODO - implement CatalogoProdotti.getCatalogoSottoScorta
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
	public void modificaProdotto(String nuovoNome, String nuovaDescrizione, Posizione nuovaPosizione, int nuovaSoglia, int nuovaQuantita, Categoria nuovaCategoria, Prodotto prodotto) {
		// TODO - implement CatalogoProdotti.modificaProdotto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param prodotto
	 * @param quantita
	 */
	public void sottraiProdotto(Prodotto prodotto, int quantita) {
		// TODO - implement CatalogoProdotti.sottraiProdotto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param codice
	 */
	public List<Prodotto> cercaCodice(String codice) {
		// TODO - implement CatalogoProdotti.cercaCodice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 */
	public List<Prodotto> cercaNome(String nome) {
		// TODO - implement CatalogoProdotti.cercaNome
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param categoria
	 */
	public List<Prodotto> cercaCategoria(Categoria categoria) {
		// TODO - implement CatalogoProdotti.cercaCategoria
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param posizione
	 */
	public List<Prodotto> cercaPosizione(Posizione posizione) {
		// TODO - implement CatalogoProdotti.cercaPosizione
		throw new UnsupportedOperationException();
	}

}