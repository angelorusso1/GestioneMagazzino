package entity;

import database.*;

import java.time.LocalDate;
import java.util.List;

public class StoricoMovimenti {

	private GestorePersistenza gestorePersistenza =  new GestorePersistenza();

	public boolean registraMovimentoScarico(Prodotto prodotto, int quantita) {
		Movimento nuovoMovimento = new Movimento();
		nuovoMovimento.setData(LocalDate.now());
		nuovoMovimento.setProdotto(prodotto);
		nuovoMovimento.setQuantitaProdotto(quantita);

		// Delega il salvataggio fisico al gestore della persistenza
		return gestorePersistenza.salva(nuovoMovimento);
	}

	public DatiReport ottieniReportAnalisi(LocalDate dataInizio, LocalDate dataFine) {
		// Interroga il gestore persistenza e restituisce il pacchetto dati completo
		return gestorePersistenza.generaReportAnalisi(dataInizio, dataFine);
	}

}