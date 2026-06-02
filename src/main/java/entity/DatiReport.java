package entity;

import java.util.List;

public class DatiReport {

	private List<Movimento> listaMovimenti;
	private List<Prodotto> listaSottoScorta;
	private List<Prodotto> listaPiuMovimentati;

	public List<Movimento> getListaMovimenti() {
		return this.listaMovimenti;
	}

	public List<Prodotto> getListaSottoScorta() {
		return this.listaSottoScorta;
	}

	public List<Prodotto> getListaPiuMovimentati() {
		return this.listaPiuMovimentati;
	}

}