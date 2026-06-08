package entity;

import jakarta.persistence.*;

@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(nullable = false, unique = true)
	private String codice;
	private String descrizione;

	@Column(nullable = false)
	private int sogliaMinima;

	@Column(nullable = false)
	private int quantitaDisponibile = 0;

	//Relazione: Molti Prodotti appartengono a una Categoria
	@ManyToOne(cascade = CascadeType.ALL) //serve a creare la tabella Categoria a cascata dalla creazione di un prodotto
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	//Relazione: Molti Prodotti sono collocati in una Posizione
	@ManyToOne(cascade = CascadeType.ALL) //serve a creare la tabella Posizione a cascata dalla creazione di un prodotto
	@JoinColumn(name = "posizione_id")
	private Posizione posizione;

	/**
	 * Costruttore VUOTO (Obbligatorio per JPA)
	 */
	public Prodotto() {
	}

	/**
	 * Costruttore con parametri per la logica di business (Creazione Prodotti)
	 */
	public Prodotto(String codice, String nome, String descrizione, int sogliaMinima, Categoria categoria, Posizione posizione) {
		//controllo sull'inizializzazione della soglia minima (non può essere negativa)
		if (sogliaMinima < 0) {
			throw new IllegalArgumentException("ERRORE: La soglia minima non può essere un valore negativo!");
		}

		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.sogliaMinima = sogliaMinima;
		this.categoria = categoria;
		this.posizione = posizione;
		this.quantitaDisponibile = 0;
	}

	public Long getId() {
		return this.id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getSogliaMinima() {
		//la soglia minima come la quantità non può essere negativa
		if (sogliaMinima < 0) {
			throw new IllegalArgumentException("ERRORE: La soglia minima non può essere un valore negativo!");
		}
		return this.sogliaMinima;
	}

	public void setSogliaMinima(int sogliaMinima) {
		this.sogliaMinima = sogliaMinima;
	}

	public int getQuantitaDisponibile() {
		return this.quantitaDisponibile;
	}

	public void setQuantitaDisponibile(int quantitaDisponibile) {
		//la quantità disponibile di un prodotto non può essere negativa
		if (quantitaDisponibile < 0) {
			throw new IllegalArgumentException("ERRORE CRITICO: La quantità disponibile non può essere negativa!");
		}

		this.quantitaDisponibile = quantitaDisponibile;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Posizione getPosizione() {
		return this.posizione;
	}

	public void setPosizione(Posizione posizione) {
		this.posizione = posizione;
	}


	public boolean isSottoScorta() {
		return this.quantitaDisponibile < this.sogliaMinima;
	}

	/**
	 * Verifica se è possibile effettuare uno scarico della quantità richiesta
	 * @param quantita la quantità da prelevare
	 */
	public boolean verificaOperazione(int quantita) {
		return this.quantitaDisponibile >= quantita;
	}
}