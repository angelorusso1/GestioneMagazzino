package entity;

import jakarta.persistence.*;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Nome;

	//costruttore vuoto utile per la persistenza
	public Categoria() {
	}

	//costruttore per i parametri
	public Categoria(String nome) {
		this.Nome = nome;
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.Nome;
	}

	public void setNome(String Nome) {
		this.Nome = Nome;
	}
}