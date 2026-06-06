package setup;

import database.GestorePersistenza;
import database.JpaUtil;
import entity.Operatore;
import entity.Responsabile;

public class PopolaDatabaseProva {
    public static void main(String[] args) {
        System.out.println("Avvio popolamento database con utenti di prova...");

        //Recuperiamo l'istanza del GestorePersistenza
        GestorePersistenza gestore = new GestorePersistenza();

        //Creiamo un Responsabile di prova
        Responsabile resp = new Responsabile();
        resp.setNome("Mario");
        resp.setCognome("Rossi");
        resp.setEmail("mario.rossi@magazzino.it");

        //Creiamo un Operatore di prova
        Operatore oper = new Operatore();
        oper.setNome("Giuseppe");
        oper.setCognome("Verdi");
        oper.setEmail("giuseppe.verdi@magazzino.it");

        //Salviamo gli utenti
        System.out.println("Salvataggio degli utenti in corso...");

        boolean esitoResp = gestore.salva(resp);
        boolean esitoOper = gestore.salva(oper);

        //Verifichiamo l'esito
        if (esitoResp && esitoOper) {
            System.out.println("Utenti salvati con successo nel database!");
        } else {
            System.err.println("Errore durante il salvataggio degli utenti.");
        }

        //Chiudiamo la connessione
        JpaUtil.getInstance().chiudi();
        System.out.println("Test terminato.");
    }
}