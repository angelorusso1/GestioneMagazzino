package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movimento {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate Data;
	private int QuantitaProdotto;

	public LocalDate getData() {
		return this.Data;
	}

	public void setData(LocalDate Data) {
		this.Data = Data;
	}

	public int getQuantitaProdotto() {
		return this.QuantitaProdotto;
	}

	public void setQuantitaProdotto(int QuantitaProdotto) {
		this.QuantitaProdotto = QuantitaProdotto;
	}

}