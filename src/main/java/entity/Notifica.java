package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Notifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String messaggio;
    private LocalDate dataEmissione;


    //costruttore vuoto
    public Notifica() {

    }

    //costruttore
    public Notifica(String messaggio, LocalDate dataEmissione) {
        this.messaggio = messaggio;
        this.dataEmissione = dataEmissione;
    }
}
