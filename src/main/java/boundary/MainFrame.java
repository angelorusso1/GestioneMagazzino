package boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.MagazzinoController;
import entity.Operatore;
import entity.Responsabile;
import entity.Utente;

public class MainFrame {
    private JPanel mainPanel;
    private JLabel lblBenvenuto;
    private JButton btnCreaProdotto;
    private JButton btnVisualizzaAnalisiMagazzino;
    private JButton btnLogout;
    private JButton btnEffettuaScarico;
    private JButton btnVisualizzaNotifiche;

    /**
     * Costruttore della Dashboard principale
     * @param utenteLoggato l'utente estratto dal database che contiene il ruolo
     */
    public MainFrame(Utente utenteLoggato) {

        //Messaggio di benvenuto dinamico
        lblBenvenuto.setText("Dashboard di: " + utenteLoggato.getNome() + " " + utenteLoggato.getCognome());

        //Controllo dei Permessi basato sul pattern di ereditarietà del dominio
        if (utenteLoggato instanceof Responsabile) {
            // Il Responsabile può creare prodotti e vedere le analisi, ma non scaricare le merci direttamente
            btnCreaProdotto.setVisible(true);
            btnVisualizzaAnalisiMagazzino.setVisible(true);
            btnEffettuaScarico.setVisible(false);
            btnVisualizzaNotifiche.setVisible(true);
        } else if (utenteLoggato instanceof Operatore) {
            // L'Operatore può solo effettuare lo scarico fisico dei colli dal magazzino
            btnCreaProdotto.setVisible(false);
            btnVisualizzaAnalisiMagazzino.setVisible(false);
            btnEffettuaScarico.setVisible(true);
            btnVisualizzaNotifiche.setVisible(false);
        }

        //Listener per la gestione del click sul pulsante "Crea Prodotto"
        if (btnCreaProdotto != null) {
            btnCreaProdotto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MagazzinoController controllerCatalogo = new MagazzinoController((SchermataRegistrazione) null);
                    controllerCatalogo.apriSchermataCreaProdotto();
                }
            });
        }

        //Listener per la gestione del click sul pulsante "Logout"
        if (btnLogout != null) {
            btnLogout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Chiude la dashboard corrente liberando le risorse grafiche
                    chiudiFinestra();
                    // Invoca il punto di ingresso statico del menu principale per un nuovo accesso
                    SchermataIniziale.main(new String[]{});
                }
            });
        }

        //Listener per la gestione del click sul pulsante "Visualizza Notifiche"
        if (btnVisualizzaNotifiche != null) {
            btnVisualizzaNotifiche.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MagazzinoController controllerNotifiche = new MagazzinoController((SchermataRegistrazione) null);

                    controllerNotifiche.apriSchermataNotifiche();
                }
            });
        }

    }

    /**
     * Recupera il frame nativo di Windows/OS che ospita il pannello corrente e lo distrugge
     */
    private void chiudiFinestra() {
        JFrame finestraCorrente = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        if (finestraCorrente != null) {
            finestraCorrente.dispose();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}