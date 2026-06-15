package entity;

import java.util.List;

public class DatiReport {

	// Le tre liste che conterranno i risultati dell'analisi
	private List<Prodotto> listaProdottiSottoScorta;
	private List<Prodotto> listaProdottiPiuMovimentati;
	private List<Movimento> listaMovimenti;

	// Costruttore vuoto utile per la persistenza
	public DatiReport() {
	}


	public List<Prodotto> getListaProdottiSottoScorta() {
		return listaProdottiSottoScorta;
	}

	public void setListaProdottiSottoScorta(List<Prodotto> listaProdottiSottoScorta) {
		this.listaProdottiSottoScorta = listaProdottiSottoScorta;
	}

	public List<Prodotto> getListaProdottiPiuMovimentati() {
		return listaProdottiPiuMovimentati;
	}

	public void setListaProdottiPiuMovimentati(List<Prodotto> listaProdottiPiuMovimentati) {
		this.listaProdottiPiuMovimentati = listaProdottiPiuMovimentati;
	}

	public List<Movimento> getListaMovimenti() {
		return listaMovimenti;
	}

	public void setListaMovimenti(List<Movimento> listaMovimenti) {
		this.listaMovimenti = listaMovimenti;
	}

}