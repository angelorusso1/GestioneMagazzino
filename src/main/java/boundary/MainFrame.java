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

                    SchermataIniziale finestraIniziale = new SchermataIniziale();

                    // 3. Creiamo la finestra nativa (JFrame) per rimpiazzare il vecchio main statico
                    JFrame frameIniziale = new JFrame("Sistema Gestione Magazzino - Benvenuto");
                    frameIniziale.setContentPane(finestraIniziale.getMainPanel());

                    // 4. Proprietà standard della finestra di ingresso
                    frameIniziale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameIniziale.setSize(500, 400); // Imposta la dimensione della finestra di benvenuto
                    frameIniziale.setLocationRelativeTo(null); // Centra la finestra sullo schermo

                    // 5. Rendiamo visibile la finestra di ingresso
                    frameIniziale.setVisible(true);
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

        if (btnEffettuaScarico != null) {
            btnEffettuaScarico.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Usiamo lo stesso approccio di istanziazione del controller visto sopra
                    MagazzinoController controllerScarico = new MagazzinoController((SchermataRegistrazione) null);

                    // Invochiamo il metodo appena creato per mostrare la finestra
                    controllerScarico.apriSchermataOperatore();
                }
            });
        }

        if (btnVisualizzaAnalisiMagazzino != null) {
            btnVisualizzaAnalisiMagazzino.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Istanziamo il controller seguendo il vostro standard
                    MagazzinoController controllerAnalisi = new MagazzinoController((SchermataRegistrazione) null);

                    // Chiamiamo il metodo appena creato per aprire il form delle date
                    controllerAnalisi.apriSchermataAnalisi();
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