package entity;

import database.GestorePersistenza;

import java.util.List;

public class GestioneNotifiche {

    private GestorePersistenza gp = new GestorePersistenza();


    //metodo chiamato dal controller per inviare una notifica
    public void creaNotifica(Prodotto prodotto) {
        // Usiamo il costruttore specifico di Notifica che imposta automaticamente la data odierna
        Notifica nuovaNotifica = new Notifica(prodotto);

        // Salviamo l'entità notifica nel database
        gp.aggiorna(nuovaNotifica);
    }

    public List<Notifica> getNotificheInOrdineCrescente() {
        return gp.recuperaNotificheOrdinatePerData();
    }
}
