package entity;

import jakarta.persistence.*;

@Entity
public class Posizione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int Scaffale;
	private String Area;

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