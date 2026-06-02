package entity;

import database.*;

import java.time.LocalDate;
import java.util.List;

public class StoricoMovimenti {

	private GestorePersistenza gestorePersistenza;

	/**
	 * 
	 * @param Data
	 * @param quantita
	 * @param tipo
	 * @param prodotto
	 */
	public void aggiungiMoviemento(LocalDate Data, int quantita, TipoMovimento tipo, Prodotto prodotto) {
		// TODO - implement StoricoMovimenti.aggiungiMoviemento
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dataInizio
	 * @param dataFine
	 */
	public List<Movimento> getMovimenti(LocalDate dataInizio, LocalDate dataFine) {
		// TODO - implement StoricoMovimenti.getMovimenti
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dataInizio
	 * @param dataFine
	 */
	public List<Movimento> getProdottiPiuMovimentati(LocalDate dataInizio, LocalDate dataFine) {
		// TODO - implement StoricoMovimenti.getProdottiPiuMovimentati
		throw new UnsupportedOperationException();
	}

}