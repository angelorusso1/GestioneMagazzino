package entity;

import jakarta.persistence.*;

@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Nome;
	private String Descrizione;
	private int SogliaMinima;
	private int QuantitaDisponibile = 0;
	private String codice;

	public Long getId() {
		return this.Id;
	}

	public String getNome() {
		return this.Nome;
	}

	public void setNome(String Nome) {
		this.Nome = Nome;
	}

	public String getDescrizione() {
		return this.Descrizione;
	}

	public void setDescrizione(String Descrizione) {
		this.Descrizione = Descrizione;
	}

	public int getSogliaMinima() {
		return this.SogliaMinima;
	}

	public void setSogliaMinima(int SogliaMinima) {
		this.SogliaMinima = SogliaMinima;
	}

	public int getQuantitaDisponibile() {
		return this.QuantitaDisponibile;
	}

	public void setQuantitaDisponibile(int QuantitaDisponibile) {
		this.QuantitaDisponibile = QuantitaDisponibile;
	}

	public boolean isSottoScorta() {
		// TODO - implement Prodotto.isSottoScorta
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param quantita
	 */
	public boolean verificaOperazione(int quantita) {
		// TODO - implement Prodotto.verificaOperazione
		throw new UnsupportedOperationException();
	}

	public Categoria getCategoria() {
		// TODO - implement Prodotto.getCategoria
		throw new UnsupportedOperationException();
	}

	public Posizione getPosizione() {
		// TODO - implement Prodotto.getPosizione
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param posizione
	 */
	public void setPosizione(Posizione posizione) {
		// TODO - implement Prodotto.setPosizione
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param categoria
	 */
	public void setCategoria(Categoria categoria) {
		// TODO - implement Prodotto.setCategoria
		throw new UnsupportedOperationException();
	}

}