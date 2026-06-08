package setup;

import database.GestorePersistenza;
import database.JpaUtil;
import entity.*;

public class PopolaDatabaseProva {
    public static void main(String[] args) {
        GestorePersistenza db = new GestorePersistenza();
        GestioneUtenti gestioneUtenti = new GestioneUtenti();

        System.out.println("Inizio popolamento del database...");

        // 1. Creazione Utenti (Credenziali in minuscolo!)
        gestioneUtenti.registraDati("mario", "rossi", "mario.rossi@email.it", Ruolo.Responsabile);

        gestioneUtenti.registraDati("luigi", "verdi", "luigi.verdi@email.it", Ruolo.Operatore);



        // 2. Creazione Categorie (Tutto MAIUSCOLO)
        Categoria catElettronica = new Categoria("ELETTRONICA");
        Categoria catAlimentari = new Categoria("ALIMENTARI");

        // 3. Creazione Posizioni (Aree in MAIUSCOLO)
        Posizione posA = new Posizione(1, "ZONA A");
        Posizione posB = new Posizione(2, "ZONA B");

        // 4. Creazione Prodotti (Codici in MAIUSCOLO)
        // Prodotto 1: Normale disponibilità
        Prodotto p1 = new Prodotto("SKU-100", "Monitor 24 Pollici", "Monitor da ufficio", 5, catElettronica, posA);
        p1.setQuantitaDisponibile(15);

        // Prodotto 2: Sotto scorta (farà scattare test interessanti!)
        Prodotto p2 = new Prodotto("SKU-200", "Tastiera Meccanica", "Layout ITA", 10, catElettronica, posA);
        p2.setQuantitaDisponibile(2);

        // Prodotto 3: Esaurito
        Prodotto p3 = new Prodotto("MELA-01", "Mele Annurche", "Cassa da 5kg", 20, catAlimentari, posB);
        p3.setQuantitaDisponibile(0);

        // Salviamo i prodotti (grazie al Cascade, salverà automaticamente anche Categorie e Posizioni!)
        db.salvaTutti(p1, p2, p3);

        System.out.println("Database popolato con successo! 🎉");

        //Chiudiamo la connessione
        JpaUtil.getInstance().chiudi();
        System.out.println("Test terminato.");
    }
}