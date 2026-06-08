package setup;

import database.GestorePersistenza;
import database.JpaUtil;
import entity.*;

public class PopolaDatabaseProva {
    public static void main(String[] args) {
        GestorePersistenza db = new GestorePersistenza();
        GestioneUtenti gestioneUtenti = new GestioneUtenti();

        System.out.println("Inizio popolamento del database...");

        //creazione utenti lower case
        gestioneUtenti.registraDati("mario", "rossi", "mario.rossi@email.it", Ruolo.Responsabile);

        gestioneUtenti.registraDati("luigi", "verdi", "luigi.verdi@email.it", Ruolo.Operatore);



        //creazione categoria upper case
        Categoria catElettronica = new Categoria("ELETTRONICA");
        Categoria catAlimentari = new Categoria("ALIMENTARI");

        //creazione posizione upper cse
        Posizione posA = new Posizione(1, "ZONA A");
        Posizione posB = new Posizione(2, "ZONA B");

        //Creazione Prodotti (Codici in MAIUSCOLO)
        //        // Prodotto 1: Normale disponibilità
        Prodotto p1 = new Prodotto("SKU-100", "Monitor 24 Pollici", "Monitor da ufficio", 5, catElettronica, posA);
        p1.setQuantitaDisponibile(15);

        //Sotto scorta
        Prodotto p2 = new Prodotto("SKU-200", "Tastiera Meccanica", "Layout ITA", 10, catElettronica, posA);
        p2.setQuantitaDisponibile(2);

        //Esaurito
        Prodotto p3 = new Prodotto("MELA-01", "Mele Annurche", "Cassa da 5kg", 20, catAlimentari, posB);
        p3.setQuantitaDisponibile(0);
        Notifica n1 = new Notifica(p2);

        //Salviamo i prodotti
        db.salvaTutti(p1, p2, p3, n1);


        System.out.println("Database popolato con successo! 🎉");

        //Chiudiamo la connessione
        JpaUtil.getInstance().chiudi();
        System.out.println("Test terminato.");
    }
}