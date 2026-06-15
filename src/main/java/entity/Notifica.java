package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataEmissione;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    //costruttore vuoto utile per la persistenza
    public Notifica() {

    }

    //costruttore reale
    public Notifica(Prodotto prodotto) {
        this.prodotto = prodotto;
        this.dataEmissione = LocalDateTime.now();
    }

    public LocalDateTime getDataEmissione() { return dataEmissione; }
    public Prodotto getProdotto() { return prodotto; }
}
