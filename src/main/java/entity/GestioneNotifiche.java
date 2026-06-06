package entity;

import database.GestorePersistenza;

import java.util.List;

public class GestioneNotifiche {

    private GestorePersistenza gestorePersistenza;


    //metodo chiamato dal controller per inviare una notifica
    public void inviaNotifica(Prodotto prodotto, int quantitaDisp) {
        // TODO - implement GestioneNotifiche.inviaNotifica
        throw new UnsupportedOperationException();
    }

    public List<Notifica> getNotificheInOrdineCrescente() {
        GestorePersistenza gp = new GestorePersistenza();
        return gp.recuperaNotificheOrdinatePerData();
    }
}
