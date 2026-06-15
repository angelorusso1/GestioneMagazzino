package entity;

import database.*;

import java.time.LocalDate;
import java.util.List;

public class StoricoMovimenti {

	private GestorePersistenza gestorePersistenza;

	/**
	 * 
	 * @param dataInizio
	 * @param dataFine
	 */
	public List<Prodotto> getProdottiPiuMovimentati(LocalDate dataInizio, LocalDate dataFine) {
		DatiReport report = gestorePersistenza.generaReportAnalisi(dataInizio, dataFine);
		return report.getListaProdottiPiuMovimentati();
	}

}