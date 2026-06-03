package boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainFrame(Utente utenteLoggato) {

        // 1. Personalizziamo il messaggio di benvenuto
        lblBenvenuto.setText("Dashboard di: " + utenteLoggato.getNome() + " " + utenteLoggato.getCognome());

        // 2. Controllo dei Permessi (Gestione della visibilità)
        if (utenteLoggato instanceof Responsabile) {
            // Se è responsabile, vede tutto
            btnCreaProdotto.setVisible(true);
            btnVisualizzaAnalisiMagazzino.setVisible(true);
            btnEffettuaScarico.setVisible(false);
        } else if (utenteLoggato instanceof Operatore) {
            // Se è operatore, gli nascondiamo i bottoni da capo
            btnCreaProdotto.setVisible(false);
            btnVisualizzaAnalisiMagazzino.setVisible(false);
            btnEffettuaScarico.setVisible(true);

            // (In futuro qui potrai rendere visibili i bottoni specifici dell'operatore, es. "Gestisci Ordini")
        }

        // 3. Listener per il Logout (se lo hai inserito)
        if (btnLogout != null) {
            btnLogout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chiudiFinestra();
                    // Riapriamo la schermata iniziale per fare un nuovo accesso
                    SchermataIniziale.main(new String[]{});
                }
            });
        }
    }

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
