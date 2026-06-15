import boundary.SchermataIniziale;
import javax.swing.*;

public class MainGestioneMagazzino {
    public static void main(String[] args) {
        //grafica più moderna
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Istanziamo la classe della schermata iniziale
                SchermataIniziale finestraIniziale = new SchermataIniziale();

                // 2. Creiamo il vero contenitore Windows (JFrame)
                JFrame frame = new JFrame("Sistema Gestione Magazzino - Benvenuto");

                // 3. Gli passiamo il pannello grafico (adatta il nome del getter se diverso)
                frame.setContentPane(finestraIniziale.getMainPanel());

                // 4. Configura le opzioni standard della finestra
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiude l'app se premi la X
                frame.setSize(500, 400); // Imposta la dimensione iniziale della dashboard di benvenuto
                frame.setLocationRelativeTo(null); // Centra la finestra sullo schermo

                // 5. 🎯 RENDIAMO VISIBILE IL FRAME (Non la classe della schermata)
                frame.setVisible(true);
            }
        });
    }
}