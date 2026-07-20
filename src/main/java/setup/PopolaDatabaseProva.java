package setup;

import database.GestorePersistenza;
import database.JpaUtil;
import entity.*;
import java.time.LocalDate;

public class PopolaDatabaseProva {
    public static void main(String[] args) {
        GestorePersistenza db = new GestorePersistenza();
        GestioneUtenti gestioneUtenti = new GestioneUtenti();

        System.out.println("Inizio popolamento del database...");

        // Creazione Utenti (Lower Case)
        gestioneUtenti.registraDati("mario", "rossi", "mario.rossi@email.it", "Responsabile");
        gestioneUtenti.registraDati("luigi", "verdi", "luigi.verdi@email.it", "Operatore");
        gestioneUtenti.registraDati("angelo", "russo", "angelo.russo21@studenti.unina.it", "Responsabile");
        gestioneUtenti.registraDati("alessandro", "scudieri", "al.scudieri@studenti.unina.it", "Responsabile");
        gestioneUtenti.registraDati("giovanni", "cozzolino", "giovanni.cozzolino13@studenti.unina.it", "Responsabile");
        gestioneUtenti.registraDati("alessio", "paparo", "aless.paparo@studenti.unina.it", "Responsabile");

        // Creazione Categorie (Upper Case)
        Categoria catElettronica = new Categoria("ELETTRONICA");
        Categoria catAlimentari = new Categoria("ALIMENTARI");

        // Creazione Posizioni (Upper Case)
        Posizione posA = new Posizione(1, "ZONA A");
        Posizione posB = new Posizione(2, "ZONA B");

        // Creazione Prodotti (Codici in MAIUSCOLO)
        // Prodotto 1: Normale disponibilità
        Prodotto p1 = new Prodotto("SKU-100", "Monitor 24 Pollici", "Monitor da ufficio", 5, catElettronica, posA);
        p1.setQuantitaDisponibile(15);

        // Prodotto 2: Sotto scorta (Giacenza 2 < Soglia 10)
        Prodotto p2 = new Prodotto("SKU-200", "Tastiera Meccanica", "Layout ITA", 10, catElettronica, posA);
        p2.setQuantitaDisponibile(2);

        // Prodotto 3: Esaurito e sotto scorta (Giacenza 0 < Soglia 20)
        Prodotto p3 = new Prodotto("MELA-01", "Mele Annurche", "Cassa da 5kg", 20, catAlimentari, posB);
        p3.setQuantitaDisponibile(30);

        // Creazione Notifica iniziale di prova
        Notifica n1 = new Notifica(p2);

        // Creazione Storico Movimenti per il Report Analisi

        // --- MOVIMENTI NEL PERIODO DI TEST (01/06/2026 - 15/06/2026) ---
        // Monitor 24" (p1): totale 30 + 25 = 55 unità (Sarà il 1° in classifica)
        Movimento m1 = new Movimento();
        m1.setData(LocalDate.of(2026, 6, 2));
        m1.setQuantitaProdotto(30);
        m1.setProdotto(p1);

        Movimento m2 = new Movimento();
        m2.setData(LocalDate.of(2026, 6, 10));
        m2.setQuantitaProdotto(25);
        m2.setProdotto(p1);

        // Tastiera Meccanica (p2): totale 12 unità (Sarà la 2° in classifica)
        Movimento m3 = new Movimento();
        m3.setData(LocalDate.of(2026, 6, 5));
        m3.setQuantitaProdotto(12);
        m3.setProdotto(p2);

        // --- MOVIMENTO FUORI PERIODO (Maggio 2026) ---
        // Serve a testare che il filtro 'BETWEEN' funzioni e lo escluda dall'analisi di Giugno
        Movimento mFuoriPeriodo = new Movimento();
        mFuoriPeriodo.setData(LocalDate.of(2026, 5, 25));
        mFuoriPeriodo.setQuantitaProdotto(100);
        mFuoriPeriodo.setProdotto(p3);

        // Salvataggio di tutte le Entity nel DB
        // Sfrutta la predisposizione di salvaTutti per fare un'unica transazione pulita
        db.salvaTutti(p1, p2, p3, n1, m1, m2, m3, mFuoriPeriodo);

        System.out.println("Database popolato con successo! 🎉");

        // Chiudiamo la connessione alla EntityManagerFactory
        JpaUtil.getInstance().chiudi();
        System.out.println("Test terminato.");
    }
}