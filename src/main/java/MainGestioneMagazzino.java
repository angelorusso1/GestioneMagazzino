import boundary.SchermataIniziale;
import javax.swing.*;

public class MainGestioneMagazzino {
    public static void main(String[] args) {
        //grafica moderna
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Istanziamo la classe della schermata iniziale
                SchermataIniziale finestraIniziale = new SchermataIniziale();

                // Creiamo il contenitore Windows (JFrame)
                JFrame frame = new JFrame("Sistema Gestione Magazzino - Benvenuto");

                // Gli passiamo il pannello grafico
                frame.setContentPane(finestraIniziale.getMainPanel());

                // Configura le opzioni standard della finestra
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiude l'app se premi la X
                frame.setSize(500, 400); // Imposta la dimensione iniziale della dashboard di benvenuto
                frame.setLocationRelativeTo(null); // Centra la finestra sullo schermo

                // Rendiamo visibile il frame
                frame.setVisible(true);
            }
        });
    }
}