package entity;

import database.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CatalogoProdotti {

	private GestorePersistenza gestorePersistenza;

	public CatalogoProdotti() {
		this.gestorePersistenza = new GestorePersistenza();
	}


	public boolean verificaUnivocitaCodice(String codice) {
		Prodotto prodottoTrovato = gestorePersistenza.cercaPrimoPerCampi(
				Prodotto.class,
				Map.of("codice", codice)
		);
		return prodottoTrovato == null;
	}

	/**
	 * Implementazione del Caso d'Uso: Creazione Prodotti.
	 * Riceve i parametri atomici inviati dal MagazzinoController, istanzia internamente
	 * l'oggetto entità Prodotto con quantità iniziale configurata e lo memorizza nel sistema.
	 * * @param codice
	 * @param nome
	 * @param Descrizione
	 * @param posizioneInput
	 * @param categoriaInput
	 * @param soglia
	 * @param quantita
	 */
	public boolean aggiungiProdotto(String codice, String nome, String Descrizione, Posizione posizioneInput, Categoria categoriaInput, int soglia, int quantita) {

		Categoria categoriaTrovata = gestorePersistenza.cercaPrimoPerCampi(
				Categoria.class,
				Map.of("Nome", categoriaInput.getNome())
		);
		Categoria categoriaDefinitiva = (categoriaTrovata != null) ? categoriaTrovata : categoriaInput; //se trova la categoria nel db usa quella

		Posizione posizioneTrovata = gestorePersistenza.cercaPrimoPerCampi(
				Posizione.class,
				Map.of("Scaffale", posizioneInput.getScaffale(), "Area", posizioneInput.getArea())
		);
		Posizione posizioneDefinitiva = (posizioneTrovata != null) ? posizioneTrovata : posizioneInput; //se trova la posizione nel db usa quella

		Prodotto nuovoProdotto = new Prodotto(codice, nome, Descrizione, soglia, categoriaDefinitiva, posizioneDefinitiva);

		if (quantita > 0) {
			nuovoProdotto.setQuantitaDisponibile(quantita);
		}

		Prodotto prodottoSalvato = gestorePersistenza.aggiorna(nuovoProdotto);

		return prodottoSalvato != null; // Se non è null è andato tutto a buon fine
	}

	public List<Prodotto> getCatalogoCompleto() {
		//Ritorniamo la lista interna per consentire eventuali operazioni di lettura globali
		return gestorePersistenza.cercaPerCampi(Prodotto.class, Map.of());
	}

	/**
	 * Esegue la sottrazione della quantità e salva sul database.
	 */
	public boolean sottraiProdotto(Prodotto prodotto, int quantita) {
		int nuovaQuantita = prodotto.getQuantitaDisponibile() - quantita;
		prodotto.setQuantitaDisponibile(nuovaQuantita);

		Prodotto salvato = gestorePersistenza.aggiorna(prodotto);

		return salvato != null;
	}

	/**
	 * * @param codice
	 */
	public List<Prodotto> cercaCodice(String codice) {
		Prodotto trovato = gestorePersistenza.cercaPrimoPerCampi(
				Prodotto.class,
				Map.of("codice", codice)
		);

		List<Prodotto> risultati = new ArrayList<>();
		if (trovato != null) {
			risultati.add(trovato);
		}
		return risultati;
	}

	public List<Prodotto> ottieniCatalogoCompleto() {
		GestorePersistenza gp = new GestorePersistenza();
		return gp.cercaPerCampi(Prodotto.class, Map.of());
	}
}