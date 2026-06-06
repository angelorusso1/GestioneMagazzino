package setup; // Cambialo se l'hai messa in un pacchetto diverso

import database.JpaUtil;
import jakarta.persistence.EntityManager;

public class CreazioneTabelle {
    public static void main(String[] args) {
        System.out.println("Avvio del test di connessione a MySQL...");

        try {
            System.out.println("Lettura del persistence.xml e creazione delle tabelle in corso...");

            // Chiamando il JpaUtil, forziamo l'avvio del motore di Hibernate.
            // Hibernate si collega a MySQL e genera la struttura
            EntityManager em = JpaUtil.getInstance().getEntityManager();

            System.out.println("Connessione stabilita con successo!");
            System.out.println("Le tabelle dovrebbero essere pronte nel database.");

            // Chiudiamo le connessioni
            em.close();
            JpaUtil.getInstance().chiudi();
            System.out.println("Test completato senza errori.");

        } catch (Exception e) {
            System.err.println("Si è verificato un errore mortale di connessione:");
            e.printStackTrace();
        }
    }
}
