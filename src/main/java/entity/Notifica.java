package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Notifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmissione;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    //costruttore vuoto
    public Notifica() {

    }

    //costruttore
    public Notifica(Prodotto prodotto) {
        this.prodotto = prodotto;
        this.dataEmissione = LocalDate.now();
    }

    public LocalDate getDataEmissione() { return dataEmissione; } // <-- Ritorna LocalDate
    public Prodotto getProdotto() { return prodotto; }
}
