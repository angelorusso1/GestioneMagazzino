package entity;

import jakarta.persistence.*;

@Entity
public class Posizione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int Scaffale;
	private String Area;

	//costruttore vuoto
	public Posizione() {
	}

	//costruttore per i parametri
	public Posizione(int scaffale, String area) {
		this.Scaffale = scaffale;
		this.Area = area;
	}

	public Long getId() {
		return this.id;
	}

	public int getScaffale() {
		return this.Scaffale;
	}

	public void setScaffale(int Scaffale) {
		this.Scaffale = Scaffale;
	}

	public String getArea() {
		return this.Area;
	}

	public void setArea(String Area) {
		this.Area = Area;
	}
}