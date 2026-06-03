package boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataIniziale {
    private JPanel mainPanel;
    private JButton btnRegistrati;
    private JButton btnAccedi;

    public SchermataIniziale() {

        // 1. CLICK SU REGISTRAZIONE
        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameReg = new JFrame("Registrazione Nuovo Utente");
                SchermataRegistrazione schermataReg = new SchermataRegistrazione();

                frameReg.setContentPane(schermataReg.getMainPanel());
                frameReg.setSize(450, 350);
                frameReg.setLocationRelativeTo(null);
                frameReg.setVisible(true);
                // La SchermataIniziale resta aperta sotto, pronta a riprendere il controllo quando frameReg farà dispose()
            }
        });

        // 2. CLICK SU ACCESSO
        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameLogin = new JFrame("Accesso al Sistema");
                SchermataLogin schermataLogin = new SchermataLogin();

                frameLogin.setContentPane(schermataLogin.getMainPanel());
                frameLogin.setSize(400, 250);
                frameLogin.setLocationRelativeTo(null);
                frameLogin.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestione Magazzino - Menu Principale");
        SchermataIniziale avvio = new SchermataIniziale();

        frame.setContentPane(avvio.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiude l'app intera
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


